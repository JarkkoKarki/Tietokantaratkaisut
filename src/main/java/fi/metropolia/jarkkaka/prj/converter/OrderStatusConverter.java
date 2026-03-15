package fi.metropolia.jarkkaka.prj.converter;

import fi.metropolia.jarkkaka.prj.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        if (status == null) return null;

        return switch (status) {
            case NEW -> "N";
            case SHIPPED -> "S";
            case CANCELLED -> "C";
        };
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbValue) {
        if (dbValue == null) return null;

        return switch (dbValue) {
            case "N", "NEW" -> OrderStatus.NEW;
            case "S", "SHIPPED" -> OrderStatus.SHIPPED;
            case "C", "CANCELLED" -> OrderStatus.CANCELLED;
            default -> throw new IllegalArgumentException("Unknown status: " + dbValue);
        };
    }
}