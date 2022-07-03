package me.oncut.wordfind.service;

import java.util.List;
import me.oncut.wordfind.dto.WordDto;

public interface WordService {

    List<String> findWordsWithArgs(WordDto wordDto);

    List<String> findWordsPrimitive(String language, String letters);

}
