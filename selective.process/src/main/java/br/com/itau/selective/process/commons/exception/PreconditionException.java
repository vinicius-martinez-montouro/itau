package br.com.itau.selective.process.commons.exception;

import java.util.ArrayList;
import java.util.List;

public class PreconditionException extends RuntimeException {
    private List<String> messages = new ArrayList<>();

    public PreconditionException() {
    }

    public PreconditionException(String message) {
        this.messages.add(message);
    }

    public PreconditionException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
