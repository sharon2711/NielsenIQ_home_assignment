package init.model;

public class Shopper {
    private Long id;
    private String shopperId;

    public Shopper(Long id, String shopperId) {
        this.id = id;
        this.shopperId = shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public String getShopperId() {
        return shopperId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
