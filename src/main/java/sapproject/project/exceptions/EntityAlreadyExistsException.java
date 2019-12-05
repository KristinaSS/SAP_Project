package sapproject.project.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BackendException{

    private static String MESSAGE = "Entity already Exists";
    private static HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

    public EntityAlreadyExistsException(Class <?> eClass) {
            super(eClass.getName() + MESSAGE, HTTP_STATUS);
    }
}
