package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Message;
import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.service.IMessageService;
import com.example.digitalmindwebservices.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users/{userId}/messages")
@Api(tags = "Messages", value = "Web Service RESTFul of Messages")
public class MessageController {

    private final IMessageService messageService;
    private final IUserService userService;

    public MessageController(IMessageService messageService, IUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Messages", notes = "Method to list all Messages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Messages founds"),
            @ApiResponse(code = 404, message = "Messages Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Message>> findAllMessages(@PathVariable("userId")Long userId){
        try {
            List<Message> messages = messageService.getAll();
            Predicate<Message> byId = message -> message.getEmitter().getId() == userId;
            messages = messages.stream().filter(byId).collect(Collectors.toList());
            if(messages.size()>0)
                return new ResponseEntity<>(messages, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Message by Id", notes = "Method for find a Message by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Message found by Id"),
            @ApiResponse(code = 404, message = "Message Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> findMessageById(@PathVariable("id")Long id){
        try {
            Optional<Message> message = messageService.getById(id);
            if(!message.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Message", notes = "Method for create a Message")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Message created"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> insertMessage(@Valid @RequestBody Message message, @PathVariable(value = "userId") Long userId){
        Optional<User> emitter = userService.getById(userId);
        message.setEmitter(emitter.get());
        try{
            Message newMessage = messageService.save(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(newMessage);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SneakyThrows
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Message", notes = "Method for update a Message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Message updated"),
            @ApiResponse(code = 400, message = "Invalid Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> updateMessage(@PathVariable("id") Long id, @Valid @RequestBody Message message, @PathVariable(value = "userId") Long userId){
        Optional<User> emitter = userService.getById(userId);
        message.setEmitter(emitter.get());
        try {
            Optional<Message> currentMessage = messageService.getById(id);
            if(!currentMessage.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            message.setId(id);
            messageService.save(message);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Message", notes = "Method for delete a Message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Message deleted"),
            @ApiResponse(code = 404, message = "Message Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Message> deleteMessage(@PathVariable("id") Long id){
        try {
            Optional<Message> message = messageService.getById(id);
            if(!message.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            messageService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
