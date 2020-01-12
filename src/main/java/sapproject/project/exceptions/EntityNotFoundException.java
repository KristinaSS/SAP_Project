package sapproject.project.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3671800175824527587L;
    private static String MESSAGE = ": Entity not Found";
    private static HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(Class<?> eClass) {
        super(eClass + MESSAGE + HTTP_STATUS);
    }
}
