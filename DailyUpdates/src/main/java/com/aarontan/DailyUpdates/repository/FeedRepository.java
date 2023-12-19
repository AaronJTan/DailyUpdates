package com.aarontan.DailyUpdates.repository;

import com.aarontan.DailyUpdates.models.Feed;
import com.aarontan.DailyUpdates.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Integer> {
    List<FeedsOnly> findByUserOrderByIdDesc(User user);

    interface FeedsOnly {
        int getId();
        String getName();
    }

    @Query(value = "SELECT GROUP_CONCAT(source_id) FROM feed_sources WHERE feed_id = :feedId GROUP BY feed_id", nativeQuery = true)
    String findSourceIdsByFeedId(@Param("feedId") int feedId);
}
