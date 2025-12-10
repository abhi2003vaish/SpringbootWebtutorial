package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="employeesCopy")
public class EmployeeControllerCopy {

    @GetMapping(path="/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId,"Abhishek","abhi.vaish2003@gmail.com",22, LocalDate.of(2025,3,28),true);
    }

    @GetMapping(path="/name/{employeeName}")
    public EmployeeDTO getEmployeeByIdd(@PathVariable(name="employeeName") String Name){
        return new EmployeeDTO(1011L,Name,"abhi.vaish2003@gmail.com",22, LocalDate.of(2025,3,28),true);
    }


    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "hi age "+age+" "+sortBy;
    }

    @GetMapping(path="/greeting/{Greet}")
    public String getGreeting(@PathVariable(name="Greet") String Message,
                              @RequestParam(required = false,name="InputName") String name){
        return "Hello "+Message+" "+name;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return  inputEmployee;
    }

    @PutMapping
    public String updateEmployeeById(){
        return "hello";
    }
}
