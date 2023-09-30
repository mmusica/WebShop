package github.mmusica.webshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeDTO {
    private String name;
    private String address;
}
