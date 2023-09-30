package github.mmusica.webshop.controller;

import github.mmusica.webshop.dto.CustomerDTO;
import github.mmusica.webshop.model.Customer;
import github.mmusica.webshop.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult result){
        return new ResponseEntity<>(customerService.saveCustomer(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<CustomerDTO>> getCustomersPageable(@RequestParam("page") int page, @RequestParam("size") int size){
        return new ResponseEntity<>(customerService.getCustomersPageable(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
}
