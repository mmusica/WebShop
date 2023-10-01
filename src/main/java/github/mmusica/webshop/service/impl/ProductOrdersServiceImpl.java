package github.mmusica.webshop.service.impl;

import github.mmusica.webshop.dto.AddProductDTO;
import github.mmusica.webshop.dto.OrdersDTO;
import github.mmusica.webshop.dto.ProductOrdersDTO;
import github.mmusica.webshop.model.Customer;
import github.mmusica.webshop.model.Orders;
import github.mmusica.webshop.model.Product;
import github.mmusica.webshop.model.ProductOrders;
import github.mmusica.webshop.repository.CustomerRepository;
import github.mmusica.webshop.repository.OrdersRepository;
import github.mmusica.webshop.repository.ProductOrdersRepository;
import github.mmusica.webshop.repository.ProductRepository;
import github.mmusica.webshop.service.ProductOrdersService;
import github.mmusica.webshop.service.mapper.ProductOrdersListMapper;
import github.mmusica.webshop.service.mapper.impl.CustomerToCustomerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOrdersServiceImpl implements ProductOrdersService {

    private final CustomerRepository customerRepository;
    private final ProductOrdersRepository productOrdersRepository;
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;
    private final ProductOrdersListMapper<List<ProductOrdersDTO>> productOrdersListToProductOrdersDTOListMapper;
    private final CustomerToCustomerDTOMapper customerToCustomerDTOMapper;

    @Override
    public ProductOrdersDTO createOrder(Long customerId, List<AddProductDTO> addProductDTOList) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty())
            throw new RuntimeException("No customer with id: %s found".formatted(customerId));

        List<ProductOrders> productOrdersList = new ArrayList<>();

        Orders order = Orders.builder()
                .customer(customerOptional.get())
                .productOrders(productOrdersList)
                .build();

        addProductDTOList.forEach(addProductDTO -> {
            Optional<Product> productOptional = productRepository.findById(addProductDTO.getProductDTO().getId());
            if(productOptional.isEmpty()) throw new RuntimeException("Product with id %s not found".formatted(addProductDTO.getProductDTO()));
            Product product = productOptional.get();
            ProductOrders productOrder = ProductOrders.builder()
                    .product(product)
                    .order(order)
                    .quantity(addProductDTO.getQuantity())
                    .build();
            productOrdersList.add(productOrder);
        });

        try {
            productOrdersRepository.saveAll(productOrdersList);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        OrdersDTO ordersDTO = OrdersDTO.builder()
                .customer(customerToCustomerDTOMapper.apply(customerOptional.get()))
                .build();

        return ProductOrdersDTO.builder()
                .ordersDTO(ordersDTO)
                .addProductDTOList(addProductDTOList)
                .build();
    }

    @Override
    public List<ProductOrdersDTO> getAllOrdersByCustomerId(Long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        Customer gotCustomer;
        if(customer.isEmpty()) throw new RuntimeException("No customer with id: %s found".formatted(customerId));

        gotCustomer = customer.get();
        List<Orders> ordersList = ordersRepository.findAllByCustomer(gotCustomer);
        if(ordersList.isEmpty()) throw new RuntimeException("No orders exist for customer with id: %s".formatted(customerId));

        List<ProductOrders> productOrdersList = new ArrayList<>();
        ordersList.forEach(order -> {
            List<ProductOrders> productOrdersByOrderList = productOrdersRepository.findAllByOrder(order);
            if(productOrdersByOrderList.isEmpty()) throw new RuntimeException("No productOrders for Order id: %s".formatted(order.getId()));
            productOrdersList.addAll(productOrdersByOrderList);
        });
        return productOrdersListToProductOrdersDTOListMapper.apply(productOrdersList);
    }
}
