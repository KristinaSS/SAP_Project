package sapproject.project.exceptions;

public class PriceZeroOrLess extends RuntimeException{
    private static final long serialVersionUID = 5587662462228614068L;

    public PriceZeroOrLess(String productName) {
        super("Regular price cannot be less or equal to zero of product: " + productName);
    }
}
