package init.controller;

import init.model.ShopperPersonalizedDataRequest;
import init.model.ShopperRequest;
import init.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/shopper")
public class InternalShopperController {

    @Autowired
    ShopperService shopperService;

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createShopper(@RequestBody ShopperRequest shopperRequest) throws Exception {
        try {
            shopperService.createShopper(shopperRequest);
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PutMapping(value = "/personalized",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateShopperPersonalizedData(@RequestBody ShopperPersonalizedDataRequest request){
        try {
            shopperService.updateShopperPersonalizedData(request);
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
