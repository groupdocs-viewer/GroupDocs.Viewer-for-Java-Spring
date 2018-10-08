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
    protected ResponseEntity<ExceptionEntity> handleTotalGroupDocsException(TotalGroupDocsException exception) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setMessage(exception.getMessage());
        if (logger.isDebugEnabled()) {
            exception.printStackTrace();
            exceptionEntity.setException(exception);
        }
        logger.error(exception.getCause() != null? exception.getCause().getLocalizedMessage() : exception.getMessage());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
