//lecture->2.3  we connect this Controller with the EmployeeRepository
package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="employeesCopy1")
public class EmployeeControllerCopy1 {

    private final EmployeeRepository employeeRepository;

    public EmployeeControllerCopy1(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path="/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long Id){
        return employeeRepository.findById(Id).orElse(null);
    }

    @GetMapping(path="/name/{employeeName}")
    public EmployeeDTO getEmployeeByIdd(@PathVariable(name="employeeName") String Name){
        return new EmployeeDTO(1011L,Name,"abhi.vaish2003@gmail.com",22, LocalDate.of(2025,3,28),true);
    }


    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @GetMapping(path="/greeting/{Greet}")
    public String getGreeting(@PathVariable(name="Greet") String Message,
                              @RequestParam(required = false,name="InputName") String name){
        return "Hello "+Message+" "+name;
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return  employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById(){
        return "hello";
    }
}
