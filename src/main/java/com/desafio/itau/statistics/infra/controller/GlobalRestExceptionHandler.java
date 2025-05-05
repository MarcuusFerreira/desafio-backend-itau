package com.desafio.itau.statistics.infra.controller;

import com.desafio.itau.statistics.domain.constants.ErrorMessagesConstants;
import com.desafio.itau.statistics.infra.dto.response.ErrorResponseDTO;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.desafio.itau.statistics.domain.constants.ErrorCodeMessageConstants;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(ErrorMessagesConstants.UNKNOWN_VALIDATION.getValue());

        String[] parts = errorMessage.split(":", 2);
        String errorCode = parts[0];
        String message = (parts.length > 1) ? parts[1] : errorMessage;

        if (ErrorCodeMessageConstants.NON_NEGATIVE_VIOLATION.getValue().equals(errorCode) ||
            ErrorCodeMessageConstants.FUTURE_DATE_VIOLATION.getValue().equals(errorCode)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                message
            ));
        }
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        String errorMessage = exception.getCause() instanceof JsonParseException ?
                ErrorMessagesConstants.INVALID_JSON_FORMAT.getValue() :
                ErrorMessagesConstants.REQUEST_BODY_IS_MISSING.getValue();

        return ResponseEntity.badRequest().body(new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage
        ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        "Ocorreu um erro interno: " + exception.getMessage()
                ));
    }
}
