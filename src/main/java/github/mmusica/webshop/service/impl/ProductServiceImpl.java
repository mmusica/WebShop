package github.mmusica.webshop.service.impl;

import github.mmusica.webshop.model.Product;
import github.mmusica.webshop.repository.ProductRepository;
import github.mmusica.webshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


}
