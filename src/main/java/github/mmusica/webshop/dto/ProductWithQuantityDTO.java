package github.mmusica.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithQuantityDTO {
    private ProductDTO productDTO;
    private int quantity;
}
