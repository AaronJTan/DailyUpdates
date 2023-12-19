package com.aarontan.DailyUpdates.service;

import com.aarontan.DailyUpdates.models.Category;
import com.aarontan.DailyUpdates.models.Country;
import com.aarontan.DailyUpdates.models.Language;
import com.aarontan.DailyUpdates.models.TopSource;
import com.aarontan.DailyUpdates.payload.response.NewsAPIorg.SourceResponse;
import com.aarontan.DailyUpdates.pojos.news.NewsAPIorg.SourceAPIModel;
import com.aarontan.DailyUpdates.repository.CategoryRepository;
import com.aarontan.DailyUpdates.repository.CountryRepository;
import com.aarontan.DailyUpdates.repository.LanguageRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SourceMapper {
    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;
    private final CountryRepository countryRepository;

    public SourceMapper(CategoryRepository categoryRepository, LanguageRepository languageRepository,
                        CountryRepository countryRepository) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
        this.countryRepository = countryRepository;
    }

    public TopSource convertToTopSource(SourceAPIModel sourceAPIModel) {
        Category category = categoryRepository.findByName(sourceAPIModel.getCategory())
                .orElseGet(() -> categoryRepository.save(new Category(sourceAPIModel.getCategory())));

        Language language = languageRepository.findByLanguageCode(sourceAPIModel.getLanguage())
                .orElseGet(() -> languageRepository.save(new Language(sourceAPIModel.getLanguage())));

        Country country = countryRepository.findByCountryCode(sourceAPIModel.getCountry())
                .orElseGet(() -> countryRepository.save(new Country(sourceAPIModel.getCountry())));

        return new TopSource.TopSourcesBuilder()
                .setId(sourceAPIModel.getId())
                .setName(sourceAPIModel.getName())
                .setDescription(sourceAPIModel.getDescription())
                .setUrl(sourceAPIModel.getUrl())
                .setCategory(category)
                .setLanguage(language)
                .setCountry(country)
                .build();
    }

    public SourceAPIModel convertToSource(TopSource topSource) {
        SourceAPIModel sourceAPIModel = new SourceAPIModel();
        sourceAPIModel.setId(topSource.getId());
        sourceAPIModel.setName(topSource.getName());
        sourceAPIModel.setDescription(topSource.getDescription());
        sourceAPIModel.setUrl(topSource.getUrl());
        sourceAPIModel.setCategory(topSource.getCategoryName());
        sourceAPIModel.setLanguage(topSource.getLanguageCode());
        sourceAPIModel.setCountry(topSource.getCountryCode());

        return sourceAPIModel;
    }

    public SourceResponse convertToSourceResponse(List<TopSource> topSources) {
        SourceResponse sourceResponse = new SourceResponse();
        sourceResponse.setStatus("ok");

        List<SourceAPIModel> sourceAPIModelList = new ArrayList<>();
        for (TopSource topSource: topSources) {
            SourceAPIModel sourceAPIModel = convertToSource(topSource);
            sourceAPIModelList.add(sourceAPIModel);
        }

        sourceResponse.setSourceAPIModels(sourceAPIModelList);
        return sourceResponse;

    }
}
