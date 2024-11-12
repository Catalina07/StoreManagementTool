package storeManagementTool.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
public class CartEntityToDTO {

    private List<ProductEntityToDTO> productDTOs;

    private float finalPrice;
}
