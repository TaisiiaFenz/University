package ua.taisiia.model.entity;

import java.time.LocalDate;

public class Reservation {
    private Long id;
    private Long clientId;
    private Long tourId;
    private Long discountId;
    private LocalDate departureDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    private Boolean isPaid;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long saleId) {
        this.discountId = saleId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
