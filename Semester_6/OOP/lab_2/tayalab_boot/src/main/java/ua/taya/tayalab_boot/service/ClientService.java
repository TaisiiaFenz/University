package ua.taya.tayalab_boot.service;

import ua.taya.tayalab_boot.dto.ResponseDto;
import ua.taya.tayalab_boot.dto.SignUpDto;
import ua.taya.tayalab_boot.entity.Client;

public interface ClientService {
    ResponseDto saveClient(SignUpDto dto);

    Client findClientByUserId(Long userId);
}
