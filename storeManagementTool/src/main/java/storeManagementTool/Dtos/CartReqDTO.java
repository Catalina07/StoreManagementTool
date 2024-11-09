package storeManagementTool.Dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartReqDTO {

    private List<ProductEntityToDTO> products = new ArrayList<>();

    private float finalPrice;

    public CartReqDTO(){
        finalPrice = 1;
        products.add(new ProductEntityToDTO());
    }
}
