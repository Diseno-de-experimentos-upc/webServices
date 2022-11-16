package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.SocialNetwork;
import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.service.ISocialNetworkService;
import com.example.digitalmindwebservices.service.IUserService;

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
@RequestMapping("/api/v1/socialNetworks")
@CrossOrigin(origins = "*")
@Api(tags = "SocialNetworks", value = "Web Service RESTFul of Social Networks")
public class SocialNetworkController {

    private final ISocialNetworkService socialNetworkService;
    private final IUserService userService;

    public SocialNetworkController(ISocialNetworkService socialNetworkService, IUserService userService) {
        this.socialNetworkService = socialNetworkService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Social Networks", notes = "Method to list all Social Networks")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Social Networks founds"),
            @ApiResponse(code = 404, message = "Social Networks Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<SocialNetwork>> findAllSocialNetworks(){
        try {
            List<SocialNetwork> socialNetworks = socialNetworkService.getAll();
            if(socialNetworks.size()>0)
                return new ResponseEntity<>(socialNetworks, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Social Network by Id", notes = "Method for find a Social Network by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Social Network found by Id"),
            @ApiResponse(code = 404, message = "Social Network Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<SocialNetwork> findSocialNetworkById(@PathVariable("id") Long id){
        try {
            Optional<SocialNetwork> socialNetwork = socialNetworkService.getById(id);
            if(!socialNetwork.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(socialNetwork.get(), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByName", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List social Network by name", notes = "Method to list social Network filtered by name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Social Network founds by name"),
            @ApiResponse(code = 404, message = "Social Network Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<SocialNetwork>> findSocialNetworkByName(
            @RequestParam("name_social_network") String name_social_network){
        try {
            List<SocialNetwork> socialNetworks = socialNetworkService.findSocialNetworkByName(name_social_network);
            if(socialNetworks.size()>0)
                return new ResponseEntity<>(socialNetworks, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //insert a new social network by user id
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Insert a new Social Network", notes = "Method to insert a new Social Network")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Social Network created"),
            @ApiResponse(code = 404, message = "Social Network Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<SocialNetwork> insertSocialNetwork(@PathVariable("id") Long id, @Valid @RequestBody SocialNetwork socialNetwork){
        try {
            Optional<User> user = userService.getById(id);
            if(!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else{
                socialNetwork.setUser(user.get());
                SocialNetwork socialNetworkNew = socialNetworkService.save(socialNetwork);
                return ResponseEntity.status(HttpStatus.CREATED).body(socialNetworkNew);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //find social networks by user id
    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List social Network by user id", notes = "Method to list social Network filtered by user id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Social Network founds by user id"),
            @ApiResponse(code = 404, message = "Social Network Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<SocialNetwork>> findSocialNetworkByUserId(@PathVariable("id") Long id){
        try {
            Optional<User> user = userService.getById(id);
            if(!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else{
                List<SocialNetwork> socialNetworks = socialNetworkService.findByUserId(id);
                if(socialNetworks.size()>0)
                    return new ResponseEntity<>(socialNetworks, HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Data of Social Network", notes = "Method to update a Social Network")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Data of Social Network  successfully updated"),
            @ApiResponse(code = 404, message = "Social Network Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<SocialNetwork> updateSocialNetwork(@PathVariable("id") Long idSocialNetwork, @Valid @RequestBody SocialNetwork socialNetwork){
        try {
            Optional<SocialNetwork> socialNetworkOld = socialNetworkService.getById(idSocialNetwork);
            if(!socialNetworkOld.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                socialNetwork.setId(idSocialNetwork);
                socialNetworkService.save(socialNetwork);
                return new ResponseEntity<>(socialNetwork, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Social Network", notes = "Method to delete a Social Network")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Social Network successfully deleted"),
            @ApiResponse(code = 404, message = "Social Network Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<SocialNetwork> deleteSocialNetwork(@PathVariable("id") Long id){
        try {
            Optional<SocialNetwork> socialNetwork = socialNetworkService.getById(id);
            if(!socialNetwork.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                socialNetworkService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
