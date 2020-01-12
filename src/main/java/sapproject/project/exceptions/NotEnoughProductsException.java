package sapproject.project.exceptions;

public class NotEnoughProductsException extends RuntimeException{
    private static final long serialVersionUID = -3942583180738015704L;

    public NotEnoughProductsException() {
        super("Not enough of product while making order!");
    }
}
