package models;

public class Country {
    private int countryId;
    private String countryName;
    private String capital;

    public Country() {}

    public Country(String countryName, String capital) {
        this.countryName = countryName;
        this.capital = capital;
    }

    public Country(int countryId, String countryName, String capital) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.capital = capital;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

}
