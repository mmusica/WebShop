package github.mmusica.webshop.service;

import github.mmusica.webshop.dto.ProductDTO;
import github.mmusica.webshop.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product productDTO);

    List<ProductDTO> getAllProductsPageable(int page, int size);

    Product getProductById(Long id);
}
