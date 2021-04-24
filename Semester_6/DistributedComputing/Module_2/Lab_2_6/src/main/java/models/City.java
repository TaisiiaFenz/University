package models;

public class City {
    private int cityId;
    private String cityName;
    private int populationInThousands;
    private int countryId;

    public City() {}

    public City(String cityName, int populationInThousands, int countryId) {
        this.cityName = cityName;
        this.populationInThousands = populationInThousands;
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPopulationInThousands() {
        return populationInThousands;
    }

    public void setPopulationInThousands(int populationInThousands) {
        this.populationInThousands = populationInThousands;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
