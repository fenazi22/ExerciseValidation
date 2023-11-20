package com.example.trackersystemnew.Controller;


import com.example.trackersystemnew.ApiTrack.ApiTrack;
import com.example.trackersystemnew.Model.TrackMod;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;







@RestController
@RequestMapping("/api/v1/tracks")
public class TrackController {
    ArrayList<TrackMod> trackMods = new ArrayList<>();



    @GetMapping("/get")
    public ResponseEntity geTrack() {

        return ResponseEntity.status(HttpStatus.OK).body(trackMods);
    }


    @PostMapping("/add")
    public ResponseEntity AddTrack(@Valid @RequestBody TrackMod treack, Errors errors) {
        //Validate Area.
        if (errors.hasErrors()){
        String message=errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }


        trackMods.add(treack);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiTrack("Successfully Add User"));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity Update(@PathVariable int index,@Valid @RequestBody TrackMod update,Errors errors) {
        //Validate Area.
    if (errors.hasErrors()){
        String message=errors.getFieldError().getDefaultMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }


        trackMods.set(index, update);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiTrack("Successfully Update"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteTrac(@PathVariable int index) {

        trackMods.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Delete");
    }




    @PutMapping("/change/{index}")
    public ResponseEntity change(@PathVariable int index) {


        TrackMod track=trackMods.get(index);
        if (track.getStatus().equalsIgnoreCase("Not Started")){
          track.setStatus("In Progress");
            trackMods.set(index,track);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiTrack("Successfully change to In Progress"));

        }else if(track.getStatus().equalsIgnoreCase("In Progress")){
            track.setStatus("Not Started");
            trackMods.set(index, track);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiTrack("Successfully change Not Started"));
        }else if(track.getStatus().equalsIgnoreCase("Completed")){
            track.setStatus("Not Started");
            trackMods.set(index, track);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiTrack("Successfully change to Not Started"));


        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiTrack("Successfully change to In Progress"));

        }
    }



    @GetMapping("/search/{titles}")
public  ResponseEntity SearchTrack(@PathVariable String titles){

        for (TrackMod search : trackMods) {
            if (search.getTitle().equalsIgnoreCase(titles))
                return ResponseEntity.status(HttpStatus.OK).body(search);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiTrack("\"Sorry not Found!!\""));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not found");
    }



    @GetMapping("/com/{comName}")
    public ResponseEntity displayProjects(@PathVariable String comName) {
        List<TrackMod> matchingProjects = new ArrayList<>();

        for (TrackMod project : trackMods) {
            if (project.getCompanyName().equalsIgnoreCase(comName)) {
                matchingProjects.add(project);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(matchingProjects);
    }



}
