package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.services;

import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

//    public EmployeeDTO getEmployeeById(Long id) {
//         EmployeeEntity employeeEntity=employeeRepository.findById(id).orElse(null);
//         return modelMapper.map(employeeEntity, EmployeeDTO.class);
//    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
        return employeeRepository
                .findById(id)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities=employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity toSaveEntity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

//    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long id) {
//        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
////        employeeEntity.setId(null);
//        employeeEntity.setId(id);
//        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
//        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
//    }

    public EmployeeDTO upsertEmployeeById(EmployeeDTO dto, Long id) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElse(new EmployeeEntity());
        entity.setId(id);
        modelMapper.map(dto, entity);
        EmployeeEntity saved = employeeRepository.save(entity);
        return modelMapper.map(saved, EmployeeDTO.class);
    }

//    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO,Long id){
//        boolean exists=employeeRepository.existsById(id);
//        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
//        if(exists){
//            employeeEntity.setId(id);
//            EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
//            return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
//        }
//        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
//        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
//    }



//    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO,Long id){
//        boolean exists=isExistsByEmployeeId(id);
//        if(!exists) throw new ResourceNotFoundException("Employee not found with id:"+id);
//        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
//        employeeEntity.setId(id);
//        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
//        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
//    }


    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO,Long id){
        isExistsByEmployeeId(id);
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }


//    public boolean isExistsByEmployeeId(Long employeeId){
//        return employeeRepository.existsById(employeeId);
//    }

    public void isExistsByEmployeeId(Long employeeId){
        boolean exists=employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id:"+employeeId);
    }


//    public boolean deleteEmployeeById(Long id) {
//        boolean exists=isExistsByEmployeeId(id);
//        if(!exists) return false;
//        employeeRepository.deleteById(id);
//        return true;
//    }

//    public boolean deleteEmployeeById(Long id) {
//        boolean exists=isExistsByEmployeeId(id);
//        if(!exists) throw new ResourceNotFoundException("Employee not found with id:"+id);
//        employeeRepository.deleteById(id);
//        return true;
//    }

    public boolean deleteEmployeeById(Long id) {
        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }



//    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
//        boolean exists=isExistsByEmployeeId(employeeId);
//        if(!exists) return null;
//        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
//        updates.forEach((field,value)->{
//            Field fieldToBeUpdated=ReflectionUtils.findField(EmployeeEntity.class,field);
//            assert fieldToBeUpdated != null;
//            fieldToBeUpdated.setAccessible(true);
//            ReflectionUtils.makeAccessible(fieldToBeUpdated);
//            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
//        });
//        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
//
//    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated=ReflectionUtils.findField(EmployeeEntity.class,field);
//            assert fieldToBeUpdated != null;
//            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.makeAccessible(fieldToBeUpdated);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);

    }
}
