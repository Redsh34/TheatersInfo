package com.alg.minfo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ALGDB")
public class theatreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String theatreName;
    private String city;
    @Column(length = 500)
    private String maps;
    @ManyToMany(mappedBy = "theatres")
    private List<MovieEntity> movies;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTheatreName() {
        return theatreName;
    }
    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String theatreLocation) {
        this.city = theatreLocation;
    }
    public void setMovies(List<MovieEntity> movies){
        this.movies=movies;
    }
    public List<MovieEntity> getMovies(){
        return movies;
    }
    public String getMaps(){
        return maps;
    }
    public void setMaps(String maps){
        this.maps=maps;
    }

}
