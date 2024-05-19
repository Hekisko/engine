package io.lumeer.core.model.types.address;

public class AddressExample {

    private String city;
    private String country;
    private String county;
    private String houseNumber;
    private String postalCode;
    private String state;
    private String street;

    public AddressExample() {
        this("Brno", null, null, null, null, null, "Botanická");
    }

    public AddressExample(String city, String country, String county, String houseNumber, String postalCode, String state, String street) {
        this.city = city;
        this.country = country;
        this.county = county;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.state = state;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressExample{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", county='" + county + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
