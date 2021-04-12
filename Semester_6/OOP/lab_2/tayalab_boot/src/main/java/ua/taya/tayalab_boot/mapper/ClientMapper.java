package ua.taya.tayalab_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.taya.tayalab_boot.dto.SignUpDto;
import ua.taya.tayalab_boot.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mappings({
            @Mapping(target = "user.username",  source = "dto.login"),
            @Mapping(target = "user.password", source = "dto.password"),
            @Mapping(target = "isRegularClients", constant = "false")
    })
    Client clientFromSignUpDto(SignUpDto dto);
}
