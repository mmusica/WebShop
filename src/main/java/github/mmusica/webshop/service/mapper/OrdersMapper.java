package github.mmusica.webshop.service.mapper;

import github.mmusica.webshop.model.Orders;

import java.util.function.Function;

public interface OrdersMapper<T> extends Function<Orders, T> {
}
