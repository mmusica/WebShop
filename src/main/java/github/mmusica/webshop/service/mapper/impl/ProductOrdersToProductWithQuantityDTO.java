package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.ProductDTO;
import github.mmusica.webshop.dto.ProductWithQuantityDTO;
import github.mmusica.webshop.model.ProductOrders;
import github.mmusica.webshop.service.mapper.ProductMapper;
import github.mmusica.webshop.service.mapper.ProductOrdersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrdersToProductWithQuantityDTO implements ProductOrdersMapper<ProductWithQuantityDTO> {

    private final ProductMapper<ProductDTO> productToProductDTOMapper;
    @Override
    public ProductWithQuantityDTO apply(ProductOrders productOrders) {
        return ProductWithQuantityDTO.builder()
                .productDTO(productToProductDTOMapper.apply(productOrders.getProduct()))
                .quantity(productOrders.getQuantity())
                .build();
    }
}
