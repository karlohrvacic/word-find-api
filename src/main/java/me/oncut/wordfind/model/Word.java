package me.oncut.wordfind.model;

import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.oncut.wordfind.util.StringToMapUtil;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id
    @SequenceGenerator(name = "word_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "word_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Dictionary dictionary;

    private String name;

    private Integer length;

    @Transient
    private Map<Character, Integer> letterMap;

    public Map<Character, Integer> getLetterMap() {
        return StringToMapUtil.stringToWordMap(this.name);
    }

}
