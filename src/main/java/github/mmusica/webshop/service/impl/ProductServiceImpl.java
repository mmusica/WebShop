package github.mmusica.webshop.service.impl;

import github.mmusica.webshop.dto.ProductDTO;
import github.mmusica.webshop.model.Product;
import github.mmusica.webshop.repository.ProductRepository;
import github.mmusica.webshop.service.ProductService;
import github.mmusica.webshop.service.mapper.impl.ProductToProductDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductToProductDTOMapper productToProductDTOMapper;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductDTO> getAllProductsPageable(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Product> allProducts = productRepository.findAll(pageRequest);
        return allProducts.stream().map(productToProductDTOMapper).toList();
    }
}
