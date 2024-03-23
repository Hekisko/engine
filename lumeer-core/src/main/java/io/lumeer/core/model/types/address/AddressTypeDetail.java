package io.lumeer.core.model.types.address;

import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.*;

public class AddressTypeDetail {

    private boolean city;
    private boolean country;
    private boolean county;
    private boolean houseNumber;
    private boolean postalCode;
    private boolean state;
    private boolean street;

    public AddressTypeDetail() {
    }

    public AddressTypeDetail(boolean city, boolean country, boolean county, boolean houseNumber, boolean postalCode, boolean state, boolean street) {
        this.city = city;
        this.country = country;
        this.county = county;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.state = state;
        this.street = street;
    }

    public boolean isCity() {
        return city;
    }

    public void setCity(boolean city) {
        this.city = city;
    }

    public boolean isCountry() {
        return country;
    }

    public void setCountry(boolean country) {
        this.country = country;
    }

    public boolean isCounty() {
        return county;
    }

    public void setCounty(boolean county) {
        this.county = county;
    }

    public boolean isHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(boolean houseNumber) {
        this.houseNumber = houseNumber;
    }

    public boolean isPostalCode() {
        return postalCode;
    }

    public void setPostalCode(boolean postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isStreet() {
        return street;
    }

    public void setStreet(boolean street) {
        this.street = street;
    }


    public Object getConstraints() {
        List<String> addressFields = new ArrayList<>();
        ifTrueAdd(houseNumber, "houseNumber", addressFields);
        ifTrueAdd(street, "street", addressFields);
        ifTrueAdd(postalCode, "postalCode", addressFields);
        ifTrueAdd(city, "city", addressFields);
        ifTrueAdd(country, "country", addressFields);
        ifTrueAdd(state, "state", addressFields);
        ifTrueAdd(county, "county", addressFields);

        Map<String, String[]> resultMap = new HashMap<>();

        resultMap.put("fields", addressFields.toArray(new String[0]));

        return resultMap;
    }

    private void ifTrueAdd(boolean value, String attributeStr, List<String> list) {
        if (value) {
            list.add(attributeStr);
        }
    }

    @Override
    public String toString() {
        return "AddressTypeDetail{" +
                "city=" + city +
                ", country=" + country +
                ", county=" + county +
                ", houseNumber=" + houseNumber +
                ", postalCode=" + postalCode +
                ", state=" + state +
                ", street=" + street +
                '}';
    }
}
