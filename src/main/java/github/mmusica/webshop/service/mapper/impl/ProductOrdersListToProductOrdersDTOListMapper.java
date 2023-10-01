package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.AddProductDTO;
import github.mmusica.webshop.dto.OrdersDTO;
import github.mmusica.webshop.dto.ProductOrdersDTO;
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
    private final ProductOrdersMapper<AddProductDTO> productOrdersToAddProductDTOMapper;

    @Override
    public List<ProductOrdersDTO> apply(List<ProductOrders> productOrders) {
        Map<OrdersDTO, List<AddProductDTO>> ordersListMap = getMapOfOrdersListProduct(productOrders);
        List<ProductOrdersDTO> productOrdersList = new ArrayList<>();
        ordersListMap.forEach((orders, productList) ->

                productOrdersList.add(ProductOrdersDTO.builder()
                        .ordersDTO(orders)
                        .addProductDTOList(productList)
                .build()));

        return productOrdersList;
    }
    private Map<OrdersDTO, List<AddProductDTO>> getMapOfOrdersListProduct(List<ProductOrders> productOrdersList){
        Map<OrdersDTO, List<AddProductDTO>> hashMap = new HashMap<>();
        productOrdersList.forEach(productOrders -> {
            OrdersDTO order = ordersToOrdersDTOMapper.apply(productOrders.getOrder());
            if(hashMap.containsKey(order)){
                hashMap.get(order).add(productOrdersToAddProductDTOMapper.apply(productOrders));
            }else{
                hashMap.put(order,List.of(productOrdersToAddProductDTOMapper.apply(productOrders)));
            }
        });
        return hashMap;
    }
}
