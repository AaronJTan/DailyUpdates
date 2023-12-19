package com.aarontan.DailyUpdates.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "languages")
@NoArgsConstructor
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String languageCode;

    public Language(String languageCode) {
        this.languageCode = languageCode;
    }
}
