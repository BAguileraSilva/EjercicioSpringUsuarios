package cl.bci.evaluacionbci.dto;

import cl.bci.evaluacionbci.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    @Size(max = 255)
    String idUser;
    LocalDateTime created;
    @Size(max = 255)
    String email;
    Boolean isActive;
    @NotNull(message = "No debe ser nulo o vacio")
    LocalDateTime modified;
    LocalDateTime lastLogin;
    @Size(max = 255)
    String name;
    @Size(max = 255)
    String password;
    @Size(max = 2000)
    String token;
}