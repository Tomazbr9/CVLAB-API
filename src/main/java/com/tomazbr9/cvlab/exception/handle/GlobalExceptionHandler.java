package com.tomazbr9.cvlab.exception.handle;

import com.tomazbr9.cvlab.dto.exception.ErrorResponseDTO;
import com.tomazbr9.cvlab.modules.auth.exception.EmailAlreadyExistsException;
import com.tomazbr9.cvlab.modules.auth.exception.RoleNotFoundException;
import com.tomazbr9.cvlab.modules.users.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request){
        return buildErrorResponse(exception.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleRoleNotFoundException(RoleNotFoundException exception, HttpServletRequest request){
        return buildErrorResponse(exception.getMessage(), request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception, HttpServletRequest request){
        return buildErrorResponse(exception.getMessage(), request.getRequestURI(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception exception, HttpServletRequest request){
        logger.error("Erro não tratado: ", exception);
        return buildErrorResponse(exception.getMessage(), request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(String message, String request, HttpStatus status){
        ErrorResponseDTO error =  new ErrorResponseDTO(
                message,
                status.value(),
                request,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, status);
    }

}