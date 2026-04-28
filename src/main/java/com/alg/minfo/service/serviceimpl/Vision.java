package com.alg.minfo.service.serviceimpl;

import com.alg.minfo.dto.MovieDTO;
import com.alg.minfo.dto.TMDBMovie;
import com.alg.minfo.dto.TMDBSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class Vision {

    private final WebClient webClient;

    public Vision(WebClient webClient) {
        this.webClient = webClient;
    }

    public TMDBMovie searchMovie(String movieName, String language) {

        TMDBSearchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", movieName)
                        .queryParam("include_adult", false)
                        .queryParam("language", language)
                        .queryParam("page", 1)
                        .build())
                .retrieve()
                .bodyToMono(TMDBSearchResponse.class)
                .block();

        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            throw new RuntimeException("No movies found");
        }

        String inputName = movieName.trim().toLowerCase();

        TMDBMovie matchedMovie = response.getResults().stream()
                .filter(m -> m.getTitle() != null &&
                        m.getTitle().toLowerCase().contains(inputName))
                .findFirst()
                .orElse(response.getResults().get(0)); // fallback

        return matchedMovie;
    }
}