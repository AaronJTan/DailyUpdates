package com.aarontan.DailyUpdates.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "feeds")
@NoArgsConstructor
@Data
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(
            name="feed_sources",
            joinColumns = @JoinColumn(name = "feed_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id")
    )
    private List<TopSource> sources;

    public Feed(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @JsonIgnore
    public long getUserId() {
        return this.user.getId();
    }
}
