package init.controller;

import init.model.Shopper;
import init.service.ShopperService;
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
@RequestMapping("/api/shopper")
public class ExternalShopperController {

    @Autowired
    private ShopperService shopperService;

    @GetMapping(path = "/byProduct",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getShoppersByProduct(@RequestParam("productId") String productId,
                                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        try {
            List<Shopper> shopperList = shopperService.getShoppersByProduct(productId, Math.min(limit, 1000));
            return ResponseEntity.ok().body(shopperList);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
