package sapproject.project.exceptions;

public class RegularLessThanMinimalPrice extends RuntimeException {
    private static final long serialVersionUID = -2474706508986975166L;

    public RegularLessThanMinimalPrice(String productName) {
        super("Regular price cannot be less than minimal of product: " + productName);
    }
}
