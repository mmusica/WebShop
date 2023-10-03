package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.OrdersDTO;
import github.mmusica.webshop.dto.ProductOrdersDTO;
import github.mmusica.webshop.dto.ProductWithQuantityDTO;
import github.mmusica.webshop.model.ProductOrders;
import github.mmusica.webshop.service.mapper.OrdersMapper;
import github.mmusica.webshop.service.mapper.ProductOrdersListMapper;
import github.mmusica.webshop.service.mapper.ProductOrdersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductOrdersListToProductOrdersDTOListMapper implements ProductOrdersListMapper<List<ProductOrdersDTO>> {

    private final OrdersMapper<OrdersDTO> ordersToOrdersDTOMapper;
    private final ProductOrdersMapper<ProductWithQuantityDTO> productOrdersToProductWithQuantityDTOMapper;

    @Override
    public List<ProductOrdersDTO> apply(List<ProductOrders> productOrders) {
        Map<OrdersDTO, List<ProductWithQuantityDTO>> ordersListMap = getMapOfOrdersListProduct(productOrders);
        List<ProductOrdersDTO> productOrdersList = new ArrayList<>();
        ordersListMap.forEach((orders, productList) ->

                productOrdersList.add(ProductOrdersDTO.builder()
                        .ordersDTO(orders)
                        .productWithQuantityDTOList(productList)
                .build()));

        return productOrdersList;
    }
    private Map<OrdersDTO, List<ProductWithQuantityDTO>> getMapOfOrdersListProduct(List<ProductOrders> productOrdersList){
        Map<OrdersDTO, List<ProductWithQuantityDTO>> hashMap = new HashMap<>();

        productOrdersList.forEach(productOrders -> {
            OrdersDTO order = ordersToOrdersDTOMapper.apply(productOrders.getOrder());
            if(hashMap.containsKey(order)){
                hashMap.get(order).add(productOrdersToProductWithQuantityDTOMapper.apply(productOrders));
            }else{
                List<ProductWithQuantityDTO> productWithQuantityDTOList = new ArrayList<>();
                productWithQuantityDTOList.add(productOrdersToProductWithQuantityDTOMapper.apply(productOrders));
                hashMap.put(order, productWithQuantityDTOList);
            }
        });
        return hashMap;
    }
}
