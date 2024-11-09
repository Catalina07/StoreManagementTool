package storeManagementTool.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storeManagementTool.Dtos.ProductEntityToDTO;
import storeManagementTool.Entities.Product;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ProductEntityToDTO entityToDTO(Product product){
        return modelMapper.map(product, ProductEntityToDTO.class);
    }


    public Product convertToEntity(ProductEntityToDTO productEntityToDTO){
        return modelMapper.map(productEntityToDTO, Product.class);
    }
}
