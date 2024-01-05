package cl.bci.evaluacionbci.service;

import cl.bci.evaluacionbci.dto.UserRespDto;
import cl.bci.evaluacionbci.dto.UserSigninReqDto;

public interface UserService {
    UserRespDto saveUser(UserSigninReqDto userDto);
}
