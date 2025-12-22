package com.alg.MINFO.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "theatreInfo")
public class theatreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String theatreName;
    private String city;
    @Column(length = 500)
    private String maps;
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
    public String getMaps(){
        return maps;
    }
    public void setMaps(String maps){
        this.maps=maps;
    }

}
