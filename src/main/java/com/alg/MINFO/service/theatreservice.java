package com.alg.MINFO.service;

import com.alg.MINFO.dto.theatredto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface theatreservice {
    public String save(theatredto dto);
    public List<theatredto> retrive();
}
