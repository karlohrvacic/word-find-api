package me.oncut.wordfind.controler;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.oncut.wordfind.dto.WordDto;
import javax.validation.Valid;
import me.oncut.wordfind.service.WordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/find")
public class WordFindController {

    private final WordService wordService;

    @PostMapping
    List<String> findLetterWordCustom(@Valid @RequestBody final WordDto wordDto) {
        return wordService.findWordsWithArgs(wordDto);
    }

    @GetMapping("/{language}/{letters}")
    List<String> findLetterWordPrimitive(@PathVariable final String language, @PathVariable final String letters) {
        return wordService.findWordsPrimitive(language, letters);
    }

}
