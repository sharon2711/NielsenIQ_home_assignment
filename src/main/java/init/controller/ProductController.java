package init.controller;

import init.model.*;
import init.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/internal/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) throws Exception {
        try {
            productService.createProduct(productRequest);
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProductResponse> getProductsByShopper(ProductRequest productRequest) throws Exception {
        return productService.getProductsByShopper(productRequest);
    }
}
