package com.aarontan.DailyUpdates.repository;

import com.aarontan.DailyUpdates.models.TopSource;
import org.springframework.data.jpa.domain.Specification;

public class TopSourceSpecification {

    public static Specification<TopSource> withCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if (categoryName != null && !categoryName.isEmpty()) {
                return criteriaBuilder.equal(root.get("category").get("name"), categoryName);
            }
            return null;
        };
    }

    public static Specification<TopSource> withLanguageCode(String languageCode) {
        return (root, query, criteriaBuilder) -> {
            if (languageCode != null && !languageCode.isEmpty()) {
                return criteriaBuilder.equal(root.get("language").get("languageCode"), languageCode);
            }
            return null;
        };
    }

    public static Specification<TopSource> withCountryCode(String countryCode) {
        return (root, query, criteriaBuilder) -> {
            if (countryCode != null && !countryCode.isEmpty()) {
                return criteriaBuilder.equal(root.get("country").get("countryCode"), countryCode);
            }
            return null;
        };
    }
}