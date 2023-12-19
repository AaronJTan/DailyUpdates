package com.aarontan.DailyUpdates.repository;

import com.aarontan.DailyUpdates.models.TopSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceRepository extends JpaRepository<TopSource, String>, JpaSpecificationExecutor<TopSource> {
    List<TopSource> findAll(Specification<TopSource> spec);
}
