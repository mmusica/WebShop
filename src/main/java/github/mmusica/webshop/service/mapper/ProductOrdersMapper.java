package github.mmusica.webshop.service.mapper;

import github.mmusica.webshop.model.ProductOrders;

import java.util.function.Function;

public interface ProductOrdersMapper<T> extends Function<ProductOrders, T> {
}
