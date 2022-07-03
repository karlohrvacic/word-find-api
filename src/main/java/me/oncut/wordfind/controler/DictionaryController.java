package me.oncut.wordfind.controler;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.oncut.wordfind.model.Dictionary;
import me.oncut.wordfind.service.DictionaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/languages/supported")
    List<Dictionary> getSupportedLanguages() {
        return dictionaryService.getSupportedLanguages();
    }

}
