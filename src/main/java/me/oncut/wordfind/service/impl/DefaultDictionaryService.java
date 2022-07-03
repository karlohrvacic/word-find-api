package me.oncut.wordfind.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.oncut.wordfind.model.Dictionary;
import me.oncut.wordfind.repository.DictionaryRepository;
import me.oncut.wordfind.service.DictionaryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultDictionaryService implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    @Override
    public List<Dictionary> getSupportedLanguages() {
        return dictionaryRepository.findAll();
    }

}
