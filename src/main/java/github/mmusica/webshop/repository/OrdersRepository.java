package github.mmusica.webshop.repository;

import github.mmusica.webshop.model.Customer;
import github.mmusica.webshop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByCustomer(Customer customer);
}
