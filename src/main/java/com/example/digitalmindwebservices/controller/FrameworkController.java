package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.*;
import com.example.digitalmindwebservices.service.IDigitalProfileService;
import com.example.digitalmindwebservices.service.IDeveloperService;
import com.example.digitalmindwebservices.service.IFrameworkService;
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
@RequestMapping("/api/v1/frameworks")
@Api(tags = "Frameworks", value = "Web Service RESTFul of Frameworks")
public class FrameworkController {
    private final IFrameworkService frameworkService;
    private final IDigitalProfileService digitalProfileService;
    private final IDeveloperService developerService;
    public FrameworkController(IFrameworkService frameworkService, IDigitalProfileService digitalProfileService, IDeveloperService developerService) {
        this.frameworkService = frameworkService;
        this.digitalProfileService = digitalProfileService;
        this.developerService = developerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Frameworks", notes = "Method to list all Frameworks")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Frameworks founds"),
            @ApiResponse(code = 404, message = "Frameworks Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Framework>> findAllFrameworks(){
        try {
            List<Framework> frameworks = frameworkService.getAll();
            if(frameworks.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(frameworks, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Framework by Id", notes = "Method for find a Framework by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Framework found by Id"),
            @ApiResponse(code = 404, message = "Framework Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Framework> findFrameworkById(@PathVariable("id") Long id){
        try {
            Optional<Framework> framework = frameworkService.getById(id);
            return framework.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Framework", notes = "Method for create a Framework")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Framework created"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Framework> insertFramework(@PathVariable("id")Long digitalProfileId , @Valid @RequestBody Framework framework) {
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                framework.setDigitalProfile(digitalProfile.get());
                Framework newFramework = frameworkService.save(framework);
                return ResponseEntity.status(HttpStatus.CREATED).body(newFramework);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Framework", notes = "Method for update a Framework")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Framework updated"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Framework> updateFramework(@PathVariable("id") Long id, @Valid @RequestBody Framework framework) {
        try {
            Optional<Framework> frameworkUpdate = frameworkService.getById(id);
            if(!frameworkUpdate.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            framework.setId(id);
            frameworkService.save(framework);
            return new ResponseEntity<>(framework, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Framework", notes = "Method for delete a Framework")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Framework deleted"),
            @ApiResponse(code = 404, message = "Framework Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Framework> deleteFramework(@PathVariable("id") Long id) {
        try {
            Optional<Framework> frameworkDelete = frameworkService.getById(id);
            if(!frameworkDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            frameworkService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/digitalProfile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Framework by Digital Profile Id", notes = "Method for find a Framework by Digital Profile id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Framework found by Digital Profile Id"),
            @ApiResponse(code = 404, message = "Framework Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Framework>> findFrameworkByDigitalProfileId(@PathVariable("id") Long digitalProfileId){
        try {
            Optional<DigitalProfile> digitalProfile = digitalProfileService.getById(digitalProfileId);
            if (!digitalProfile.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Framework> frameworks = frameworkService.findByDigitalProfileId(digitalProfileId);
                return new ResponseEntity<>(frameworks, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/developer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Framework by Developer Id", notes = "Method for find a Framework by Developer id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Framework found by Developer Id"),
            @ApiResponse(code = 404, message = "Framework Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Framework>> findFrameworkByDeveloperId(@PathVariable("id") Long developerId){
        try {
            Optional<Developer> developer = developerService.getById(developerId);
            if (!developer.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Framework> frameworks = frameworkService.findByDeveloperId(developerId);
                return new ResponseEntity<>(frameworks, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
