package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Certificate;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.service.ICertificateService;
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
@RequestMapping("/api/v1/certificates")
@Api(value = "Web Service RESTFul of Certificates", tags = "Certificates")
public class CertificateController {

    private final ICertificateService certificateService;
    private final IEducationService educationService;

    public CertificateController(ICertificateService certificateService, IEducationService educationService) {
        this.certificateService = certificateService;
        this.educationService = educationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Certificates", notes = "Method to list all Certificates")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Certificates founds"),
            @ApiResponse(code = 404, message = "Certificates Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Certificate>> findAllCertificates(){
        try {
            List<Certificate> certificates = certificateService.getAll();
            if (certificates.size() > 0){
                return new ResponseEntity<>(certificates, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Certificate by Id", notes = "Method for finding an Certificate by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Certificate found by Id"),
            @ApiResponse(code = 404, message = "Certificate Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Certificate> findCertificateById(@PathVariable("id") Long id){
        try {
            Optional<Certificate> certificate = certificateService.getById(id);
            if (!certificate.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                return new ResponseEntity<>(certificate.get(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Certificate", notes = "Method for creating a Certificate")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Certificate created"),
            @ApiResponse(code = 404, message = "Certificate Not Created"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Certificate> insertCertificate(@PathVariable("id")Long educationId, @RequestBody Certificate certificate){
        try {
            Optional<Education> education  = educationService.getById(educationId);
            if (!education.isPresent()){
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
            }
            else {
                certificate.setEducation(education.get());
                Certificate newCertificate = certificateService.save(certificate);
                return ResponseEntity.status(HttpStatus.CREATED).body(newCertificate);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
