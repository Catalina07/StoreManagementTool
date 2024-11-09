package storeManagementTool.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class CartEntityToDTO {

    private List<ProductEntityToDTO> productDTOs;

    private float finalPrice;
}
