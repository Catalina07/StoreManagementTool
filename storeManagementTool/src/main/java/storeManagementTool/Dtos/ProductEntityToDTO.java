package storeManagementTool.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductEntityToDTO {

    private Long id;

    private String name;

    private String description;

    public ProductEntityToDTO(Long id, String name, String description, float price, boolean isAvailable, Long quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.quantity = quantity;
    }

    private float price;

    private boolean isAvailable;

    private Long quantity;

    public ProductEntityToDTO() {

    }
}
