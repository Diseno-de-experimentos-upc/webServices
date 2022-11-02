package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.DigitalProfile;
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

@RestController
@RequestMapping("/api/v1/digital_profiles")
@Api(value = "Web Service RESTFul of Digital Profiles", tags = "DigitalProfiles")
public class DigitalProfileController {

    private final IDigitalProfileService digitalProfileService;

    public DigitalProfileController(IDigitalProfileService digitalProfileService) {
        this.digitalProfileService = digitalProfileService;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Digital Profile", notes = "Method for creating a Digital Profile")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Digital Profile created"),
            @ApiResponse(code = 404, message = "Digital Profile Not Created"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<DigitalProfile> insertDigitalProfile(@RequestBody DigitalProfile digitalProfile){
        try{
            DigitalProfile digitalProfile1 = digitalProfileService.save(digitalProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(digitalProfile1);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
