package ua.taya.tayalab_boot.mapper;

import org.mapstruct.*;
import ua.taya.tayalab_boot.dto.ReservationDto;
import ua.taya.tayalab_boot.dto.ReservedTourDto;
import ua.taya.tayalab_boot.entity.Client;
import ua.taya.tayalab_boot.entity.Reservation;
import ua.taya.tayalab_boot.entity.User;
import ua.taya.tayalab_boot.repository.ClientRepository;
import ua.taya.tayalab_boot.repository.TourRepository;
import ua.taya.tayalab_boot.repository.UserRepository;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TourRepository.class, UserRepository.class, ClientRepository.class})
public interface ReservationMapper {
    @Mappings({
            @Mapping(target = "tourId", source = "entity.tour.id"),
            @Mapping(target = "userId", source = "entity.client.user.id")}
    )
    ReservationDto entityToDto(Reservation entity);

    @Mapping(target = "tour", source = "dto.tourId")
    Reservation dtoToEntity(ReservationDto dto);

    @Named("reservationToReservedTour")
    @Mapping(target = "reservationId", source = "entity.id")
    ReservedTourDto reservationToReservedTour(Reservation entity);

    @IterableMapping(qualifiedByName = "reservationToReservedTour")
    List<ReservedTourDto> reservationListToReservedTourList(List<Reservation> entities);
}
