package com.aarontan.DailyUpdates.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sources")
@NoArgsConstructor
@Data
public class TopSource {
    @Id
    private String id;
    private String name;
    private String description;
    private String url;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private TopSource(TopSourcesBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.url = builder.url;
        this.category = builder.category;
        this.language = builder.language;
        this.country = builder.country;
    }

    public String getCategoryName() {
        return this.category.getName();
    }

    public String getLanguageCode() {
        return this.language.getLanguageCode();
    }

    public String getCountryCode() {
        return this.country.getCountryCode();
    }

    public static class TopSourcesBuilder {
        private String id;
        private String name;
        private String description;
        private String url;
        private Category category;
        private Language language;
        private Country country;

        public TopSourcesBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public TopSourcesBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TopSourcesBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TopSourcesBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public TopSourcesBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public TopSourcesBuilder setLanguage(Language language) {
            this.language = language;
            return this;
        }

        public TopSourcesBuilder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public TopSource build() {
            return new TopSource(this);
        }
    }
}
