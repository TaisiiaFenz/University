package ua.taya.tayalab_boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.dto.SignUpDto;
import ua.taya.tayalab_boot.entity.Client;
import ua.taya.tayalab_boot.entity.RoleType;
import ua.taya.tayalab_boot.mapper.ClientMapper;
import ua.taya.tayalab_boot.repository.ClientRepository;
import ua.taya.tayalab_boot.service.ClientService;

import java.util.HashSet;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ResponseDto saveClient(SignUpDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Client client = clientMapper.clientFromSignUpDto(dto);
        client.getUser().setAuthorities(new HashSet<>());
        client.getUser().getAuthorities().add(RoleType.CLIENT);
        clientRepository.save(client);
        return new ResponseDto(true, "Client was saved");
    }

    public Client findClientByUserId(Long userId) {
        return clientRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("No client with such user id"));
    }
}
