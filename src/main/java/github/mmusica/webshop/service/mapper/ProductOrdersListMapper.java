package github.mmusica.webshop.service.mapper;

import github.mmusica.webshop.model.ProductOrders;

import java.util.List;
import java.util.function.Function;

public interface ProductOrdersListMapper<T> extends Function<List<ProductOrders>, T> {
}
