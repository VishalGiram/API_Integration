package POJOClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	@JsonProperty("street1")
    private String street1;
    @JsonProperty("street2")
    private String street2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("region")
    private String region;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("sameAsPhysicalAddress")
    private boolean sameAsPhysicalAddress;
    @JsonProperty("type")
    private String type;

    // Getters and Setters
    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isSameAsPhysicalAddress() {
        return sameAsPhysicalAddress;
    }

    public void setSameAsPhysicalAddress(boolean sameAsPhysicalAddress) {
        this.sameAsPhysicalAddress = sameAsPhysicalAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
