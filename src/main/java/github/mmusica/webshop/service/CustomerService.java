package github.mmusica.webshop.service;

import github.mmusica.webshop.dto.CustomerDTO;
import github.mmusica.webshop.model.Customer;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(CustomerDTO user);

    List<CustomerDTO> getCustomersPageable(int page, int size);

    CustomerDTO getCustomerById(Long id);
}
