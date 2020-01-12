package sapproject.project.exceptions;

public class DiscountBelowMinimalPriceException extends RuntimeException {
    private static final long serialVersionUID = 7659694516030314893L;

    public DiscountBelowMinimalPriceException(String productName) {
        super("Set discount price below minimal of product: " + productName);
    }
}
