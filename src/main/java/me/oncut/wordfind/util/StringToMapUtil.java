package me.oncut.wordfind.util;

import java.util.HashMap;
import java.util.Map;
import me.oncut.wordfind.model.Word;
import org.springframework.stereotype.Component;

@Component
public final class StringToMapUtil {

    public static Map<Character, Integer> stringToWordMap(final String word) {
        Map<Character, Integer> result = new HashMap<>();

        for (Character letter : word.toLowerCase().toCharArray()) {
            if (result.containsKey(letter)) {
                int count = result.get(letter);
                result.put(letter, count + 1);
            } else {
                result.put(letter, 1);
            }
        }
        return result;
    }

    public static Boolean isWordCompatible(final String letters,
                                           final Word testedWord) {
        if (testedWord.getLength() > letters.length()) {
            return false;
        }
        final var lettersMap = stringToWordMap(letters);
        final var testedWordMap = stringToWordMap(testedWord.getName());

        for (Character key : testedWordMap.keySet() ) {
            if (!lettersMap.containsKey(key)) {
                return false;
            }
            if (lettersMap.get(key) < testedWordMap.get(key)) {
                return false;
            }
        }

        return true;
    }
}
