package sapproject.project.exceptions;

import org.springframework.http.HttpStatus;

public class BackendException extends Exception{
    private HttpStatus httpStatus;

    public BackendException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
