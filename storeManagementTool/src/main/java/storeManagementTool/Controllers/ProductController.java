package storeManagementTool.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import storeManagementTool.Dtos.ProductEntityToDTO;
import storeManagementTool.Dtos.ProductReqDTO;
import storeManagementTool.Entities.ERole;
import storeManagementTool.Entities.User;
import storeManagementTool.Services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/PostRequestBody")
    public ProductReqDTO getRequestBody(){
        return productService.getReq();
    }

    @GetMapping("/{id}")
    public ProductEntityToDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductEntityToDTO> createProduct(@RequestBody ProductReqDTO request) {
            return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping()
    public List<ProductEntityToDTO> getProducts(){
        return productService.getProducts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntityToDTO> updateProduct(@PathVariable Long id, @RequestBody ProductEntityToDTO request){
        return new ResponseEntity<ProductEntityToDTO>(productService.updateProduct(id, request), HttpStatus.OK);
    }

    @PatchMapping({"/changePrice/{id}"})
    public ResponseEntity<ProductEntityToDTO> changePrice(@RequestParam float price, @PathVariable Long id) {
        if( price < 0) {
            log.error("Price is negative!");
            throw new IllegalArgumentException("Price must be greater than 0!");
        }
        return ResponseEntity.ok(productService.changePriceById(id, price));
    }

    @PatchMapping({"/changeQuantity/{id}"})
    public ResponseEntity<ProductEntityToDTO> changeQuantity(@RequestParam long quantity, @PathVariable Long id) {
        if( quantity < 0) {
            log.error("Quantity is negative!");
            throw new IllegalArgumentException("Price must be at least 0!");
        }
        return ResponseEntity.ok(productService.changeQuantityById(id, quantity));
    }


}
