package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.dto;

import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.annotations.EmployeeRoleValidation;
import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.annotations.ValidAge;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {

    private Long id;
    @NotBlank(message = "Name of the employee cannot be empty")
    @Size(min=3 ,max=20, message="Number of characters in name should be in the range:[3,20]")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message= "Email should be a valid email")
    private String email;

    @NotNull(message="Age cannot be null")
//    @Max(value=65, message="Age should not be greater than 80")
//    @Min(value=18, message="Age should not be less than 18")
    @ValidAge      //Custom Annotation for age validation
    private Integer age;

    @NotNull(message="Role cannot be null")
//    @Pattern(regexp= "^(ADMIN|USER)$", message="Role must be either ADMIN or USER")
    @EmployeeRoleValidation
    private String role;  //ADMIN , USER        regexp="^(ADMIN|USER)$"     or   regexp= "ADMIN|USER"

    @NotNull(message="Salary cannot be null")
    @Positive(message="Salary of employee should be positive")
    @Digits(integer=7,fraction=2,message="The salary can be in the form XXXXXXX.XX")
    @DecimalMax(value="1000000.98", message="Salary should not exceed 1,000,000.98")
    @DecimalMin(value="30000.00", message="Salary should not be less than 30,000.00")
    private Double salary;

    @PastOrPresent(message="Date of joining cannot be in the future")
    private LocalDate dateOfJoining;

    @JsonProperty("active")
    private Boolean isActive;

    @AssertTrue(message="Employee must be happy so this isHappy field can't be false ..it will always be true")
    private Boolean isHappy;

    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
}
