package me.oncut.wordfind.bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        loadWordsToDatabase();
    }

    private void loadWordsToDatabase() {
        final List<Dictionary> dictionaries = dictionaryRepository.findAll();
        log.info(String.format("Found %d dictionaries", dictionaries.size()));
        for (final Dictionary dictionary : dictionaries) {
            try (InputStream in = getClass().getResourceAsStream(String.format("/dictionaries/%s", dictionary.getDictionaryFileName()));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String word;
                while ((word = reader.readLine()) != null) {
                    wordRepository.save(Word.builder().dictionary(dictionary).name(word).length(word.length()).build());
                }
                log.info(String.format("Dictionary for %s language has %d words", dictionary.getEnglishLanguageName(), wordRepository.count()));
            } catch (Exception e) {
                throw new DictionaryException("Dictionary read error", e);
            }
        }
    }
}
