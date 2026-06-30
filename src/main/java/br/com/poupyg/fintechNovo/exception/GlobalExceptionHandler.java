package br.com.poupyg.fintechNovo.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        // Montamos um JSON estruturado para o React entender o erro facilmente
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("erro", "Not Found");
        body.put("mensagem", ex.getMessage());

        // Retorna o JSON com o status 404
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
