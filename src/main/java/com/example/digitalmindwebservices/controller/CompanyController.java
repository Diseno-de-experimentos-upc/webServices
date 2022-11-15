package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Company;
import com.example.digitalmindwebservices.service.ICompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/companies")
@CrossOrigin(origins = "*")
@Api(tags = "Companies", value = "Web Service RESTFul of Companies")
public class CompanyController {

    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Companies", notes = "Method to list all Companies")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Companies founds"),
            @ApiResponse(code = 404, message = "Companies Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Company>> findAllCompanies(){
        try {
            List<Company> companies = companyService.getAll();
            if(companies.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(companies, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Company by Id", notes = "Method for find a Company by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Company found by Id"),
            @ApiResponse(code = 404, message = "Company Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Company> findCompanyById(@PathVariable("id") Long id){
        try {
            Optional<Company> company = companyService.getById(id);
            return company.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Company", notes = "Method for create a Company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Company created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company){
        try {
            Company companyCreate = companyService.save(company);
            return new ResponseEntity<>(companyCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Company", notes = "Method for update a Company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Company updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id, @Valid @RequestBody Company company){
        try {
            if(id.equals(company.getId())){
                Company companyUpdate = companyService.save(company);
                return new ResponseEntity<>(companyUpdate, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Company", notes = "Method for delete a Company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Company deleted"),
            @ApiResponse(code = 404, message = "Company Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id){
        try {
            Optional<Company> company = companyService.getById(id);
            if(company.isPresent()){
                companyService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
