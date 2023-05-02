package init.controller;

import init.model.ProductRequest;
import init.model.ShopperPersonalizedDataRequest;
import init.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/internal/migration")
public class InternalMigrationController {

    @Autowired
    MigrationService migrationService;

    @PostMapping(value = "/product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProductMigration(@RequestBody List<ProductRequest> productRequests){
        try {
            migrationService.createProductMigration(productRequests);
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PostMapping(value = "shopper/personalized",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPersonalizedDataMigration(
            @RequestBody List<ShopperPersonalizedDataRequest> personalizedDataRequests
    ){
        try {
            migrationService.createPersonalizedDataMigration(personalizedDataRequests);
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
