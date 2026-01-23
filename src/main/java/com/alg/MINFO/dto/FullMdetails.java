package com.alg.MINFO.dto;

import java.util.List;

public class FullMdetails {

    private String movieName;
    private String posterUrl;
    private String language;
    private String plot;
    private List<String> theatredetails;


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getLanguage() {
        return language;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public List<String> getTheatredetails() {
        return theatredetails;
    }

    public void setTheatredetails(List<String> theatredetails) {
        this.theatredetails = theatredetails;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}

