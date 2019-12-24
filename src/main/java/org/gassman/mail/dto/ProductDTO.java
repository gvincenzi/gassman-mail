package org.gassman.mail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class ProductDTO implements Comparable<ProductDTO> {
    private Long productId;
    private String name;
    private String description;
    private String unitOfMeasure;
    private BigDecimal pricePerUnit;
    private Integer availableQuantity;
    private String deliveryDateTime;
    private Boolean active = Boolean.TRUE;

    @Override
    public String toString() {
        return "\nID : " + productId +
                " -- Nome :'" + name + '\'' +
                "\nDescrizione :'" + description + '\'' +
                "\nPrezzo al " + unitOfMeasure + " : " + pricePerUnit + "€" +
                " -- Disponibilità : " + availableQuantity +
                "\nData di consegna : " + LocalDateTime.parse(deliveryDateTime).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public int compareTo(ProductDTO productDTO) {
        return this.deliveryDateTime.compareTo(productDTO.deliveryDateTime);
    }

    public String toHTTPQuery(String prefix) {
        return prefix + ".productId=" + productId +
               prefix + ".name=" + name +
               prefix + ".description=" + description +
               prefix + ".unitOfMeasure=" + unitOfMeasure +
               prefix + ".pricePerUnit=" + pricePerUnit +
               prefix + ".deliveryDateTime=" + deliveryDateTime;
    }
}
