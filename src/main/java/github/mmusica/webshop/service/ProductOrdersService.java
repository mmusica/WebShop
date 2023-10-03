package github.mmusica.webshop.service;

import github.mmusica.webshop.dto.ProductOrdersDTO;
import github.mmusica.webshop.dto.ProductWithQuantityDTO;

import java.util.List;

public interface ProductOrdersService {

    ProductOrdersDTO createOrder(Long customerId, List<ProductWithQuantityDTO> productList);

    List<ProductOrdersDTO> getAllOrdersByCustomerId(Long customerId);
}
