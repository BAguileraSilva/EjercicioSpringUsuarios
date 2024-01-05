package cl.bci.evaluacionbci.service;

import cl.bci.evaluacionbci.dto.UserDto;
import cl.bci.evaluacionbci.dto.UserRespDto;
import cl.bci.evaluacionbci.dto.UserSigninReqDto;
import cl.bci.evaluacionbci.entity.*;
import cl.bci.evaluacionbci.exception.EmailAlreadyRegisteredException;
import cl.bci.evaluacionbci.repository.PhoneRepository;
import cl.bci.evaluacionbci.repository.UserRepository;
import cl.bci.evaluacionbci.security.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${error.email.alreadyRegistered}")
    private String emailAlreadyRegisteredMessage;


    @Override
    public UserRespDto saveUser(UserSigninReqDto userDto) {
        findUserByEmail(userDto);
        User user = modelMapper.map(userDto, User.class);
        configureSignupUser(user, userDto);
        User savedUser = userRepository.save(user);
        savePhones(savedUser);
        UserRespDto respDto = modelMapper.map(savedUser, UserRespDto.class);
        return respDto;
    }

    public void findUserByEmail(UserSigninReqDto userDto){
        if(userRepository.findUserByEmail(userDto.getEmail()).isPresent()){
            throw new EmailAlreadyRegisteredException(emailAlreadyRegisteredMessage);
        }
    }

    public void savePhones(User savedUser){
        if (savedUser.getPhones() != null && !savedUser.getPhones().isEmpty()) {
            phoneRepository.saveAll(savedUser.getPhones());
        }
    }

    private void configureSignupUser(User user, UserSigninReqDto userDto) {
        setCommonUserProperties(user);
        setCommonUserPhoneProperties(user, userDto);
    }

    private void setCommonUserProperties(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setIdUser(UUID.randomUUID().toString());
        user.setIsActive(true);
        user.setCreated(now);
        user.setLastLogin(now);
        user.setToken(jwtTokenUtil.generateJwtToken(user));
    }

    private void setCommonUserPhoneProperties(User user, UserSigninReqDto userDto) {
        if (userDto.getPhones() != null && !userDto.getPhones().isEmpty()) {
            user.setPhones(userDto.getPhones().stream()
                    .map(phoneDto -> {
                        Phone phone = modelMapper.map(phoneDto, Phone.class);
                        phone.setUser(user);
                        return phone;
                    })
                    .collect(Collectors.toList()));
        }
    }




}

