package br.com.apilistatarefas.handler;

import br.com.apilistatarefas.exceptions.ErrorResponse;
import br.com.apilistatarefas.exceptions.ExcecaoTarefaNaoEncontrada;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipulacaoExcecoesGlobais {

    @ExceptionHandler(ExcecaoTarefaNaoEncontrada.class)
    public ResponseEntity<ErrorResponse> handleExcecaoTarefaNaoEncontrada(ExcecaoTarefaNaoEncontrada ex) {
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
}
