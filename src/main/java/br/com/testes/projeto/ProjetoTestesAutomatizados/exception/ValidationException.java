package br.com.testes.projeto.ProjetoTestesAutomatizados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    public ValidationException(String msg) {
        super(msg);
    }
}