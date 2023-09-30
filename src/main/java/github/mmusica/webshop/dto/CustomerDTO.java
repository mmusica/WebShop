package github.mmusica.webshop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CustomerDTO {
    private String username;
    private List<HomeDTO> home;
}
