package github.mmusica.webshop.controller;


import github.mmusica.webshop.dto.ProductDTO;
import github.mmusica.webshop.dto.ProductOrdersDTO;
import github.mmusica.webshop.service.ProductOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordersProducts")
@RequiredArgsConstructor
public class OrdersProductController {

    private final ProductOrdersService productOrdersService;

    @PostMapping("/{customerId}")
    public ResponseEntity<ProductOrdersDTO> addOrderForCustomer(
            @PathVariable(name = "customerId") Long id,
            @RequestBody List<ProductDTO> products) {
        return new ResponseEntity<>(productOrdersService.createOrder(id, products), HttpStatus.CREATED);
    }
}
