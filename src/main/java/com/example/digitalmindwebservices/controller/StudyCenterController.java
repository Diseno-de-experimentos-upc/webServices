package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.entities.StudyCenter;
import com.example.digitalmindwebservices.service.IEducationService;
import com.example.digitalmindwebservices.service.IStudyCenterService;
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
@RequestMapping("/api/v1/study-centers")
@Api(tags = "StudyCenters", value = "Web Service RESTFul of Study Centers")
public class StudyCenterController {

    private final IStudyCenterService studyCenterService;
    private final IEducationService educationService;
    public StudyCenterController(IStudyCenterService studyCenterService, IEducationService educationService) {
        this.studyCenterService = studyCenterService;
        this.educationService = educationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Study Centers", notes = "Method to list all Study Centers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Study Centers founds"),
            @ApiResponse(code = 404, message = "Study Centers Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<StudyCenter>> findAllStudyCenters(){
        try {
            List<StudyCenter> studyCenters = studyCenterService.getAll();
            if (studyCenters.size() > 0){
                return new ResponseEntity<>(studyCenters, HttpStatus.OK);
            }
            else {
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Study Center by Id", notes = "Method for finding an Study Center by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Study Center found by Id"),
            @ApiResponse(code = 404, message = "Study Center Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<StudyCenter> findStudyCenterById(@PathVariable("id") Long id){
        try {
            Optional<StudyCenter> studyCenter = studyCenterService.getById(id);
            if (!studyCenter.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(studyCenter.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Study Center", notes = "Method for creating an Study Center")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Study Center created"),
            @ApiResponse(code = 404, message = "Study Center Not Created"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<StudyCenter> insertStudyCenter(@PathVariable("id") Long educationId ,  @RequestBody StudyCenter studyCenter){
        try {
            Optional<Education> education = educationService.getById(educationId);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                studyCenter.setEducation(education.get());
                StudyCenter newStudyCenter = studyCenterService.save(studyCenter);
                return ResponseEntity.status(HttpStatus.CREATED).body(newStudyCenter);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/education/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Study Centers by Education Id", notes = "Method for finding an Study Centers by Education id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Study Centers found by Education Id"),
            @ApiResponse(code = 404, message = "Study Centers Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<StudyCenter>> findStudyCentersByEducationId(@PathVariable("id") Long educationId){
        try {
            Optional<Education> education = educationService.getById(educationId);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<StudyCenter> studyCenters = studyCenterService.findByEducationId(educationId);
                return new ResponseEntity<>(studyCenters, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
