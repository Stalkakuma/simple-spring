package lt.vidunas.simple_spring.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//TODO SWagger

@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
