package cl.bci.evaluacionbci.dto;

import jakarta.validation.constraints.Size;
import lombok.Value;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@Value
public class PhoneDto implements Serializable {
    @Size(max = 20)
    String number;
    @Size(max = 4)
    @JsonProperty("citycode")
    String cityCode;
    @Size(max = 4)
    @JsonProperty("contrycode")
    String countryCode;

}