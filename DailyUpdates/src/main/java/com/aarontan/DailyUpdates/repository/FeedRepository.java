package com.aarontan.DailyUpdates.repository;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Integer> {
    List<FeedsOnly> findByUserOrderByIdDesc(User user);

    interface FeedsOnly {
        int getId();
        String getName();
    }
}
