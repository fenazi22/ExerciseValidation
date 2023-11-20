package com.example.eventsystemnew.Controller;

import com.example.eventsystemnew.Api.ApiEvent;
import com.example.eventsystemnew.Model.EvMod;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/even")
public class EvSys {
    List<EvMod> evModList = new ArrayList<>();


    @GetMapping("/get")
    public ResponseEntity getEvent() {
        //validation Area.
        return ResponseEntity.status(HttpStatus.OK).body(evModList);
    }


    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody EvMod addEvent) {
        //validation Area.

        evModList.add(addEvent);
        return ResponseEntity.status(HttpStatus.OK).body(evModList);
    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateEven(@PathVariable int index, @Valid @RequestBody EvMod updEven, Errors errors) {
        //validation Area.
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            ResponseEntity.status(HttpStatus.OK).body(message);
        }

        evModList.set(index, updEven);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiEvent("Successfully update"));
    }


    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleEvent(@PathVariable int index) {
        //validation Area.


        evModList.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiEvent("Successfully Deleted"));
    }


    @PutMapping("/change/index/{cap}")
    public ResponseEntity changeCapacity(@PathVariable int index, @PathVariable int cap) {


        EvMod changes = evModList.get(index);
        if (changes.getCapacity() == cap) {
            changes.setCapacity(cap);
            evModList.set(index, changes);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiEvent("Successfully change"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiEvent("Sorry I can't change!!"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity searchById(@PathVariable String id){
        List<EvMod>shearched=new ArrayList<>();
        for (EvMod searchId:evModList) {
            if (searchId.getId().equals(id))
                shearched.add(searchId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(evModList);
    }





}


