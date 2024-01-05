package cl.bci.evaluacionbci.dto;

import cl.bci.evaluacionbci.dto.PhoneDto;
import cl.bci.evaluacionbci.utils.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSigninReqDto {

    private String name;

    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El correo no cumple con el formato correcto")
    private String email;

    @NotNull(message = "La contraseña no puede ser nulo")
    @NotBlank(message = "La contraseña no puede estar en blanco")
    @ValidPassword
    private String password;

    private List<PhoneDto> phones;
}
