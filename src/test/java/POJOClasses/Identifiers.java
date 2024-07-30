package POJOClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Identifiers {

	@JsonProperty("incorporationCountry")
    private String incorporationCountry;
    @JsonProperty("incorporationStateOrProvince")
    private String incorporationStateOrProvince;
    @JsonProperty("dateOfIncorporation")
    private String dateOfIncorporation;
    @JsonProperty("incorporationRegion")
    private String incorporationRegion;
    @JsonProperty("governmentIssedTaxpayerOrEmployerIdentificationNumber")
    private String governmentIssedTaxpayerOrEmployerIdentificationNumber;
    @JsonProperty("idNumberAssignedByTheCaliforniaSecretaryOfState")
    private String idNumberAssignedByTheCaliforniaSecretaryOfState;
    @JsonProperty("idNumberAssignedByIncorporatingAgency")
    private String idNumberAssignedByIncorporatingAgency;
    @JsonProperty("dunsNumber")
    private String dunsNumber;

    // Getters and Setters
    public String getIncorporationCountry() {
        return incorporationCountry;
    }

    public void setIncorporationCountry(String incorporationCountry) {
        this.incorporationCountry = incorporationCountry;
    }

    public String getIncorporationStateOrProvince() {
        return incorporationStateOrProvince;
    }

    public void setIncorporationStateOrProvince(String incorporationStateOrProvince) {
        this.incorporationStateOrProvince = incorporationStateOrProvince;
    }

    public String getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public void setDateOfIncorporation(String dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getIncorporationRegion() {
        return incorporationRegion;
    }

    public void setIncorporationRegion(String incorporationRegion) {
        this.incorporationRegion = incorporationRegion;
    }

    public String getGovernmentIssedTaxpayerOrEmployerIdentificationNumber() {
        return governmentIssedTaxpayerOrEmployerIdentificationNumber;
    }

    public void setGovernmentIssedTaxpayerOrEmployerIdentificationNumber(String governmentIssedTaxpayerOrEmployerIdentificationNumber) {
        this.governmentIssedTaxpayerOrEmployerIdentificationNumber = governmentIssedTaxpayerOrEmployerIdentificationNumber;
    }

    public String getIdNumberAssignedByTheCaliforniaSecretaryOfState() {
        return idNumberAssignedByTheCaliforniaSecretaryOfState;
    }

    public void setIdNumberAssignedByTheCaliforniaSecretaryOfState(String idNumberAssignedByTheCaliforniaSecretaryOfState) {
        this.idNumberAssignedByTheCaliforniaSecretaryOfState = idNumberAssignedByTheCaliforniaSecretaryOfState;
    }

    public String getIdNumberAssignedByIncorporatingAgency() {
        return idNumberAssignedByIncorporatingAgency;
    }

    public void setIdNumberAssignedByIncorporatingAgency(String idNumberAssignedByIncorporatingAgency) {
        this.idNumberAssignedByIncorporatingAgency = idNumberAssignedByIncorporatingAgency;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }
}
