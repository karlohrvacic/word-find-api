package me.oncut.wordfind.service.impl;

import java.util.ArrayList;
import java.util.List;
import liquibase.util.StringUtil;
import lombok.RequiredArgsConstructor;
import me.oncut.wordfind.dto.WordDto;
import me.oncut.wordfind.model.Dictionary;
import me.oncut.wordfind.model.Word;
import me.oncut.wordfind.repository.DictionaryRepository;
import me.oncut.wordfind.repository.WordRepository;
import me.oncut.wordfind.service.WordService;
import me.oncut.wordfind.util.StringToMapUtil;
import me.oncut.wordfind.validator.DictionaryValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class DefaultWordService implements WordService {

    private final DictionaryValidator dictionaryValidator;
    private final DictionaryRepository dictionaryRepository;
    private final WordRepository wordRepository;

    @Override
    public List<String> findWordsWithArgs(@RequestBody final WordDto wordDto) {
        final Dictionary dictionary = getDictionary(wordDto.getLanguage());
        final List<Word> words;
        if (wordDto.getMaxLength() != null) {
            words = wordRepository.getByDictionaryAndLengthEqualsOrderByLengthDesc(dictionary, wordDto.getMaxLength());
        } else {
             words = wordRepository.getByDictionaryAndLengthLessThanEqualOrderByLengthDesc(dictionary, wordDto.getLetters().length());
        }
        return getResult(wordDto.getLetters(), words);

    }

    @Override
    public List<String> findWordsPrimitive(final String language, final String letters) {
        final Dictionary dictionary = getDictionary(language);
        final List<Word> words = wordRepository.getByDictionaryAndLengthLessThanEqualOrderByLengthDesc(dictionary, letters.length());
        return getResult(letters, words);
    }

    private List<String> getResult(String letters, List<Word> words) {
        final List<String> result = new ArrayList<>();

        for (final Word word : words) {
            if (Boolean.TRUE.equals(StringToMapUtil.isWordCompatible(letters, word))) {
                result.add(word.getName());
            }
        }

        return result;
    }

    private Dictionary getDictionary(String language) {
        dictionaryValidator.languageSupported(language);
        return dictionaryRepository.getByIsoCodeIgnoreCase(language);
    }

}
