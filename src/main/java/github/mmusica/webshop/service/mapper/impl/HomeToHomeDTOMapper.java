package github.mmusica.webshop.service.mapper.impl;

import github.mmusica.webshop.dto.HomeDTO;
import github.mmusica.webshop.model.Home;
import github.mmusica.webshop.service.mapper.HomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeToHomeDTOMapper implements HomeMapper<HomeDTO> {

    @Override
    public HomeDTO apply(Home home) {
        return HomeDTO.builder()
                .address(home.getAddress())
                .name(home.getName())
                .build();
    }


}
