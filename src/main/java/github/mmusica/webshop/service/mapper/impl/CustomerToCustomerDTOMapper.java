package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.HomeDTO;
import github.mmusica.webshop.dto.CustomerDTO;
import github.mmusica.webshop.model.Customer;
import github.mmusica.webshop.service.mapper.HomeMapper;
import github.mmusica.webshop.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerToCustomerDTOMapper implements CustomerMapper<CustomerDTO> {

    private final HomeMapper<HomeDTO> homeToHomeDTOMapper;

    @Override
    public CustomerDTO apply(Customer customer) {
        return CustomerDTO.builder()
                .home(customer.getHomes().stream().map(homeToHomeDTOMapper).toList())
                .username(customer.getUsername())
                .build();
    }
}
