package com.alg.minfo.dto;

import java.io.Serializable;

public class MovieDTO  implements Serializable {
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    private String movieName;

    public String getLanguage() {
        return language;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    private String posterUrl;

    public void setLanguage(String language) {
        this.language = language;
    }

    private String language;
}

