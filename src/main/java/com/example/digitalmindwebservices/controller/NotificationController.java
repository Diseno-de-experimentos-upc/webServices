package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Notification;
import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.service.INotificationService;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users/{userId}/notifications")
@Api(tags = "Notifications", value = "Web Service RESTFul of Notifications")
public class NotificationController {

        private final INotificationService notificationService;
        private final IUserService userService;

        public NotificationController(INotificationService notificationService, IUserService userService) {
            this.notificationService = notificationService;
            this.userService = userService;
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "List all Notifications", notes = "Method to list all Notifications")
        @ApiResponses({
                @ApiResponse(code = 200, message = "All Notifications founds"),
                @ApiResponse(code = 404, message = "Notifications Not Found"),
                @ApiResponse(code = 501, message = "Internal Server Error")
        })
        public ResponseEntity<List<Notification>> findAllNotifications(@PathVariable("userId")Long userId){
            try {
                List<Notification> notifications = notificationService.getAll();
                Predicate<Notification> byId = notification -> notification.getUser().getId() == userId;
                notifications = notifications.stream().filter(byId).collect(Collectors.toList());
                if(notifications.size()>0)
                    return new ResponseEntity<>(notifications, HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Search Notification by Id", notes = "Method for find a Notification by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Notification found by Id"),
                @ApiResponse(code = 404, message = "Notification Not Found"),
                @ApiResponse(code = 501, message = "Internal Server Error")
        })
        public ResponseEntity<Notification> findNotificationById(@PathVariable("userId")Long userId, @PathVariable("id")Long id){
            try {
                Optional<Notification> notification = notificationService.getById(id);
                if(notification.isPresent() && notification.get().getUser().getId() == userId)
                    return new ResponseEntity<>(notification.get(), HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Create Notification", notes = "Method for create a Notification")
        @ApiResponses({
                @ApiResponse(code = 201, message = "Notification created"),
                @ApiResponse(code = 400, message = "Invalid Request"),
                @ApiResponse(code = 501, message = "Internal Server Error")
        })
        public ResponseEntity<Notification> createNotification(@PathVariable("userId")Long userId, @Valid @RequestBody Notification notification){
            try {
                Optional<User> user = userService.getById(userId);
                if(user.isPresent()){
                    notification.setUser(user.get());
                    Notification notificationCreate = notificationService.save(notification);
                    return new ResponseEntity<>(notificationCreate, HttpStatus.CREATED);
                }else
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Update Notification", notes = "Method for update a Notification")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Notification updated"),
                @ApiResponse(code = 400, message = "Invalid Request"),
                @ApiResponse(code = 501, message = "Internal Server Error")
        })
    public ResponseEntity<Notification> updateNotification(@PathVariable("userId")Long userId, @Valid @RequestBody Notification notification){
            try {
                Optional<User> user = userService.getById(userId);
                if(user.isPresent()){
                    Optional<Notification> notificationUpdate = notificationService.getById(notification.getId());
                    if(notificationUpdate.isPresent() && notificationUpdate.get().getUser().getId() == userId){
                        notification.setUser(user.get());
                        notificationService.save(notification);
                        return new ResponseEntity<>(notification, HttpStatus.OK);
                    }else
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }else
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Delete Notification", notes = "Method for delete a Notification")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Notification deleted"),
                @ApiResponse(code = 404, message = "Notification Not Found"),
                @ApiResponse(code = 501, message = "Internal Server Error")
        })
    public ResponseEntity<Notification> deleteNotification(@PathVariable("userId")Long userId, @PathVariable("id")Long id){
            try {
                Optional<Notification> notificationDelete = notificationService.getById(id);
                if(notificationDelete.isPresent() && notificationDelete.get().getUser().getId() == userId){
                    notificationService.delete(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }else
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
