package github.mmusica.webshop.repository;

import github.mmusica.webshop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
