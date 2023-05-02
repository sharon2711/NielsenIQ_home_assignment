package init.controller;

import init.model.Product;
import init.model.ProductResponse;
import init.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ExternalProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/byShopper",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsByShopper(@RequestParam("shopperId") String shopperId,
                                                  @RequestParam(value = "category", required = false) String category,
                                                  @RequestParam(value = "brand", required = false) String brand,
                                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        try {
            List<Product> productList = productService.getProductsByShopper(shopperId, category, brand, Math.min(limit, 100));
            return ResponseEntity.ok().body(productList);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
