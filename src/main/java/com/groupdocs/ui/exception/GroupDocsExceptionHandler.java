package com.groupdocs.ui.exception;

import com.groupdocs.ui.model.response.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GroupDocsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TotalGroupDocsException.class)
    protected ResponseEntity<ExceptionEntity> handleTotalGroupDocsException(TotalGroupDocsException ex) {
        ExceptionEntity exceptionEntity = new ExceptionEntity(ex.getMessage());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
