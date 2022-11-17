package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Developer;
import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.service.IDeveloperService;
import com.example.digitalmindwebservices.service.IDigitalProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/digital_profiles")
@Api(value = "Web Service RESTFul of Digital Profiles", tags = "DigitalProfiles")
public class DigitalProfileController {

    private final IDigitalProfileService digitalProfileService;
    private final IDeveloperService developerService;

    public DigitalProfileController(IDigitalProfileService digitalProfileService, IDeveloperService developerService) {
        this.digitalProfileService = digitalProfileService;
        this.developerService = developerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Digital Profiles", notes = "Method to list all Digital Profiles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Digital Profiles founds"),
            @ApiResponse(code = 404, message = "Digital Profiles Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<DigitalProfile>> findAllDigitalProfiles(){
        try{
            List<DigitalProfile> digitalProfiles = digitalProfileService.getAll();
            if (digitalProfiles.size() > 0){
                return new ResponseEntity<>(digitalProfiles, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Digital Profile by Id", notes = "Method for finding a Digital Profile by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Digital Profile found by Id"),
            @ApiResponse(code = 404, message = "Digital Profile Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<DigitalProfile> findDigitalProfileById(@PathVariable("id") Long id){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(id);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(digitalProfile.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   //post method to create a new digital profile by developer id
    @PostMapping(value = "/{developer_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Digital Profile", notes = "Method for creating a new Digital Profile")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Digital Profile created successfully"),
            @ApiResponse(code = 404, message = "Digital Profile Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<DigitalProfile> createDigitalProfile(@PathVariable("developer_id") Long developerId, @RequestBody DigitalProfile digitalProfile){
        try {
            Optional<Developer> existingDeveloper = developerService.getById(developerId);
            if (!existingDeveloper.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                digitalProfile.setDeveloper(existingDeveloper.get());
                DigitalProfile newDigitalProfile = digitalProfileService.save(digitalProfile);
                return ResponseEntity.status(HttpStatus.CREATED).body(newDigitalProfile);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Digital Profile", notes = "Method for updating a Digital Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Digital Profile updated"),
            @ApiResponse(code = 404, message = "Digital Profile Not Updated"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<DigitalProfile> updateDigitalProfile(@PathVariable("id") Long id, @RequestBody DigitalProfile digitalProfile){
        try{
            Optional<DigitalProfile> digitalProfile1 = digitalProfileService.getById(id);
            if (!digitalProfile1.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                digitalProfile.setId(id);
                digitalProfileService.save(digitalProfile);
                return new ResponseEntity<>(digitalProfile, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Digital Profile", notes = "Method for deleting a Digital Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Digital Profile deleted"),
            @ApiResponse(code = 404, message = "Digital Profile Not Deleted"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<DigitalProfile> deleteDigitalProfile(@PathVariable("id") Long id){
        try{
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(id);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                digitalProfileService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/developer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Digital Profile by Developer Id", notes = "Method for finding a Digital Profile by Developer id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Digital Profile found by Developer Id"),
            @ApiResponse(code = 404, message = "Digital Profile Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<DigitalProfile> findDigitalProfileByDeveloperId(@PathVariable("id") Long id){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.findDigitalProfileByDeveloperId(id);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(digitalProfile.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
