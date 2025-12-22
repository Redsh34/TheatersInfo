package com.alg.MINFO.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
@JsonPropertyOrder({
        "theatreName",
        "city",
        "maps"
})
public class theatredto {
    private String theatreName;
    private String city;

    private String maps;

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getMaps(){
        return maps;
    }
    public void setMaps(String maps){
        this.maps=maps;
    }


}
