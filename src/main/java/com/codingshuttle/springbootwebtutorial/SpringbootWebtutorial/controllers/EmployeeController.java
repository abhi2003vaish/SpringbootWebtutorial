package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path="/employees/getSecretMessage")
    public String getMySuperSecretMessage(){
        return "secret message: hjfghbf@#4546758fngn";
    }


    @GetMapping(path="/employees/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId,"Abhishek","abhi.vaish2003@gmail.com",22, LocalDate.of(2025,3,28),true);
    }

//  It doesn’t matter whether you name it {employeeId} or {employeeName} — to Spring MVC, both are just wildcards for one path segment.
//  So Spring can’t distinguish them → Ambiguous mapping exception at startup (or strange runtime errors if somehow loaded).
//    If you want two separate endpoints (one by ID, one by name), you must make the paths different:
//    so we just change the below path with "/employees/name/{employeeName}"
//    if we have to run in chrome -->  /employees/name/John
    @GetMapping(path="/employees/name/{employeeName}")
    public EmployeeDTO getEmployeeByIdd(@PathVariable(name="employeeName") String Name){
        return new EmployeeDTO(1011L,Name,"abhi.vaish2003@gmail.com",22, LocalDate.of(2025,3,28),true);
    }


    @GetMapping(path="/employees")
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "hi age "+age+" "+sortBy;
    }

    @GetMapping(path="/employees/greeting/{Greet}")
    public String getGreeting(@PathVariable(name="Greet") String Message,
                              @RequestParam(required = false,name="InputName") String name){
        return "Hello "+Message+" "+name;
    }

    @PostMapping(path="/employees")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return  inputEmployee;
    }

    @PutMapping(path="/employees  ")
    public String updateEmployeeById(){
        return "hello";
    }
}
