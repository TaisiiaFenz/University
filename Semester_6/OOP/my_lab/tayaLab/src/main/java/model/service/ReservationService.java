package model.service;

import com.google.gson.Gson;
import model.dao.*;
import model.dao.implementation.TransactionalDaoFactory;
import model.dto.BillingDto;
import model.dto.ReservationDto;
import model.dto.ReservedTourDto;
import model.dto.ResponseDto;
import model.entity.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private TransactionalDaoFactory daoFactory;

    public ReservationService() {
        daoFactory = new TransactionalDaoFactory();
    }


    public String saveReservation(ReservationDto reservationDto) throws Exception {
        ClientDao clientDao = daoFactory.createClientDao();
        Gson gson = new Gson();
        try (ReservationDao reservationDao = daoFactory.createReservationDao()) {
            Reservation reservation = new Reservation();
            Client client = clientDao.findByUserId(reservationDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("No such client"));
            reservation.setClientId(client.getId());
            reservation.setStartDate(reservationDto.getStartDate());
            reservation.setEndDate(reservationDto.getEndDate());
            reservation.setStatus(Status.NEW);
            reservation.setTourId(reservationDto.getTourId());
            reservation.setReservationDate(LocalDate.now());
            reservationDao.create(reservation);
            return gson.toJson(new ResponseDto(true, "Reservation is successfully saved"));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseDto(false, "Reservation is not successfully saved"));
        } finally {
            clientDao.close();
        }
    }

    public List<ReservedTourDto> getAllReservations() throws Exception {

        try (ReservationDao reservationDao = daoFactory.createReservationDao()) {
            List<Reservation> reservations = reservationDao.findAll();
            List<ReservedTourDto> resultList = getReservedTourDtos(reservations);
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<ReservedTourDto> getReservedTourDtos(List<Reservation> reservations) {
        ClientDao clientDao = daoFactory.createClientDao();
        TourDao tourDao = daoFactory.createTourDao();
        List<ReservedTourDto> resultList = new ArrayList<>();
        for (Reservation r : reservations) {
            ReservedTourDto reservedTourDto = new ReservedTourDto();
            reservedTourDto.setClient(clientDao.findById(r.getClientId()));
            reservedTourDto.setTour(tourDao.findById(r.getTourId()));
            reservedTourDto.setStartDate(r.getStartDate());
            reservedTourDto.setEndDate(r.getEndDate());
            reservedTourDto.setStatus(r.getStatus());
            reservedTourDto.setReservationId(r.getId());
            resultList.add(reservedTourDto);
        }
        return resultList;
    }

    public void cancelReservation(Long id) {
        try (ReservationDao reservationDao = daoFactory.createReservationDao()) {
            Reservation reservation = reservationDao.findById(id);
            reservation.setStatus(Status.NOT_ACCEPTED);
            reservationDao.update(reservation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: TRANSACTIONAL
    public String acceptReservation(BillingDto dto) throws Exception {
        Gson gson = new Gson();
        DiscountDao discountDao = daoFactory.createDiscountDao();
        TourDao tourDao = daoFactory.createTourDao();
        BillingDao billingDao = daoFactory.createBillingDao();
        try (ReservationDao reservationDao = daoFactory.createReservationDao()) {
            Reservation reservation = reservationDao.findById(dto.getReservationId());
            reservation.setStatus(Status.ACCEPTED);
            reservationDao.update(reservation);
            Billing billing = new Billing();
            billing.setReservation(reservation);
            billing.setDiscount(discountDao.findById(dto.getDiscountId()));
            //calculate sum
            Tour tour = tourDao.findById(reservation.getTourId());
            BigDecimal sum = tour.getPrice().multiply(BigDecimal.ONE.subtract((BigDecimal.valueOf(billing.getDiscount().getDiscountPercentage()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)))).setScale(2, RoundingMode.HALF_UP);
            billing.setSum(sum);
            billingDao.create(billing);
            return gson.toJson(new ResponseDto(true, "Billing is successfully saved"));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseDto(false, "Billing is not successfully saved"));
        } finally {
            discountDao.close();
            tourDao.close();
            billingDao.close();
        }
    }

    public List<ReservedTourDto> findReservationsByUser(Long userId) {
        try(ReservationDao reservationDao = daoFactory.createReservationDao()){
            List<Reservation> reservations = reservationDao.findByUserId(userId);
           return getReservedTourDtos(reservations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
