package github.mmusica.webshop.repository;

import github.mmusica.webshop.model.ProductOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrdersRepository extends JpaRepository<ProductOrders, Long> {
}
