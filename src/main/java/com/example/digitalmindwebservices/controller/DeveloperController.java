package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Developer;
import com.example.digitalmindwebservices.service.IDeveloperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/developers")
@Api(tags = "Developers", value = "Web Service RESTFul of Developers")
public class DeveloperController {
    private final IDeveloperService developerService;
    public DeveloperController(IDeveloperService developerService) {
        this.developerService = developerService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Developers", notes = "Method to list all Developers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Developers founds"),
            @ApiResponse(code = 404, message = "Developers Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Developer>> findAllDevelopers(){
        try {
            List<Developer> developers = developerService.getAll();
            if(developers.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(developers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Developer by Id", notes = "Method for find a Developer by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Developer found by Id"),
            @ApiResponse(code = 404, message = "Developer Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Developer> findDeveloperById(@PathVariable("id") Long id){
        try {
            Optional<Developer> developer = developerService.getById(id);
            return developer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Developer", notes = "Method for create a Developer")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Developer created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Developer> createDeveloper(@Valid @RequestBody Developer developer){
        try {
            Developer developerCreate = developerService.save(developer);
            return new ResponseEntity<>(developerCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Developer", notes = "Method for update a Developer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Developer updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Developer> updateDeveloper(@PathVariable("id") Long id, @RequestBody Developer developer){
        try {
            Developer developerUpdate = developerService.save(developer);
            return new ResponseEntity<>(developerUpdate, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Developer", notes = "Method for delete a Developer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Developer deleted"),
            @ApiResponse(code = 404, message = "Developer Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Developer> deleteDeveloper(@PathVariable("id") Long id){
        try {
            Optional<Developer> developer = developerService.getById(id);
            if(developer.isPresent()){
                developerService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
