package github.mmusica.webshop.service.mapper;

import github.mmusica.webshop.model.Customer;

import java.util.function.Function;

public interface CustomerMapper<T> extends Function<Customer, T> {
}
