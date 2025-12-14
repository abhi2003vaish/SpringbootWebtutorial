package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.advices;

import com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
//        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception){
//        ApiError apiError=ApiError.builder().status(HttpStatus.NOT_FOUND).message("Resource not found").build();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
//    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
//        ApiError apiError=ApiError.builder()
//                .status(HttpStatus.NOT_FOUND)
//                .message(exception.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
//    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }




//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleInternalServerError(Exception exception){
//        ApiError apiError=ApiError.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .message(exception.getMessage())
//                .build();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
//
//    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);

    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception){
//        List<String> errors=exception
//                .getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(error->error.getDefaultMessage())
//                .collect(Collectors.toList());
//        ApiError apiError=ApiError.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message(errors.toString())
//                .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
//    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException exception){
//        List<String> errors=exception
//                .getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(error->error.getDefaultMessage())
//                .collect(Collectors.toList());
//        ApiError apiError=ApiError.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message("Input validation failed")
//                .subErrors(errors)
//                .build();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception){
        List<String> errors=exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .subErrors(errors)
                .build();
        return buildErrorResponseEntity(apiError);
    }


    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }




}
