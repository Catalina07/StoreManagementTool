package storeManagementTool.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product not found!");
    }
}
