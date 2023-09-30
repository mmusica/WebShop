package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.ProductDTO;
import github.mmusica.webshop.model.Product;
import github.mmusica.webshop.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductToProductDTOMapper implements ProductMapper<ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
       return ProductDTO.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .build();
    }
}
