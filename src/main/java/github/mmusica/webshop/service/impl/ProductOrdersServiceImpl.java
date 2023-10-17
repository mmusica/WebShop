package github.mmusica.webshop.service.impl;

import github.mmusica.webshop.dto.OrdersDTO;
import github.mmusica.webshop.dto.ProductOrdersDTO;
import github.mmusica.webshop.dto.ProductWithQuantityDTO;
import github.mmusica.webshop.model.Customer;
import github.mmusica.webshop.model.Orders;
import github.mmusica.webshop.model.Product;
import github.mmusica.webshop.model.ProductOrders;
import github.mmusica.webshop.repository.CustomerRepository;
import github.mmusica.webshop.repository.OrdersRepository;
import github.mmusica.webshop.repository.ProductOrdersRepository;
import github.mmusica.webshop.repository.ProductRepository;
import github.mmusica.webshop.service.PostRequestSender;
import github.mmusica.webshop.service.ProductOrdersService;
import github.mmusica.webshop.service.mapper.ProductOrdersListMapper;
import github.mmusica.webshop.service.mapper.impl.CustomerToCustomerDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductOrdersServiceImpl implements ProductOrdersService {

    private final CustomerRepository customerRepository;
    private final ProductOrdersRepository productOrdersRepository;
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;
    private final ProductOrdersListMapper<List<ProductOrdersDTO>> productOrdersListToProductOrdersDTOListMapper;
    private final CustomerToCustomerDTOMapper customerToCustomerDTOMapper;
    private final PostRequestSender postRequestSender;

    @Override
    public ProductOrdersDTO createOrder(Long customerId, List<ProductWithQuantityDTO> productWithQuantityDTOList) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty())
            throw new RuntimeException("No customer with id: %s found".formatted(customerId));

        List<ProductOrders> productOrdersList = new ArrayList<>();

        Orders order = Orders.builder()
                .customer(customerOptional.get())
                .productOrders(productOrdersList)
                .build();

        productWithQuantityDTOList.forEach(productWithQuantityDTO -> {
            Optional<Product> productOptional = productRepository.findById(productWithQuantityDTO.getProductDTO().getId());
            if(productOptional.isEmpty()) throw new RuntimeException("Product with id %s not found".formatted(productWithQuantityDTO.getProductDTO()));
            Product product = productOptional.get();
            ProductOrders productOrder = ProductOrders.builder()
                    .product(product)
                    .order(order)
                    .quantity(productWithQuantityDTO.getQuantity())
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

        ProductOrdersDTO productOrdersDTO = ProductOrdersDTO.builder()
                .ordersDTO(ordersDTO)
                .productWithQuantityDTOList(productWithQuantityDTOList)
                .build();
        // Currenty work in progress :)
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String json = mapper.writeValueAsString(productOrdersDTO);
//            String s = postRequestSender.sendPostRequest("http://127.0.0.1:5000/orders/ai", json);
//            log.info(s);
//        } catch (JsonProcessingException e) {
//            log.error(e.getMessage());
//        }

        return productOrdersDTO;
    }

    @Override
    public List<ProductOrdersDTO> getAllOrdersByCustomerId(Long customerId) {

        log.debug("getAllOrdersByCustomerId ----------------");
        Optional<Customer> customer = customerRepository.findById(customerId);
        Customer gotCustomer;
        if(customer.isEmpty()) throw new RuntimeException("No customer with id: %s found".formatted(customerId));

        gotCustomer = customer.get();
        List<Orders> ordersList = ordersRepository.findAllByCustomer(gotCustomer);
        if(ordersList.isEmpty()) throw new RuntimeException("No orders exist for customer with id: %s".formatted(customerId));

        List<ProductOrders> productOrdersList = new ArrayList<>();
        ordersList.forEach(order -> {
            List<ProductOrders> productOrders = order.getProductOrders();
            if(productOrders.isEmpty()) throw new RuntimeException("No productOrders for Order id: %s".formatted(order.getId()));
            productOrdersList.addAll(productOrders);
        });
        return productOrdersListToProductOrdersDTOListMapper.apply(productOrdersList);
    }
}
