package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.controllers;


import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/EmployeeControllerServiceLayer")
public class EmployeeControllerServiceLayer {

    private final EmployeeService employeeService;

    public EmployeeControllerServiceLayer(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }


//    @GetMapping(path="/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
//        return employeeService.getEmployeeById(id);
//    }

    @GetMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping
//    public List<EmployeeDTO> getAllEmployees(){
//        return employeeService.getAllEmployees();
//    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        return employeeService.createNewEmployee(inputEmployee);
//    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee= employeeService.createNewEmployee(inputEmployee);
//        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);

    }

//    @PutMapping(path="/{employeeTd}")
//    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,@PathVariable(name="employeeTd") Long id){
//        return employeeService.updateEmployeeById(employeeDTO,id);
//    }

    @PutMapping(path="/{employeeTd}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,@PathVariable(name="employeeTd") Long id){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO,id));
    }

    @PutMapping(path="/upsert/{employeeId}")
    public EmployeeDTO upsertEmployeeById(@RequestBody EmployeeDTO dto,@PathVariable(name="employeeId") Long id) {
        return employeeService.upsertEmployeeById(dto, id);
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name="employeeId") Long id){
        boolean gotDeleted= employeeService.deleteEmployeeById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }



}
