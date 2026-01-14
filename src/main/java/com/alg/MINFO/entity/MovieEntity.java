package com.alg.MINFO.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MOVIE")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String movieName;

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    private String posterUrl;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String language;

    public List<theatreEntity> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<theatreEntity> theatres) {
        this.theatres = theatres;
    }

    @ManyToMany
    @JoinTable(
            name = "movie_theatre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "theatre_id")
    )
    private List<theatreEntity> theatres;
}
