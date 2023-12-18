package com.aarontan.DailyUpdates.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Feed(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public long getUserId() {
        return this.user.getId();
    }
}
