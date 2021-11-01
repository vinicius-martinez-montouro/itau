package br.com.itau.selective.process.service.impl;

import br.com.itau.selective.process.commons.exception.PreconditionException;
import br.com.itau.selective.process.commons.exception.model.ErrorResponse;
import br.com.itau.selective.process.model.PasswordResponse;
import br.com.itau.selective.process.model.UserRequest;
import br.com.itau.selective.process.service.PasswordService;
import org.springframework.stereotype.Service;

import static br.com.itau.selective.process.utils.PasswordUtils.*;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Override
    public PasswordResponse validate(UserRequest userRequest) {
        passwordValidate(userRequest.getPassword());
        return PasswordResponse.builder()
                .passwordValid(true)
                .build();
    }

    private void passwordValidate(String password) {
        ErrorResponse error = new ErrorResponse();
        validSize(password, error);
        validOneUpperCharacter(password, error);
        validOneLowerCharacter(password, error);
        validOneSpecialCharacter(password, error);
        validStringNotSpace(password, error);
        validCharacterRepeat(password, error);
        if (error.hasError())
            throw new PreconditionException(error.getMessages());
    }

    private void validSize(String value, ErrorResponse error) {
        if (isSizeBiggerThanOne(value))
            error.addError("A senha deve ter tamanho maior que 1 caracter");
    }

    private void validOneUpperCharacter(String value, ErrorResponse error) {
        if (!hasOneUpperCharacter(value))
            error.addError("A senha deve ter pelo menos uma letra maiúscula");
    }

    private void validOneLowerCharacter(String value, ErrorResponse error) {
        if (!hasOneLowerCharacter(value))
            error.addError("A senha deve ter pelo menos uma letra minúscula");
    }

    private void validOneSpecialCharacter(String value, ErrorResponse error) {
        if (!hasOneSpecialCharacter(value))
            error.addError("A senha deve ter pelo menos um caracter especial");
    }

    private void validStringNotSpace(String value, ErrorResponse error) {
        if (hasNotSpaceInString(value))
            error.addError("A senha não deve conter espaço");
    }

    private void validCharacterRepeat(String value, ErrorResponse error) {
        if (hasRepeatCharacter(value))
            error.addError("A senha não deve conter caracteres repetidos");
    }
}
