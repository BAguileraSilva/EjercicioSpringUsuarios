package cl.bci.evaluacionbci.dto;

import cl.bci.evaluacionbci.entity.Phone;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Phone}
 */
@Value
public class PhoneDto implements Serializable {
    @Size(max = 20)
    String number;
    @Size(max = 4)
    String cityCode;
    @Size(max = 4)
    String countryCode;

}