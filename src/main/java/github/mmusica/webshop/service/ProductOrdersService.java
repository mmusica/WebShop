package github.mmusica.webshop.service;

import github.mmusica.webshop.dto.ProductOrdersDTO;
import github.mmusica.webshop.dto.ProductDTO;

import java.util.List;

public interface ProductOrdersService {

    ProductOrdersDTO createOrder(Long customerId, List<ProductDTO> productList);
}
