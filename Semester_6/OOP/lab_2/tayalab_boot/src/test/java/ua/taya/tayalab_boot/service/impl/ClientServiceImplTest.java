package ua.taya.tayalab_boot.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.taya.tayalab_boot.TayalabBootApplication;
import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.dto.SignUpDto;
import ua.taya.tayalab_boot.entity.Client;
import ua.taya.tayalab_boot.entity.RoleType;
import ua.taya.tayalab_boot.entity.User;
import ua.taya.tayalab_boot.mapper.ClientMapper;
import ua.taya.tayalab_boot.repository.ClientRepository;
import ua.taya.tayalab_boot.repository.UserRepository;
import ua.taya.tayalab_boot.service.ClientService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TayalabBootApplication.class})
public class ClientServiceImplTest {
    @MockBean
    private ClientRepository clientRepository;
    @InjectMocks
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private ClientMapper clientMapper;
    private SignUpDto signUpDto;
    private Client client;
    @Autowired
//    @Lazy
    @Spy
    private PasswordEncoder passwordEncoder;

    @Configuration
    public static class Config {
        @Bean
        public ClientServiceImpl clientService(){
            return new ClientServiceImpl();
        }

        @Bean
        public ClientMapper clientMapper(){
            return Mappers.getMapper(ClientMapper.class);
        }

//        @Bean
//        public PasswordEncoder passwordEncoder(){
//            return new BCryptPasswordEncoder();
//        }
    }

    @Before
    public void setUp() throws Exception {
        signUpDto = SignUpDto.builder()
                .login("taisha")
                .password("password")
                .firstName("Taisia")
                .middleName("Vitaliivna")
                .lastName("Fenz")
                .passport("AA111111")
                .birthday(LocalDate.of(2001, 8, 23))
                .build();
        User user = User.builder()
                .username("taisha")
                .password(passwordEncoder.encode("password"))
                .build();
        client = Client.builder()
                .id(1L)
                .user(user)
                .firstName("Taisia")
                .middleName("Vitaliivna")
                .lastName("Fenz")
                .passport("AA111111")
                .birthday(LocalDate.of(2001, 8, 23))
                .isRegularClients(false)
                .build();
    }

    @Test
    public void saveClient() {
        ResponseDto expectedResponse = new ResponseDto(true, "Client was saved");
        client.getUser().setAuthorities(new HashSet<>());
        client.getUser().getAuthorities().add(RoleType.CLIENT);
        ResponseDto actualResult = clientService.saveClient(signUpDto);
        assertTrue(passwordEncoder.matches("password", client.getUser().getPassword()));
        Mockito.verify(clientRepository, Mockito.times(1)).save(any(Client.class));
        assertEquals(expectedResponse, actualResult);
    }

    @Test
    public void findClientByUserId() {
        Mockito.when(clientRepository.findByUserId(1L)).thenReturn(Optional.of(client));
        Client actualClient = clientService.findClientByUserId(1L);
        assertEquals(client, actualClient);
    }
}