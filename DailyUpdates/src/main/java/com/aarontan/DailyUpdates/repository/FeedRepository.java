package com.aarontan.DailyUpdates.repository;

import com.aarontan.DailyUpdates.models.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Integer> {
}
