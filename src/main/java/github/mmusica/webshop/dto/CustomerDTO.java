package github.mmusica.webshop.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class CustomerDTO {
    private String username;
    private List<HomeDTO> home;
}
