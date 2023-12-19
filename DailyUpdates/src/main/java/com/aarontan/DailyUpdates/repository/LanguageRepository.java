package com.aarontan.DailyUpdates.repository;

import com.aarontan.DailyUpdates.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByLanguageCode(String languageCode);
}
