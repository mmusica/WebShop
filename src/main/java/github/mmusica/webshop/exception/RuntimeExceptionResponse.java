package github.mmusica.webshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuntimeExceptionResponse {
    private String errorMessage;
}
