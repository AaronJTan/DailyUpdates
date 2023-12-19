package com.aarontan.DailyUpdates.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@Data
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String countryCode;

    public Country(String countryCode) {
        this.countryCode = countryCode;
    }
}
