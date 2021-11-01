package br.com.itau.selective.process.commons.handler;

import br.com.itau.selective.process.commons.exception.PreconditionException;
import br.com.itau.selective.process.commons.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PreconditionException.class)
    public final ResponseEntity<ErrorResponse> handlerPrecondition(PreconditionException ex) {
        return new ResponseEntity(ErrorResponse.builder()
                .messages(ex.getMessages())
                .build(), HttpStatus.PRECONDITION_FAILED);
    }

}
