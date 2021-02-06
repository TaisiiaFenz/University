package ua.taisiia.model.entity;

import java.math.BigDecimal;

public class Tour {
    private Long id;
    private TourType tourType;
    private TransportType transportType;
    private String country;
    private BigDecimal costPerTour;
    private int durationInDays;
    private Boolean lastMinuteTour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getCostPerTour() {
        return costPerTour;
    }

    public void setCostPerTour(BigDecimal costPerTour) {
        this.costPerTour = costPerTour;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public Boolean getLastMinuteTour() {
        return lastMinuteTour;
    }

    public void setLastMinuteTour(Boolean lastMinuteTour) {
        this.lastMinuteTour = lastMinuteTour;
    }
}
