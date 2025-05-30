package br.com.esteroliver.files.exceptions.filesExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends Exception {
    public FileNotFoundException(String message) {
        super(message);
    }
    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
