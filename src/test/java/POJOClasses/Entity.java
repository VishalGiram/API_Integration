package POJOClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity {
	@JsonProperty("jurisdiction")
    private String jurisdiction;
    @JsonProperty("entityType")
    private String entityType;
    @JsonProperty("legalName")
    private String legalName;
    @JsonProperty("operatingName")
    private String operatingName;
    private String typeOfOrganization;

    // Getters and Setters
    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getOperatingName() {
        return operatingName;
    }

    public void setOperatingName(String operatingName) {
        this.operatingName = operatingName;
    }
    public String getTypeOfOrganization() {
        return typeOfOrganization;
    }

    public void setTypeOfOrganization(String typeOfOrganization) {
        this.typeOfOrganization = typeOfOrganization;
    }

}
