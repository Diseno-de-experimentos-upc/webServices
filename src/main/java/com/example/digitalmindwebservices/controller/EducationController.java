package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.service.IDigitalProfileService;
import com.example.digitalmindwebservices.service.IEducationService;
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
@RequestMapping("/api/v1/educations")
@CrossOrigin(origins = "*")
@Api(value = "Web Service RESTFul of Educations", tags = "Educations")
public class EducationController {

    private final IEducationService educationService;
    private final IDigitalProfileService digitalProfileService;

    public EducationController(IEducationService educationService, IDigitalProfileService digitalProfileService) {
        this.educationService = educationService;
        this.digitalProfileService = digitalProfileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Educations", notes = "Method to list all Educations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Educations founds"),
            @ApiResponse(code = 404, message = "Educations Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Education>> findAllEducations(){
        try {
            List<Education> educations = educationService.getAll();
            if (educations.size() > 0){
                return new ResponseEntity<>(educations, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Education by Id", notes = "Method for finding an Education by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Education found by Id"),
            @ApiResponse(code = 404, message = "Education Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Education> findEducationById(@PathVariable("id") Long id){
        try {
            Optional<Education> education = educationService.getById(id);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(education.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Education", notes = "Method for creating an Education")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Education created"),
            @ApiResponse(code = 404, message = "Education Not Created"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Education> insertEducation(@PathVariable("id")Long idDigitalProfile, @RequestBody Education education){
        try{
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(idDigitalProfile);
            if(!digitalProfile.isPresent())
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            else {
                education.setDigitalProfile(digitalProfile.get());
                Education newEducation = educationService.save(education);
                return ResponseEntity.status(HttpStatus.CREATED).body(newEducation);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Education by Digital Profile", notes = "Method for finding an Education by Digital Profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Education found by Digital Profile"),
            @ApiResponse(code = 404, message = "Education Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Education> findEducationByDigitalProfile(@PathVariable("id") Long id){
        try {
            Optional<Education> education = educationService.findByDigitalProfileId(id);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(education.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
