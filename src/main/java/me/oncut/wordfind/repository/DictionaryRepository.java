package me.oncut.wordfind.repository;

import me.oncut.wordfind.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    boolean existsByIsoCodeIgnoreCase(String languageIso);

    Dictionary getByIsoCodeIgnoreCase(String languageIso);

}