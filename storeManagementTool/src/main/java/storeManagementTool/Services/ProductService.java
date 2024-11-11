package storeManagementTool.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storeManagementTool.Dtos.ProductEntityToDTO;
import storeManagementTool.Dtos.ProductReqDTO;
import storeManagementTool.Entities.Cart;
import storeManagementTool.Entities.Product;
import storeManagementTool.Mappers.ProductMapper;
import storeManagementTool.Repositories.CartRepository;
import storeManagementTool.Repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductMapper productMapper;

    public ProductReqDTO getReq(){
        return new ProductReqDTO();
    }

    public List<ProductEntityToDTO> getProducts(){
        return productRepository.findAll().stream()
                .map(productMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public ProductEntityToDTO getProductById(long id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return productMapper.entityToDTO(product.get());
        }
        else {
            // TO DO add exceptions
        }
        return null;
    }

    public void deleteProductById(long id){
        productRepository.deleteById(id);
    }

    public ProductEntityToDTO createProduct(ProductReqDTO productReqDTO){
        Product product = new Product();

        product.setName(productReqDTO.getName());
        product.setDescription(productReqDTO.getDescription());
        product.setAvailable(productReqDTO.isAvailable());
        product.setPrice(productReqDTO.getPrice());
        product.setQuantity(productReqDTO.getQuantity());

        productRepository.save(product);

        return productMapper.entityToDTO(product);
    }

}
