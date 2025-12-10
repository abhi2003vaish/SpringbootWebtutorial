package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.repositories;

import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
//    List<EmployeeEntity> findByName(String name);
}
