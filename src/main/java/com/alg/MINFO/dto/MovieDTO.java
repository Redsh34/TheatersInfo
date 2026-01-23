package com.alg.MINFO.dto;

import java.util.List;

public class MovieDTO {
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

    public List<String> getTheatredetails() {
        return theatredetails;
    }

    public void setTheatredetails(List<String> theatredetails) {
        this.theatredetails = theatredetails;
    }

    private List<String> theatredetails;
}

