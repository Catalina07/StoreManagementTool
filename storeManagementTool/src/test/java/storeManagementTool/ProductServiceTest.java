package storeManagementTool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import storeManagementTool.Dtos.ProductEntityToDTO;
import storeManagementTool.Dtos.ProductReqDTO;
import storeManagementTool.Entities.Product;
import storeManagementTool.Exceptions.ProductNotFoundException;
import storeManagementTool.Mappers.ProductMapper;
import storeManagementTool.Repositories.ProductRepository;
import storeManagementTool.Services.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private static List<Product> products;

    @BeforeAll
    public static void setUp() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product1");
        product1.setDescription("Description1");
        product1.setQuantity(10L);
        product1.setPrice(10.0f);
        product1.isAvailable();


        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product2");
        product2.setDescription("Description2");
        product2.setQuantity(29l);
        product2.setPrice(5f);
        product2.isAvailable();

        products = Arrays.asList(product1, product2);


    }

    @BeforeEach
    public void mockMapper() {
        lenient().when(productMapper.entityToDTO(any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            return ProductEntityToDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build() ;
        });
    }

    @Test
    public void getProductWhenItExistsById() {

        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(products.get(0)));

        ProductEntityToDTO productEntityToDTO = productService.getProductById(productId);

        assertNotNull(productEntityToDTO);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void getProductWhenItDoesNotExistById() {

        Long productId = 5L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    public void updateProduct(){
        ProductReqDTO productReqDTO = new ProductReqDTO();
        ProductEntityToDTO productEntityToDTO = new ProductEntityToDTO();
        productEntityToDTO.setId(1L);
        productEntityToDTO.setName("New Name");
        productEntityToDTO.setPrice(111f);
        productEntityToDTO.setQuantity(10000l);


        when(productRepository.findById(productEntityToDTO.getId())).thenReturn(Optional.of(products.get(0)));

        ProductEntityToDTO updatedProductDTO = productService.updateProduct(productEntityToDTO.getId(), productEntityToDTO);

        assertNotNull(updatedProductDTO);
        assertEquals("New Name", products.get(0).getName());
        verify(productRepository, times(1)).save(products.get(0));

    }

    @Test
    public void updateProductWhenItDoesNotExist(){
        ProductReqDTO productReqDTO = new ProductReqDTO();
        ProductEntityToDTO productEntityToDTO = new ProductEntityToDTO();
        productEntityToDTO.setId(10L);

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(productEntityToDTO.getId(), productEntityToDTO));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void changePrice(){
        Long id = 1l;
        float price = 1000f;
        when(productRepository.findById(id)).thenReturn(Optional.of(products.get(0)));
        when(productRepository.save(products.get(0))).thenReturn(products.get(0));

        ProductEntityToDTO productDTO = productService.changePriceById(id, price);

        assertNotNull(productDTO);
        assertEquals(price, products.get(0).getPrice());
        verify(productRepository, times(1)).save(products.get(0));
    }

    @Test
    public void changeQuantity(){
        Long id = 1l;
        long quantity = 1000l;
        when(productRepository.findById(id)).thenReturn(Optional.of(products.get(0)));
        when(productRepository.save(products.get(0))).thenReturn(products.get(0));

        ProductEntityToDTO productDTO = productService.changeQuantityById(id, quantity);

        assertNotNull(productDTO);
        assertEquals(quantity, products.get(0).getQuantity());
        verify(productRepository, times(1)).save(products.get(0));
    }
    
}
