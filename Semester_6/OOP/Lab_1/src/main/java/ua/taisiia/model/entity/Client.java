package ua.taisiia.model.entity;

import java.time.LocalDate;

public class Client {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    private LocalDate birthday;
    private Boolean regularCustomer;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getRegularCustomer() {
        return regularCustomer;
    }

    public void setRegularCustomer(Boolean regularCustomer) {
        this.regularCustomer = regularCustomer;
    }
}
