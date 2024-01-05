package cl.bci.evaluacionbci.controller;

import cl.bci.evaluacionbci.dto.UserRespDto;
import cl.bci.evaluacionbci.dto.UserSigninReqDto;
import cl.bci.evaluacionbci.service.UserService;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import cl.bci.evaluacionbci.exception.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RequestMapping("/v1")
@Validated
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value ="/signup", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Registra un nuevo usuario",
            description = "Crea un nuevo usuario en el sistema",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserSigninReqDto.class),
                            examples = @ExampleObject(
                                    name = "EjemploSignup",
                                    summary = "Ejemplo de Usuario",
                                    value = "{\"name\": \"Juan Rodriguez\", \"email\": \"juan@rodriguez.org\", \"password\": \"Jr1231\", \"phones\": [{\"number\": \"1234567\", \"citycode\": \"1\", \"countryCode\": \"57\"}]}"
                            )
                    )
            ),responses = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserRespDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Respuesta Erronea",
                                    summary = "Error Signup",
                                    value = "{\"message\": \"La contraseña no cumple con el formato requerido.\"}"))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Respuesta Erronea",
                                    summary = "Error Signup",
                                    value = "{\"message\": \"Se ha producido un error en el servidor.\"}")))
            }
    )
    public ResponseEntity<UserRespDto> registrarUsuario(@Valid @RequestBody UserSigninReqDto request) {
        UserRespDto resp = userService.saveUser(request);
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }


}
