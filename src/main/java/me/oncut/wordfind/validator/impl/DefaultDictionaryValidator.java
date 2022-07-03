package me.oncut.wordfind.validator.impl;

import lombok.RequiredArgsConstructor;
import me.oncut.wordfind.exception.DictionaryException;
import me.oncut.wordfind.repository.DictionaryRepository;
import me.oncut.wordfind.validator.DictionaryValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultDictionaryValidator implements DictionaryValidator {

    private final DictionaryRepository dictionaryRepository;

    @Override
    public void languageSupported(String language) {
        if (!dictionaryRepository.existsByIsoCodeIgnoreCase(language)) {
            throw new DictionaryException("Language not supported");
        }
    }

}
