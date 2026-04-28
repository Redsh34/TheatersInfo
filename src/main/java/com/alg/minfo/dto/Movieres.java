package com.alg.minfo.dto;

import java.io.Serializable;
import java.util.List;

public class Movieres implements Serializable {

    private String movieName;
    private String posterUrl;
    private String language;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    private String releaseDate;
    private List<theatredto> theatredetails;


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
    public List<theatredto> getTheatredetails() {
        return theatredetails;
    }

    public void setTheatredetails(List<theatredto> theatredetails) {
        this.theatredetails = theatredetails;
    }


}

