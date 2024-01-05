package cl.bci.evaluacionbci.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.bci.evaluacionbci.dto.UserSigninReqDto;
import cl.bci.evaluacionbci.entity.Phone;
import cl.bci.evaluacionbci.entity.User;
import cl.bci.evaluacionbci.exception.EmailAlreadyRegisteredException;
import cl.bci.evaluacionbci.repository.PhoneRepository;
import cl.bci.evaluacionbci.repository.UserRepository;
import cl.bci.evaluacionbci.security.JwtTokenUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PhoneRepository phoneRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;


    /**
     * Method under test: {@link UserServiceImpl#findUserByEmail(UserSigninReqDto)}
     */
    @Test
    void testFindUserByEmail() {
        User user = new User();
        user.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setIdUser("Id User");
        user.setIsActive(true);
        user.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhones(new ArrayList<>());
        user.setToken("ABC123");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findUserByEmail(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(EmailAlreadyRegisteredException.class, () -> userServiceImpl.findUserByEmail(new UserSigninReqDto()));
        verify(userRepository).findUserByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findUserByEmail(UserSigninReqDto)}
     */
    @Test
    void testFindUserByEmail2() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findUserByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        userServiceImpl.findUserByEmail(new UserSigninReqDto());
        verify(userRepository).findUserByEmail(Mockito.<String>any());
    }

    /**
     * Method under test:  {@link UserServiceImpl#findUserByEmail(UserSigninReqDto)}
     */
    @Test
    void testFindUserByEmail3() {
        when(userRepository.findUserByEmail(Mockito.<String>any()))
                .thenThrow(new EmailAlreadyRegisteredException("An error occurred"));
        assertThrows(EmailAlreadyRegisteredException.class, () -> userServiceImpl.findUserByEmail(new UserSigninReqDto()));
        verify(userRepository).findUserByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#savePhones(User)}
     */
    @Test
    void testSavePhones() {
        User savedUser = new User();
        savedUser.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setEmail("jane.doe@example.org");
        savedUser.setIdUser("Id User");
        savedUser.setIsActive(true);
        savedUser.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setName("Name");
        savedUser.setPassword("iloveyou");
        savedUser.setPhones(new ArrayList<>());
        savedUser.setToken("ABC123");
        userServiceImpl.savePhones(savedUser);
        assertEquals("00:00", savedUser.getCreated().toLocalTime().toString());
        assertEquals("00:00", savedUser.getLastLogin().toLocalTime().toString());
        assertEquals("00:00", savedUser.getModified().toLocalTime().toString());
        assertEquals("ABC123", savedUser.getToken());
        assertEquals("Id User", savedUser.getIdUser());
        assertEquals("Name", savedUser.getName());
        assertEquals("iloveyou", savedUser.getPassword());
        assertEquals("jane.doe@example.org", savedUser.getEmail());
        assertTrue(savedUser.getIsActive());
        assertTrue(savedUser.getPhones().isEmpty());
    }

    /**
     * Method under test:  {@link UserServiceImpl#savePhones(User)}
     */
    @Test
    void testSavePhones2() {
        when(phoneRepository.saveAll(Mockito.<Iterable<Phone>>any())).thenReturn(new ArrayList<>());

        User user = new User();
        user.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setIdUser("Id User");
        user.setIsActive(true);
        user.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhones(new ArrayList<>());
        user.setToken("ABC123");

        Phone phone = new Phone();
        phone.setCityCode("Oxford");
        phone.setCountryCode("GB");
        phone.setId(1L);
        phone.setNumber("42");
        phone.setUser(user);

        ArrayList<Phone> phones = new ArrayList<>();
        phones.add(phone);

        User savedUser = new User();
        savedUser.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setEmail("jane.doe@example.org");
        savedUser.setIdUser("Id User");
        savedUser.setIsActive(true);
        savedUser.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setName("Name");
        savedUser.setPassword("iloveyou");
        savedUser.setPhones(phones);
        savedUser.setToken("ABC123");
        userServiceImpl.savePhones(savedUser);
        verify(phoneRepository).saveAll(Mockito.<Iterable<Phone>>any());
        assertEquals("00:00", savedUser.getCreated().toLocalTime().toString());
        assertEquals("00:00", savedUser.getLastLogin().toLocalTime().toString());
        assertEquals("00:00", savedUser.getModified().toLocalTime().toString());
        assertEquals("ABC123", savedUser.getToken());
        assertEquals("Id User", savedUser.getIdUser());
        assertEquals("Name", savedUser.getName());
        assertEquals("iloveyou", savedUser.getPassword());
        assertEquals("jane.doe@example.org", savedUser.getEmail());
        assertEquals(1, savedUser.getPhones().size());
        assertTrue(savedUser.getIsActive());
    }

    /**
     * Method under test:  {@link UserServiceImpl#savePhones(User)}
     */
    @Test
    void testSavePhones3() {
        when(phoneRepository.saveAll(Mockito.<Iterable<Phone>>any()))
                .thenThrow(new EmailAlreadyRegisteredException("An error occurred"));

        User user = new User();
        user.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setIdUser("Id User");
        user.setIsActive(true);
        user.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhones(new ArrayList<>());
        user.setToken("ABC123");

        Phone phone = new Phone();
        phone.setCityCode("Oxford");
        phone.setCountryCode("GB");
        phone.setId(1L);
        phone.setNumber("42");
        phone.setUser(user);

        ArrayList<Phone> phones = new ArrayList<>();
        phones.add(phone);

        User savedUser = new User();
        savedUser.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setEmail("jane.doe@example.org");
        savedUser.setIdUser("Id User");
        savedUser.setIsActive(true);
        savedUser.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        savedUser.setName("Name");
        savedUser.setPassword("iloveyou");
        savedUser.setPhones(phones);
        savedUser.setToken("ABC123");
        assertThrows(EmailAlreadyRegisteredException.class, () -> userServiceImpl.savePhones(savedUser));
        verify(phoneRepository).saveAll(Mockito.<Iterable<Phone>>any());
    }
}
