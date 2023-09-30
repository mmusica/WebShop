package github.mmusica.webshop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductOrdersDTO {
    OrdersDTO ordersDTO;
    List<AddProductDTO> addProductDTOList;

}
