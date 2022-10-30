package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAllUsers(){
        try {
            List<User> users = userService.getAll();
            if(users.size()>0)
                return new ResponseEntity<>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserById(@PathVariable("id")Long id){
        try {
            Optional<User> user = userService.getById(id);
            if(!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findByEmail (@PathVariable ("email") String email){
        try {
            User user = userService.findByEmail(email);
            if(user == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findByFirstName (@PathVariable ("firstName") String firstName){
        try {
            List<User> users = userService.findByFirstName(firstName);
            if(users.size() == 0)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> insertUser(@Valid @RequestBody User user){
        try{
            User newUser = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user){
        try {
            Optional<User> currentUser = userService.getById(id);
            if(!currentUser.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            user.setId(id);
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        try {
            Optional<User> user = userService.getById(id);
            if(!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
