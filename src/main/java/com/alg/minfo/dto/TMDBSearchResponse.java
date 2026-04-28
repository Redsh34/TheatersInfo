package com.alg.minfo.dto;

import java.util.List;

public class TMDBSearchResponse {
    private List<TMDBMovie> results;

    public List<TMDBMovie> getResults() {
        return results;
    }

    public void setResults(List<TMDBMovie> results) {
        this.results = results;
    }
}