package com.alg.MINFO.controller;

import com.alg.MINFO.dto.theatredto;
import com.alg.MINFO.service.theatreservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class theatrecontroller {

 @Autowired
 public theatreservice ser;

 @PostMapping("/save")
   public ResponseEntity<String> save(@RequestBody theatredto dto){
       String res= ser.save(dto);
       return ResponseEntity.ok(res);
   }

   @GetMapping("/getTheatreInfo")
    public ResponseEntity<List<theatredto>> getdetails(){
       List<theatredto> res= ser.retrive();
       return ResponseEntity.ok(res);
   }


}
