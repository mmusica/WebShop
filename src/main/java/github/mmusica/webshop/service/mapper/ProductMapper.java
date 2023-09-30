package github.mmusica.webshop.service.mapper;

import github.mmusica.webshop.model.Product;

import java.util.function.Function;

public interface ProductMapper<T> extends Function<Product, T> {
}
