package me.oncut.wordfind.bean;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import me.oncut.wordfind.exception.DictionaryException;
import me.oncut.wordfind.model.Dictionary;
import me.oncut.wordfind.model.Word;
import me.oncut.wordfind.repository.DictionaryRepository;
import me.oncut.wordfind.repository.WordRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


@Component
@CommonsLog
@RequiredArgsConstructor
public class StartupAction implements ApplicationListener<ContextRefreshedEvent> {

    private final DictionaryRepository dictionaryRepository;
    private final WordRepository wordRepository;
    private final ResourceLoader resourceLoader;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        try {
            loadWordsToDatabase();
        } catch (Exception e) {
            throw new DictionaryException("Dictionary read error", e);
        }
    }

    private void loadWordsToDatabase() throws Exception {
        final List<Dictionary> dictionaries = dictionaryRepository.findAll();
        log.info(String.format("Found %d dictionaries", dictionaries.size()));
        for (final Dictionary dictionary : dictionaries) {
            final Resource resource = resourceLoader.getResource(String.format("classpath:dictionaries/%s", dictionary.getDictionaryFileName()));
            List<String> words = Files.readAllLines(Paths.get(resource.getURI()), StandardCharsets.UTF_8);
            log.info(String.format("Dictionary for %s language has %d words", dictionary.getEnglishLanguageName(), words.size()));
            for (final String word : words) {
                wordRepository.save(Word.builder().dictionary(dictionary).name(word).length(word.length()).build());
            }
        }
    }
}
