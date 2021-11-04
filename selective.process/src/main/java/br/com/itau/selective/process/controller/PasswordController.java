package br.com.itau.selective.process.controller;

import br.com.itau.selective.process.model.PasswordResponse;
import br.com.itau.selective.process.model.UserRequest;
import br.com.itau.selective.process.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static br.com.itau.selective.process.commons.constant.ApplicationConstant.Password.V1.PASSWORD_VALIDATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping(path = PASSWORD_VALIDATE, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PasswordResponse> validatePassword(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(passwordService.validate(userRequest), HttpStatus.CREATED);
    }
}
