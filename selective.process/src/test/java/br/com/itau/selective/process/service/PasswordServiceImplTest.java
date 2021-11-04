package br.com.itau.selective.process.service;

import br.com.itau.selective.process.commons.exception.PreconditionException;
import br.com.itau.selective.process.model.PasswordResponse;
import br.com.itau.selective.process.model.UserRequest;
import br.com.itau.selective.process.service.impl.PasswordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class PasswordServiceImplTest {

    @InjectMocks
    private PasswordServiceImpl passwordService;

    @Test
    public void shouldValidateWhenPasswordValidThenReturnTrue() {
        PasswordResponse passwordResponse = passwordService.validate(UserRequest.builder()
                .password("AbTp9!fok")
                .build());
        assertEquals(passwordResponse.getPasswordValid(), true);
    }

    @Test
    public void shouldValidateWhenPasswordSizeNotvalidThenThrowPreconditionException() {
        assertThrows(PreconditionException.class, () -> passwordService.validate(UserRequest.builder()
                .password("A")
                .build()));
    }

    @Test
    public void shouldValidateWhenPasswordNotHaveBiggerCharacterThenThrowPreconditionException() {
        assertThrows(PreconditionException.class, () -> passwordService.validate(UserRequest.builder()
                .password("abtp9!fok")
                .build()));
    }

    @Test
    public void shouldValidateWhenPasswordNotHaveLowerCharacterThenThrowPreconditionException() {
        assertThrows(PreconditionException.class, () -> passwordService.validate(UserRequest.builder()
                .password("ABTP9!FOK")
                .build()));
    }

    @Test
    public void shouldValidateWhenPasswordHaveSpaceThenThrowPreconditionException() {
        assertThrows(PreconditionException.class, () -> passwordService.validate(UserRequest.builder()
                .password("AbTp9 !fok")
                .build()));
    }

    @Test
    public void shouldValidateWhenPasswordHaveRepeatCharacterThenThrowPreconditionException() {
        assertThrows(PreconditionException.class, () -> passwordService.validate(UserRequest.builder()
                .password("AbTp9!fbok")
                .build()));
    }
}
