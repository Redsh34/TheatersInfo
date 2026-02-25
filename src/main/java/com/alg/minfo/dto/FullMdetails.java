package com.alg.minfo.dto;

import java.util.List;

public class FullMdetails {

    private String movieName;
    private String language;
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
    public void setLanguage(String language) {
        this.language = language;
    }
    public List<String> getTheatredetails() {
        return theatredetails;
    }

    public void setTheatredetails(List<String> theatredetails) {
        this.theatredetails = theatredetails;
    }

}

