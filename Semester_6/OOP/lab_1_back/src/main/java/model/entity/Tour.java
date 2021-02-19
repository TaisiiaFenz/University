package model.entity;

import java.math.BigDecimal;

public class Tour {
    private Long id;
    private String name;
    private TourType tourType;
    private TransportType transportType;
    private String country;
    private BigDecimal price;
    private Boolean isLastMinuteTour;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getLastMinuteTour() {
        return isLastMinuteTour;
    }

    public void setLastMinuteTour(Boolean lastMinuteTour) {
        this.isLastMinuteTour = lastMinuteTour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
