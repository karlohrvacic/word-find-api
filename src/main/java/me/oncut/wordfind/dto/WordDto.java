package me.oncut.wordfind.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
public class WordDto {

    @NotBlank
    private String language;

    @NotBlank
    private String letters;

    private Integer maxLength;

}
