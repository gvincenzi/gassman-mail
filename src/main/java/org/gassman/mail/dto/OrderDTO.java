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

    public String toHTTPQuery() {
        return "orderId=" + orderId + "&quantity=" + quantity + user.toHTTPQuery("&user") + product.toHTTPQuery("&product");
    }
}
