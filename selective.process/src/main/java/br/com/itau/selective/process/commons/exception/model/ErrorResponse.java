package br.com.itau.selective.process.commons.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private List<String> messages = new ArrayList<>();

    public void addError(String message){
        messages.add(message);
    }

    public boolean hasError(){
        return !messages.isEmpty();
    }
}
