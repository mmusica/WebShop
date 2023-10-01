package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.AddProductDTO;
import github.mmusica.webshop.dto.ProductDTO;
import github.mmusica.webshop.model.ProductOrders;
import github.mmusica.webshop.service.mapper.ProductMapper;
import github.mmusica.webshop.service.mapper.ProductOrdersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrdersToAddProductDTOMapper implements ProductOrdersMapper<AddProductDTO> {

    private final ProductMapper<ProductDTO> productToProductDTOMapper;
    @Override
    public AddProductDTO apply(ProductOrders productOrders) {
        return AddProductDTO.builder()
                .productDTO(productToProductDTOMapper.apply(productOrders.getProduct()))
                .quantity(productOrders.getQuantity())
                .build();
    }
}
