package github.mmusica.webshop.controller;

import github.mmusica.webshop.dto.ProductDTO;
import github.mmusica.webshop.model.Product;
import github.mmusica.webshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam("page") int page, @RequestParam("size") int size){
        return new ResponseEntity<>(productService.getAllProductsPageable(page,size), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
}
