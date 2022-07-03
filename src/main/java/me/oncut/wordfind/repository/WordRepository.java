package me.oncut.wordfind.repository;

import java.util.List;
import me.oncut.wordfind.model.Dictionary;
import me.oncut.wordfind.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    List<Word> getByDictionaryAndLengthLessThanEqualOrderByLengthDesc(Dictionary dictionary, Integer length);
    List<Word> getByDictionaryAndLengthEqualsOrderByLengthDesc(Dictionary dictionary, Integer length);

    long count();

}
