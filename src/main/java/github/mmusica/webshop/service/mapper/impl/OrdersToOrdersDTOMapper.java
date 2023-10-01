package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.CustomerDTO;
import github.mmusica.webshop.dto.OrdersDTO;
import github.mmusica.webshop.model.Orders;
import github.mmusica.webshop.service.mapper.CustomerMapper;
import github.mmusica.webshop.service.mapper.OrdersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersToOrdersDTOMapper implements OrdersMapper<OrdersDTO> {

    private final CustomerMapper<CustomerDTO> customerToCustomerDTOMapper;

    @Override
    public OrdersDTO apply(Orders orders) {
       return OrdersDTO.builder()
                .customer(customerToCustomerDTOMapper.apply(orders.getCustomer()))
                .build();
    }
}
