package storeManagementTool.Dtos;

import lombok.Data;

@Data
public class ProductReqDTO {

    private String name;

    private String description;

    private float price;

    private boolean isAvailable;

    private Long quantity;

    public ProductReqDTO(){
        name="name";
        description="description";
        price=1;
        isAvailable=true;
        quantity=1L;
    }
}
