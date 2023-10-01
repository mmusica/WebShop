package github.mmusica.webshop.service;

import github.mmusica.webshop.dto.AddProductDTO;
import github.mmusica.webshop.dto.ProductOrdersDTO;

import java.util.List;

public interface ProductOrdersService {

    ProductOrdersDTO createOrder(Long customerId, List<AddProductDTO> productList);

    List<ProductOrdersDTO> getAllOrdersByCustomerId(Long customerId);
}
