package com.alg.MINFO.controller;

import com.alg.MINFO.dto.theatredto;
import com.alg.MINFO.service.theatreservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

   @GetMapping("/getByName")
   public ResponseEntity<theatredto> getTheatreDetailsByName(@RequestParam String theatreName){
     theatredto res= ser.getTheatreDetailsByName(theatreName);
     return ResponseEntity.ok(res);
   }

   @DeleteMapping("/deleteByName")
    public ResponseEntity<String> deleteTheatreByName(@RequestParam String theatreName){
     String res= ser.deleteTheatreByName(theatreName);
     if(res.equals("THEATRE REMOVED FROM DB")){
         return ResponseEntity.ok(res);
     }
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
   }

   @PutMapping("/update")
   public ResponseEntity<String> updateEntity(@RequestParam String theatreName, @RequestBody theatredto dto){
        String res= ser.updateFullInfo(theatreName,dto);
        return ResponseEntity.ok(res);
   }
   @PutMapping("/updatefeilds")
  public ResponseEntity<String> patch(@RequestParam String theatreName,@RequestBody theatredto dto){
     String res= ser.patch(theatreName,dto);
     return ResponseEntity.ok(res);
  }

}
