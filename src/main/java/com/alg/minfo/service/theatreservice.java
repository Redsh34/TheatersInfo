package com.alg.minfo.service;

import com.alg.minfo.dto.theatredto;

import java.util.List;


public interface theatreservice {
    public String save(theatredto dto);
    public List<theatredto> retrive();
    public theatredto getTheatreDetailsByName(String theatreName);
    public String deleteTheatreByName(String theatreName);
    public String updateFullInfo(String theatreName,theatredto dto);
    public String patch(String theatreName,theatredto dto);
}
