package com.alg.MINFO.service.serviceimpl;

import com.alg.MINFO.dto.theatredto;
import com.alg.MINFO.entity.theatreEntity;
import com.alg.MINFO.repo.theatrerepo;
import com.alg.MINFO.service.theatreservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class theatreserviceimpl implements theatreservice {


    @Autowired
    public theatrerepo repo;

    @Override
    public String save(theatredto dto) {
        theatreEntity obj= new theatreEntity();
        obj.setTheatreName(dto.getTheatreName());
        obj.setCity(dto.getCity());
        obj.setMaps(dto.getMaps());
        if(repo.existsByTheatreName(obj.getTheatreName())){
            return "Theatre already exists in the database";
        }
        theatreEntity res=repo.save(obj);
        if(res!=null){
            return "success";
        } else{
            return "unexpected error";
        }
    }

    @Override
    public List<theatredto> retrive() {
        List<theatreEntity> res=repo.getTheatreDetails();
        List<theatredto> dto= new ArrayList<>();
        if(res!=null) {
            for (int i = 0; i < res.size(); i++) {
                theatreEntity en = res.get(i);
                theatredto dt = new theatredto();
                dt.setTheatreName(en.getTheatreName());
                dt.setCity(en.getCity());
                dt.setMaps(en.getMaps());
                dto.add(dt);
            }
        }else{
            return null;
        }
        return dto;
    }

    @Override
    public theatredto getTheatreDetailsByName(String theatreName) {
       theatreEntity res= repo.getTheatreDetailsByName(theatreName);
       if(res!=null){
           theatredto dto= new theatredto();
           dto.setTheatreName(res.getTheatreName());
           dto.setCity(res.getCity());
           dto.setMaps(res.getMaps());
           return dto;
       }
       return null;
    }

    @Override
    public String deleteTheatreByName(String theatreName) {
        int res= repo.deleteTheatreByName(theatreName);
        if(res==1){
            return "THEATRE REMOVED FROM DB";
        }
        return "NO THEATRE FOUND WITH THIS NAME";

    }
}
