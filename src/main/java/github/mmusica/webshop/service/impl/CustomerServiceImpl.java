package github.mmusica.webshop.service.impl;

import github.mmusica.webshop.dto.CustomerDTO;
import github.mmusica.webshop.model.Home;
import github.mmusica.webshop.model.Customer;
import github.mmusica.webshop.repository.CustomerRepository;
import github.mmusica.webshop.service.CustomerService;
import github.mmusica.webshop.service.mapper.impl.CustomerToCustomerDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerToCustomerDTOMapper customerToCustomerDTOMapper;

    @Override
    public Customer saveCustomer(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new RuntimeException("User cannot be null");
        }
        Customer customer = Customer.builder()
                .username(customerDTO.getUsername())
                .build();

        List<Home> homes = new ArrayList<>(customerDTO.getHome().stream().map(
                homeDTO -> Home.builder()
                        .address(homeDTO.getAddress())
                        .name(homeDTO.getName())
                        .customer(customer)
                        .build()
        ).toList());
        customer.setHomes(homes);

        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> getCustomersPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Customer> allUsers = customerRepository.findAll(pageable);
        return allUsers.stream()
                .map(customerToCustomerDTOMapper)
                .toList();

    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customerToCustomerDTOMapper.apply(customer.get());
        }else{
            throw new RuntimeException("Customer with id %s not found".formatted(id));
        }
    }
}
