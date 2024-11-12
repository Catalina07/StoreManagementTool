package storeManagementTool.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storeManagementTool.Dtos.ProductEntityToDTO;
import storeManagementTool.Dtos.ProductReqDTO;
import storeManagementTool.Entities.Product;
import storeManagementTool.Exceptions.ProductNotFoundException;
import storeManagementTool.Mappers.ProductMapper;
import storeManagementTool.Repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public ProductReqDTO getReq() {
        return new ProductReqDTO();
    }

    public List<ProductEntityToDTO> getProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.isAvailable())
                .map(productMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public ProductEntityToDTO getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return productMapper.entityToDTO(product.get());
        }
        throw new ProductNotFoundException();
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public ProductEntityToDTO createProduct(ProductReqDTO productReqDTO) {
        Product product = new Product();

        product.setName(productReqDTO.getName());
        product.setDescription(productReqDTO.getDescription());
        product.setAvailable(productReqDTO.isAvailable());
        product.setPrice(productReqDTO.getPrice());
        product.setQuantity(productReqDTO.getQuantity());

        productRepository.save(product);
        log.info("Product with name {} is is saved into database", product.getName());
        return productMapper.entityToDTO(product);
    }

    public ProductEntityToDTO updateProduct(long id, ProductEntityToDTO request) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setName(request.getName());
            product.setQuantity(request.getQuantity());
            product.setDescription(request.getDescription());
            product.setAvailable(request.isAvailable());
            product.setPrice(request.getPrice());

            productRepository.save(product);
            log.info("Product with id: {} was updated", product.getId());

            return productMapper.entityToDTO(product);
        }
        throw new ProductNotFoundException();

    }

    public ProductEntityToDTO changePriceById(long id, float price) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPrice(price);

            productRepository.save(product);
            log.info("Product with id: {} was updated", product.getId());

            return productMapper.entityToDTO(product);
        }
        throw new ProductNotFoundException();

    }

    public ProductEntityToDTO changeQuantityById(long id, long quantity) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setQuantity(quantity);

            productRepository.save(product);
            log.info("Product with id: {} was updated", product.getId());

            return productMapper.entityToDTO(product);
        }
        throw new ProductNotFoundException();

    }

}
