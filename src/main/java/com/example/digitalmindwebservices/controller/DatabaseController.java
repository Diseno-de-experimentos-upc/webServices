package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Database;
import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Framework;
import com.example.digitalmindwebservices.entities.Project;
import com.example.digitalmindwebservices.service.IDatabaseService;
import com.example.digitalmindwebservices.service.IDigitalProfileService;
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
@RestController
@RequestMapping("/api/v1/databases")
@Api(tags = "Databases", value = "Web Service RESTFul of Databases")
public class DatabaseController {
    private final IDatabaseService databaseService;
    private final IDigitalProfileService digitalProfileService;
    public DatabaseController(IDatabaseService databaseService, IDigitalProfileService digitalProfileService) {
        this.databaseService = databaseService;
        this.digitalProfileService = digitalProfileService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Databases", notes = "Method to list all Databases")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Databases founds"),
            @ApiResponse(code = 404, message = "Databases Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Database>> findAllDatabases(){
        try {
            List<Database> databases = databaseService.getAll();
            if(databases.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(databases, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Database by Id", notes = "Method for find a Database by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Database found by Id"),
            @ApiResponse(code = 404, message = "Database Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Database> findDatabaseById(@PathVariable("id") Long id){
        try {
            Optional<Database> database = databaseService.getById(id);
            return database.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping( value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Database", notes = "Method for create a new Database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Database created"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Database> insertDatabase(@PathVariable("id")Long digitalProfileId, @Valid @RequestBody Database database){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                database.setDigitalProfile(digitalProfile.get());
                Database newDatabase = databaseService.save(database);
                return ResponseEntity.status(HttpStatus.CREATED).body(newDatabase);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Database", notes = "Method for update a Database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Database updated"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Database> updateDatabase(@PathVariable("id") Long id, @Valid @RequestBody Database database){
        try {
            Optional<Database> currentDatabase = databaseService.getById(id);
            if(!currentDatabase.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            database.setId(id);
            databaseService.save(database);
            return new ResponseEntity<>(database, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Database", notes = "Method for delete a Database")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Database deleted"),
            @ApiResponse(code = 404, message = "Database Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Database> deleteDatabase(@PathVariable("id") Long id){
        try {
            Optional<Database> currentDatabase = databaseService.getById(id);
            if(!currentDatabase.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            databaseService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Databases by Digital Profile Id", notes = "Method for find Databases by Digital Profile id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Databases found by Digital Profile Id"),
            @ApiResponse(code = 404, message = "Databases Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Database>> findDatabasesByDigitalProfileId(@PathVariable("id") Long digitalProfileId){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Database> databases = databaseService.findByDigitalProfileId(digitalProfileId);
                return new ResponseEntity<>(databases, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
