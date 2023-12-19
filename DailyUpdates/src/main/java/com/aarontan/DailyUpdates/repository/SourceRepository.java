package com.aarontan.DailyUpdates.repository;

import com.aarontan.DailyUpdates.models.TopSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<TopSource, Integer> {
}
