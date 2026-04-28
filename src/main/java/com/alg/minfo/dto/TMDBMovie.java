package com.alg.minfo.dto;

import java.util.List;

public class TMDBMovie {

    private Long id;
    private String title;
    private String original_language;
    private String original_title;
    private String poster_path;
    private String release_date;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getOriginal_language() { return original_language; }
    public String getOriginal_title() { return original_title; }
    public String getPoster_path() { return poster_path; }
    public String getRelease_date() { return release_date; }
    private List<String> theatredetails;

    public void setTheatredetails(List<String> theatredetails) {
        this.theatredetails = theatredetails;
    }
    public List<String> getTheatredetails() {
        return theatredetails;
    }


    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setOriginal_language(String original_language) { this.original_language = original_language; }
    public void setOriginal_title(String original_title) { this.original_title = original_title; }
    public void setPoster_path(String poster_path) { this.poster_path = poster_path; }
    public void setRelease_date(String release_date) { this.release_date = release_date; }
}