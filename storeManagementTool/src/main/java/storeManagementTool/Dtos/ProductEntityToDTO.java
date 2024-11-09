package storeManagementTool.Dtos;

import lombok.Data;

@Data
public class ProductEntityToDTO {

    private Long id;

    private String name;

    private String description;

    private float price;

    private boolean isAvailable;

    private Long quantity;

}
