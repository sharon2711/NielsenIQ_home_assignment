import init.model.*;
import init.repository.ProductRepository;
import init.repository.ShelfRepository;
import init.repository.ShopperRepository;
import init.service.impl.ShopperServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class ShopperServiceTests {

    @InjectMocks
    ShopperServiceImpl shopperService;

    @Mock
    ShopperRepository shopperRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    ShelfRepository shelfRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNewShopper() {
        String shopperId = UUID.randomUUID().toString();
        ShopperRequest shopperRequest = new ShopperRequest(shopperId);

        shopperService.createShopper(shopperRequest);

        verify(shopperRepository, times(1)).createShopper(any());
    }

    @Test
    public void testUpdateShopperPersonalizedDataWhenProvidingExistingShopper() throws Exception {
        String shopperId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        String productCategory = "Milk";
        String productBrand = "Juice";
        ShopperPersonalizedDataRequest request = new ShopperPersonalizedDataRequest(shopperId, Arrays.asList(new ShopperProductData(productId, 0.8)));

        Shopper existingShopper = new Shopper(null, shopperId);
        when(shopperRepository.getShopperById(shopperId)).thenReturn(existingShopper);

        Product product = new Product(null, productId, productCategory, productBrand);
        when(productRepository.getProductById(productId)).thenReturn(product);

        shopperService.updateShopperPersonalizedData(request);

        verify(shopperRepository, times(1)).getShopperById(shopperId);
        verify(shopperRepository, never()).createShopper(any());
        verify(shelfRepository, times(1)).createShelf(any());
    }

    @Test
    public void testUpdateShopperPersonalizedDataWithNewShopper() throws Exception {
        String shopperId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        String productCategory = "Milk";
        String productBrand = "Juice";
        ShopperPersonalizedDataRequest request = new ShopperPersonalizedDataRequest(shopperId, Arrays.asList(new ShopperProductData(productId, 0.8)));

        when(shopperRepository.getShopperById(shopperId)).thenReturn(null);

        Product product = new Product(null, productId, productCategory, productBrand);
        when(productRepository.getProductById(productId)).thenReturn(product);

        shopperService.updateShopperPersonalizedData(request);

        verify(shopperRepository, times(1)).getShopperById(shopperId);
        verify(shopperRepository, times(1)).createShopper(any());
        verify(shelfRepository, times(1)).createShelf(any());
    }

    @Test
    public void testUpdateShopperPersonalizedDataWithProductNotExist() {
        String shopperId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        ShopperPersonalizedDataRequest request = new ShopperPersonalizedDataRequest(shopperId, Arrays.asList(new ShopperProductData(productId, 0.8)));

        Shopper existingShopper = new Shopper(null, shopperId);
        when(shopperRepository.getShopperById(shopperId)).thenReturn(existingShopper);

        when(productRepository.getProductById(productId)).thenReturn(null);

        Assertions.assertThrows(Exception.class, () -> shopperService.updateShopperPersonalizedData(request));

        verify(shopperRepository, times(1)).getShopperById(shopperId);
        verify(shopperRepository, never()).createShopper(any());
        verify(shelfRepository, never()).createShelf(any());
    }

    @Test
    public void testGetShoppersByProduct() throws Exception {
        String firstShopperId = UUID.randomUUID().toString();
        String secondShopperId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        Integer limit = 10;

        List<Shopper> shoppers = Arrays.asList(new Shopper(null, firstShopperId), new Shopper(null, secondShopperId));
        when(shopperRepository.getShoppersByProduct(productId, limit)).thenReturn(shoppers);

        List<Shopper> result = shopperService.getShoppersByProduct(productId, limit);

        verify(shopperRepository, times(1)).getShoppersByProduct(productId, limit);
    }
}