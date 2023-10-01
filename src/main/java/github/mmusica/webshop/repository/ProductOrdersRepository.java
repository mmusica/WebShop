package github.mmusica.webshop.repository;

import github.mmusica.webshop.model.Orders;
import github.mmusica.webshop.model.ProductOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrdersRepository extends JpaRepository<ProductOrders, Long> {

    List<ProductOrders> findAllByOrder(Orders order);
}
