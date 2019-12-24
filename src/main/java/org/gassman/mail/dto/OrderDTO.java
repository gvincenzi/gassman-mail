package org.gassman.mail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Integer quantity;
    private UserDTO user;
    private ProductDTO product;

    @Override
    public String toString() {
        return "\nID Ordine=" + orderId +
                ", Quantità : " + quantity +
                "\nProdotto : " + product;
    }

    public String getTotalToPay(){
        return this.getQuantity() != null && this.getProduct() != null
                && this.getProduct().getPricePerUnit() != null ? new BigDecimal(getQuantity()).multiply(getProduct().getPricePerUnit()).toString() : null;
    }

    public String toHTTPQuery() {
        return "orderId=" + orderId + "&quantity=" + quantity + "&totalToPay=" + getTotalToPay() + product.toHTTPQuery("&product");
    }
}
