package me.oncut.wordfind.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {

    @Id
    @SequenceGenerator(name = "language_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "language_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String englishLanguageName;

    @Column(unique = true)
    private String localLanguageName;

    @Column(unique = true)
    private String isoCode;

    @JsonIgnore
    @Column(unique = true)
    private String dictionaryFileName;

    @OneToMany
    @JsonIgnore
    private List<Word> words;

}
