package storeManagementTool.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import storeManagementTool.Dtos.ProductEntityToDTO;
import storeManagementTool.Dtos.ProductReqDTO;
import storeManagementTool.Services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/PostRequestBody")
    public ProductReqDTO getRequestBody(){
        return productService.getReq();
    }

    @GetMapping("/test")
    public String testing(){
        return "It works!";
    }

    @GetMapping("/{id}")
    public ProductEntityToDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
//    TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductEntityToDTO> createRestaurant(@RequestBody ProductReqDTO request) {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
//    TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }




}
