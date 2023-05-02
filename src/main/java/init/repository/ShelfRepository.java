package init.repository;

import init.model.Shelf;

public interface ShelfRepository {
    void createShelf(Shelf shelf);
    Shelf getShelfByShopperIdAndProductId(String shopperId, String productId);
}
