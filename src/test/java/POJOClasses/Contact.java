package POJOClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
	@JsonProperty("telephoneNumber")
    private String telephoneNumber;
    @JsonProperty("ext")
    private String ext;
    @JsonProperty("mobilePhone")
    private Object mobilePhone;
    @JsonProperty("facsimileNumber")
    private String facsimileNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("websiteAddress")
    private String websiteAddress;
    @JsonProperty("consent")
    private String consent;

    // Getters and Setters
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Object getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Object mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFacsimileNumber() {
        return facsimileNumber;
    }

    public void setFacsimileNumber(String facsimileNumber) {
        this.facsimileNumber = facsimileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public String getConsent() {
        return consent;
    }

    public void setConsent(String consent) {
        this.consent = consent;
    }

}
