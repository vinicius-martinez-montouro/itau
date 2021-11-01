package br.com.itau.selective.process.service;

import br.com.itau.selective.process.model.PasswordResponse;
import br.com.itau.selective.process.model.UserRequest;

public interface PasswordService {

    public PasswordResponse validate(UserRequest userRequest);
}
