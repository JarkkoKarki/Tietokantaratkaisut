package fi.metropolia.jarkkaka.prj.dto;

import java.math.BigDecimal;

public class OrderTotalDto {

    private Integer orderId;
    private BigDecimal total;

    public OrderTotalDto(Integer orderId, BigDecimal total) {
        this.orderId = orderId;
        this.total = total;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }
}