package storeManagementTool.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import storeManagementTool.Dtos.ProductEntityToDTO;
import storeManagementTool.Dtos.ProductReqDTO;
import storeManagementTool.Services.ProductService;

import java.util.List;

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
    public ResponseEntity<ProductEntityToDTO> createProduct(@RequestBody ProductReqDTO request) {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
//    TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
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


}
