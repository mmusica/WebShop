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
import java.util.Optional;

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

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }else{
            throw new RuntimeException("Product with id: %s not found".formatted(id));
        }
    }
}
