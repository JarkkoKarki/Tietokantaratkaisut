package fi.metropolia.jarkkaka.prj.dto;

import java.math.BigDecimal;

public class OrderItemDto {
    private Integer productId;
    private Integer quantity;
    private BigDecimal unit_price;

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }
}
