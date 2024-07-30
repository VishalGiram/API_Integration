package RestAssuredScriptsToGenerateData;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;

import POJOClasses.Address;
import POJOClasses.Contact;
import POJOClasses.Entity;
import POJOClasses.Identifiers;
import POJOClasses.Identifiers_WA;
import POJOClasses.Mailing;
import POJOClasses.RequestBody;
import POJOClasses.RequestBody1;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Covered_Entity_Washington {
	
	private static final String BASE_URL = "https://api.qa2.wci-registry.org";
	// Access tokens
	public static String AR1; // causerb
	public static String AR2; // nsuserc
	public static String RegistrarWAToken; //RegToken

	public static void main(String[] args) {
		loginUserAR1();
//		loginUserAR2();
		LoginRegistrar();
//		EntityType();
//		DataCoveredEntity();
//		CreateFacility();
//		AddRepresentative();
//		AlternateContact();
		Auction();
		IsSubmitted();
		ApproveRequest();
		SpecialAccount();
		EntityAccounts();
		AccountNumberAAHAC();
		AccountNumberAAHAG();
		AccountNumberCompliance();
		AccountNumberGeneral();
		AccountNumberLUHA();
	}

	public static void loginUserAR1() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("wciadmin.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		RestAssured.baseURI = prop.getProperty("base.uri");
		RestAssured.config = RestAssured.config().encoderConfig(encoderConfig()
				.encodeContentTypeAs("application/x-amz-json-1.1", io.restassured.http.ContentType.JSON));
		JSONObject authParameters = new JSONObject();
		authParameters.put("USERNAME", prop.getProperty("AR1USERNAME"));
		authParameters.put("PASSWORD", prop.getProperty("AR1PASSWORD"));
		JSONObject clientMetadata = new JSONObject();
		clientMetadata.put("clientTokenCandidate", prop.getProperty("client.token"));
		JSONObject requestParams = new JSONObject();
		requestParams.put("AuthFlow", prop.getProperty("auth.flow"));
		requestParams.put("ClientId", prop.getProperty("client.id"));
		requestParams.put("AuthParameters", authParameters);
		requestParams.put("ClientMetadata", clientMetadata);
		Response response = given().header("Content-Type", prop.getProperty("content.type"))
				.header("X-Amz-Target", prop.getProperty("x.amz.target")).body(requestParams.toString()).when()
				.post("/");
		JsonPath jsonPathEvaluator = response.jsonPath();
		AR1 = jsonPathEvaluator.getString("AuthenticationResult.IdToken");
		System.out.println("AR1 AccessToken: " + AR1);
		System.setProperty("AR1", AR1);
	}

	public static void loginUserAR2() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("wciadmin.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		RestAssured.baseURI = prop.getProperty("base.uri");
		RestAssured.config = RestAssured.config().encoderConfig(encoderConfig()
				.encodeContentTypeAs("application/x-amz-json-1.1", io.restassured.http.ContentType.JSON));
		JSONObject authParameters = new JSONObject();
		authParameters.put("USERNAME", prop.getProperty("AR2USERNAME"));
		authParameters.put("PASSWORD", prop.getProperty("AR2PASSWORD"));
		JSONObject clientMetadata = new JSONObject();
		clientMetadata.put("clientTokenCandidate", prop.getProperty("client.token"));
		JSONObject requestParams = new JSONObject();
		requestParams.put("AuthFlow", prop.getProperty("auth.flow"));
		requestParams.put("ClientId", prop.getProperty("client.id"));
		requestParams.put("AuthParameters", authParameters);
		requestParams.put("ClientMetadata", clientMetadata);
		Response response = given().header("Content-Type", prop.getProperty("content.type"))
				.header("X-Amz-Target", prop.getProperty("x.amz.target")).body(requestParams.toString()).when()
				.post("/");
		JsonPath jsonPathEvaluator = response.jsonPath();
		AR2 = jsonPathEvaluator.getString("AuthenticationResult.IdToken");
		System.out.println("AR2 AccessToken: " + AR2);
		System.setProperty("AR2", AR2);
	}
	
	public static void LoginRegistrar() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("wciadmin.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		RestAssured.baseURI = prop.getProperty("base.uri");
		RestAssured.config = RestAssured.config().encoderConfig(encoderConfig()
				.encodeContentTypeAs("application/x-amz-json-1.1", io.restassured.http.ContentType.JSON));
		JSONObject authParameters = new JSONObject();
		authParameters.put("USERNAME", prop.getProperty("RegistrarWAUSERNAME"));
		authParameters.put("PASSWORD", prop.getProperty("RegistrarWAPASSWORD"));
		JSONObject clientMetadata = new JSONObject();
		clientMetadata.put("clientTokenCandidate", prop.getProperty("client.token"));
		JSONObject requestParams = new JSONObject();
		requestParams.put("AuthFlow", prop.getProperty("auth.flow"));
		requestParams.put("ClientId", prop.getProperty("client.id"));
		requestParams.put("AuthParameters", authParameters);
		requestParams.put("ClientMetadata", clientMetadata);
		Response response = given().header("Content-Type", prop.getProperty("content.type"))
				.header("X-Amz-Target", prop.getProperty("x.amz.target")).body(requestParams.toString()).when()
				.post("/");
		JsonPath jsonPathEvaluator = response.jsonPath();
		RegistrarWAToken = jsonPathEvaluator.getString("AuthenticationResult.IdToken");
		System.out.println("RegistrarWA AccessToken: " + RegistrarWAToken);
		System.setProperty("RegistrarToken", RegistrarWAToken);
	}

	public static void EntityType() {
		Response response = RestAssured.given().header("Authorization", "Bearer " + AR1).contentType(ContentType.JSON)
				.accept(ContentType.JSON).log().all() // Log
				.get(BASE_URL + "/entityCommon/api/entitytypes");
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());

		List<Map<String, Object>> dataList = response.jsonPath().getList("data");
		// Variables to store IDs
		String coveredEntityID = null;
		String gmpiEntityID = null;
		String gmpoEntityID = null;
		for (Map<String, Object> data : dataList) {
			if ("COVERED_ENTITY_COVERED_SOURCE_OR_OPT_IN_ENTITY".equals(data.get("Name"))) {
				coveredEntityID = String.valueOf(data.get("ID"));
				System.setProperty("Covered_Entity_ID", coveredEntityID);
				System.out.println("Covered_Entity_ID: " + coveredEntityID);
			}
		}
		for (Map<String, Object> data : dataList) {
			if ("GENERAL_MARKET_PARTICIPANT_INDIVIDUAL".equals(data.get("Name"))) {
				gmpiEntityID = String.valueOf(data.get("ID"));
				System.setProperty("GMPI_Entity_ID", gmpiEntityID);
				System.out.println("GMPI_Entity_ID: " + gmpiEntityID);
			}
		}
		for (Map<String, Object> data : dataList) {
			if ("GENERAL_MARKET_PARTICIPANT_ORGANIZATION".equals(data.get("Name"))) {
				gmpoEntityID = String.valueOf(data.get("ID"));
				System.setProperty("GMPO_Entity_ID", gmpoEntityID);
				System.out.println("GMPO_Entity_ID: " + gmpoEntityID);
			}
		}
	}

	public static void DataCoveredEntity() {

		// Create the request body
		Entity entity = new Entity();
		entity.setJurisdiction("8ed88405-b7de-497c-94b7-38aaef0f6293");
		entity.setEntityType("d601fc39-a224-4593-80b1-cff41777b0df");
		entity.setLegalName("california");
		entity.setOperatingName("california");

		Address physical = new Address();
		physical.setStreet1("Test");
		physical.setStreet2("test");
		physical.setCity("california");
		physical.setState("b58cfa78-42d7-4fa2-a8a9-83a405ad0bb6");
		physical.setRegion("");
		physical.setPostalCode("411052");
		physical.setCountry("3ee7cc2b-517e-4cd0-87a7-f7d991df9283");
		physical.setSameAsPhysicalAddress(true);
		physical.setType("Physical");

		Mailing mailing = new Mailing();
		mailing.setStreet1("Test");
		mailing.setStreet2("test");
		mailing.setCity("california");
		mailing.setState("b58cfa78-42d7-4fa2-a8a9-83a405ad0bb6");
		mailing.setRegion("");
		mailing.setPostalCode("411052");
		mailing.setCountry("3ee7cc2b-517e-4cd0-87a7-f7d991df9283");
		mailing.setSameAsPhysicalAddress(true);
		mailing.setType("Mailing");

		Contact entityContact = new Contact();
		entityContact.setTelephoneNumber("(123)98777777");
		entityContact.setExt("");
		entityContact.setMobilePhone(null);
		entityContact.setFacsimileNumber("");
		entityContact.setEmail("ab2@gmail.com");
		entityContact.setWebsiteAddress("");
		entityContact.setConsent("consent");

		Identifiers_WA identifiers = new Identifiers_WA();
        identifiers.setIncorporationCountry("3ee7cc2b-517e-4cd0-87a7-f7d991df9283");
        identifiers.setIncorporationStateOrProvince("b58cfa78-42d7-4fa2-a8a9-83a405ad0bb6");
        identifiers.setDateOfIncorporation("2024-07-18T18:30:00.000Z");
        identifiers.setIncorporationRegion("");
        identifiers.setIdNumberAssignedByIncorporatingAgency("2356849684");
        identifiers.setDunsNumber("5689745632");
        identifiers.setWashingtonUnifiedBusinessIdentifierNumberUBI("5647896523");

		RequestBody1 requestBody = new RequestBody1();
		requestBody.setEntity(entity);
		requestBody.setPhysical(physical);
		requestBody.setMailing(mailing);
		requestBody.setEntityContact(entityContact);
		requestBody.setIdentifiers(identifiers);

		RestAssured.baseURI = "https://api.qa2.wci-registry.org";
		Response response = given().header("authorization", "Bearer " + AR1)
				.header("component-name", "entity,identifiers,physical,mailing,entityContact")
				.header("content-type", "application/json").header("page-name", "Page_EntityRegistration")
				.body(requestBody).when().post("/entityCommon/api/entity").then().log().all().assertThat().statusCode(200).and()
				.body("messageKey", equalTo("SUCCESS")).extract().response();

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
		String dataId = response.jsonPath().getString("data");
		System.out.println("Extracted Data ID: " + dataId);
		System.setProperty("Entity_ID", dataId);
	}

	public static void CreateFacility() {
		String jsonBody = "[\r\n" + "    {\r\n" + "        \"facilityPhysicalAddress\": {\r\n"
				+ "            \"street1\": \"Test\",\r\n" + "            \"street2\": \"test\",\r\n"
				+ "            \"city\": \"california\",\r\n"
				+ "            \"state\": \"b58cfa78-42d7-4fa2-a8a9-83a405ad0bb6\",\r\n"
				+ "            \"region\": null,\r\n" + "            \"postalCode\": \"411052\",\r\n"
				+ "            \"country\": \"3ee7cc2b-517e-4cd0-87a7-f7d991df9283\",\r\n"
				+ "            \"sameAsPhysicalAddress\": true,\r\n" + "            \"type\": \"Physical\"\r\n"
				+ "        },\r\n" + "        \"facilityMailingAddress\": {\r\n"
				+ "            \"street1\": \"Test\",\r\n" + "            \"street2\": \"test\",\r\n"
				+ "            \"city\": \"california\",\r\n"
				+ "            \"state\": \"b58cfa78-42d7-4fa2-a8a9-83a405ad0bb6\",\r\n"
				+ "            \"region\": null,\r\n" + "            \"postalCode\": \"411052\",\r\n"
				+ "            \"country\": \"3ee7cc2b-517e-4cd0-87a7-f7d991df9283\",\r\n"
				+ "            \"sameAsPhysicalAddress\": true,\r\n" + "            \"type\": \"Mailing\"\r\n"
				+ "        },\r\n" + "        \"facilityContact\": {\r\n" + "            \"mobileNumber\": null,\r\n"
				+ "            \"telephoneNumber\": null,\r\n" + "            \"firstName\": \"\",\r\n"
				+ "            \"lastName\": \"\",\r\n" + "            \"emailAddress\": \"\",\r\n"
				+ "            \"jobTitle\": \"\",\r\n" + "            \"statusId\": null\r\n" + "        },\r\n"
				+ "        \"facility\": {\r\n" + "            \"entityId\": \"8053bffb-f51d-4e1d-9449-4ad16715db18\",\r\n"
				+ "            \"isFacility\": false,\r\n" + "            \"isUseEntity\": false,\r\n"
				+ "            \"naicsCode\": \"\",\r\n"
				+ "            \"ghgEmissionsReportingId\": \"98756258\",\r\n"
				+ "            \"facilityStatus\": null,\r\n" + "            \"facilityName\": \"california\",\r\n"
				+ "            \"operatingName\": \"california\",\r\n"
				+ "            \"entityType\": \"d601fc39-a224-4593-80b1-cff41777b0df\"\r\n" + "        }\r\n"
				+ "    }\r\n" + "]";

		// Replace placeholder with property values
		StringSubstitutor sub = new StringSubstitutor();
		String replacedJsonBody = sub.replace(jsonBody);

		// Use the replaced JSON body with REST Assured
		RestAssured.given().contentType(ContentType.JSON).header("accept", "application/json, text/plain, */*")
				.header("component-name", "facility,facilityPhysicalAddress,facilityMailingAddress,facilityContact")
				.header("content-type", "application/json")
				.header("page-name", "Page_EntityRegistration").header("priority", "u=1, i")
				.header("authorization", "Bearer " + AR1).body(replacedJsonBody)
				.post("https://api.qa2.wci-registry.org/entityCommon/api/facility").then().log().all().assertThat()
				.statusCode(200).and().body("messageKey", equalTo("FACILITY_ADDED_SUCCESSFULLY")).extract().response();
	}
	
	public static void AddRepresentative() {
					
		 String jsonBody = "{\r\n" +
		            "  \"representative\": [\r\n" +
		            "    {\r\n" +
		            "      \"role\": \"4bb95c9d-c8cd-48c9-9eec-39bcd9854a31\",\r\n" +
		            "      \"userReferenceCode\": \"OGE16HCCRXZG\",\r\n" +
		            "      \"isAllowedAccess\": true,\r\n" +
		            "      \"name\": \"causerb Testone\",\r\n" +
		            "      \"entityId\": \"8053bffb-f51d-4e1d-9449-4ad16715db18\",\r\n" +
		            "      \"email\": \"test123456789@eqanim-qa.wci-validate.org\",\r\n" +
		            "      \"status\": \"21533c59-af85-4d29-af8a-1d97508e211e\"\r\n" +
		            "    },\r\n" +
		            "    {\r\n" +
		            "      \"role\": \"5152cc54-cb46-43bb-b019-9131884dc358\",\r\n" +
		            "      \"userReferenceCode\": \"2WZ0Y41EGCOI\",\r\n" +
		            "      \"isAllowedAccess\": false,\r\n" +
		            "      \"name\": \"wausera\",\r\n" +
		            "      \"entityId\": \"8053bffb-f51d-4e1d-9449-4ad16715db18\",\r\n" +
		            "      \"email\": \"wausera@eqanim-qa.wci-validate.org\",\r\n" +
		            "      \"status\": \"21533c59-af85-4d29-af8a-1d97508e211e\"\r\n" +
		            "    }\r\n" +
		            "  ]\r\n" +
		            "}";

		        // Define the headers
		        Map<String, String> headers = new HashMap<>();
		        headers.put("accept", "application/json, text/plain, */*");
		        headers.put("accept-language", "en-US,en;q=0.9,fr;q=0.8,en-IN;q=0.7");
		        headers.put("component-name", "representative");
		        headers.put("content-type", "application/json");
		        headers.put("page-name", "Page_EntityRegistration");
		        headers.put("authorization", "Bearer " + AR1);

		        // Send the POST request
		        Response response = RestAssured
		                .given()
		                .headers(headers)
		                .body(jsonBody)
		                .contentType(ContentType.JSON)
		                .post("https://api.qa2.wci-registry.org/entityCommon/api/representatives")

		        .then().log().all().assertThat()
				.statusCode(200).and().body("messageKey", equalTo("REPRESENTATIVE_ADDED_SUCCESSFULLY_AND_MAIL_SENT_TO_AR")).extract().response();
	}
		       
	public static void AlternateContact() {
		
		String jsonBody = "{\r\n" +
                "  \"contact\": {\r\n" +
                "    \"firstName\": \"test\",\r\n" +
                "    \"lastName\": \"test\",\r\n" +
                "    \"position\": \"Tester\",\r\n" +
                "    \"mobilePhone\": \"2356849658\",\r\n" +
                "    \"telephoneNumber\": \"8569745236\",\r\n" +
                "    \"email\": \"test@gmail.com\",\r\n" +
                "    \"entityId\": \"8053bffb-f51d-4e1d-9449-4ad16715db18\"\r\n" +
                "  }\r\n" +
                "}";

        	// Define the headers
		        Map<String, String> headers = new HashMap<>();
		        headers.put("accept", "application/json, text/plain, */*");
		        headers.put("page-name", "Page_EntityRegistration");
		        headers.put("content-type", "application/json");
		        headers.put("component-name", "contact");
		        headers.put("Authorization", "Bearer " + AR1);

		     // Send the POST request
		        Response response = RestAssured
		                .given()
		                .headers(headers)
		                .body(jsonBody)
		                .contentType(ContentType.JSON)
		                .post("https://api.qa2.wci-registry.org/entityCommon/api/alternatecontact")

		        .then().log().all().assertThat()
				.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
		}
	
	public static void Auction() {
		
		 String jsonBody = "{\r\n" +
	                "  \"is_auction\": \"true\"\r\n" +
	                "}";

	        // Define the headers
	        Map<String, String> headers = new HashMap<>();
	        headers.put("accept", "application/json, text/plain, */*");
	        headers.put("content-type", "application/json");
	        headers.put("Authorization", "Bearer " + AR1);

	     // Send the POST request
	        Response response = RestAssured
	                .given()
	                .headers(headers)
	                .body(jsonBody)
	                .contentType(ContentType.JSON)
	                .put("https://api.qa2.wci-registry.org/entityCommon/api/auction/8053bffb-f51d-4e1d-9449-4ad16715db18")

	        .then().log().all().assertThat()
			.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	    }
		
	public static void IsSubmitted() {
		
		String jsonBody = "{}";
		
		 // Define the headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + AR1);

     // Send the POST request
        Response response = RestAssured
                .given()
                .headers(headers)
                .body(jsonBody)
                .contentType(ContentType.JSON)
                .put("https://api.qa2.wci-registry.org/entityCommon/api/isSubmitted/8053bffb-f51d-4e1d-9449-4ad16715db18")

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("APPLICATION_SUBMITTED_SUCCESSFULLY")).extract().response();
		
	}
	public static void ApproveRequest() {
		
		String jsonBody = "{\r\n" +
                "  \"entityId\": \"8053bffb-f51d-4e1d-9449-4ad16715db18\",\r\n" +
                "  \"comment\": \"\",\r\n" +
                "  \"requestStatusId\": \"31c57b95-ec68-4588-a17d-1b4601e485b9\",\r\n" +
                "  \"statusId\": \"a6ce56e5-cf22-43b2-adad-a8e0f507a24b\",\r\n" +
                "  \"entityAnnualAllocationId\": \"44a0c4b6-39e4-4d3d-89b9-4ad5a72348ed\",\r\n" +
                "  \"entitySpecialPurposeId\": \"a423cc66-471c-496b-96e2-0eb4f1b8b415\"\r\n" +
                "}";
		
		// Define the headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + RegistrarWAToken);

     // Send the POST request
        Response response = RestAssured
                .given()
                .headers(headers)
                .body(jsonBody)
                .contentType(ContentType.JSON)
                .put("https://api.qa2.wci-registry.org/entityCommon/api/requeststatus")

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("APPROVE_STATUS_UPDATED")).extract().response();
			
	}
	
	public static void SpecialAccount() {
		
		String jsonBody = "{\r\n" +
                "  \"entityAnnualAllocationId\": \"97f85efc-3b3c-4ffe-8e9a-dd150826f1e4\",\r\n" +
                "  \"entitySpecialPurposeId\": \"\"\r\n" +
                "}";
		
		// Define the headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + RegistrarWAToken);

     // Send the POST request
        Response response = RestAssured
                .given()
                .headers(headers)
                .body(jsonBody)
                .contentType(ContentType.JSON)
                .post("https://api.qa2.wci-registry.org/entityCommon/api/special-accounts/8053bffb-f51d-4e1d-9449-4ad16715db18")

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("ACCOUNT_ADDED_SUCCESSFULLY")).extract().response();
		
	}
	
	private static String cAnnualAllocationId1;
	private static String complianceId1;
	private static String generalId1;
	private static String gAnnualAllocationId1;
	private static String luhaId1;
	
	public static void EntityAccounts() {
		
		// Define the headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + RegistrarWAToken);

     // Send the POST request
        Response response = RestAssured
                .given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/8053bffb-f51d-4e1d-9449-4ad16715db18/accounts")

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
        
        JsonPath jsonPath = response.jsonPath();
        Map<String, String> accountIds = new HashMap<>();
        jsonPath.getList("data").forEach(account -> {
            String accountType = ((Map<String, String>) account).get("account_type");
            String accountId = ((Map<String, String>) account).get("id");
            
            System.out.println("Account ID-" +accountId);

            switch (accountType) {
                case "C_ANNUAL_ALLOCATION_HOLDING":
                	accountIds.put("C_ANNUAL_ALLOCATION_HOLDING_ID1", accountId);
                	cAnnualAllocationId1 = accountId;
                	System.out.println("cAnnualAllocationId1-" +cAnnualAllocationId1);
                    break;
                case "COMPLIANCE":
                	accountIds.put("COMPLIANCE_ID1", accountId);
                	complianceId1 = accountId;
                	System.out.println("complianceId1-" +complianceId1);
                    break;
                case "GENERAL":
                	accountIds.put("GENERAL_ID1", accountId);
                	generalId1 = accountId;
                	System.out.println("generalId1-" +generalId1);
                    break;
                case "G_ANNUAL_ALLOCATION_HOLDING":
                	accountIds.put("G_ANNUAL_ALLOCATION_HOLDING_ID1", accountId);
                	gAnnualAllocationId1 = accountId;
                	System.out.println("gAnnualAllocationId1-" +gAnnualAllocationId1);
                    break;
                case "LIMITED_USE_HOLDING_ACCOUNT":
                	accountIds.put("LIMITED_USE_HOLDING_ACCOUNT_ID1", accountId);
                	luhaId1 = accountId;
                	System.out.println("luhaId1-" +luhaId1);
                    break;
                default:
                    System.out.println("Unknown account type: " + accountType);
            }
        });

        accountIds.forEach((key, value) -> System.out.println(key + ": " + value));
	}
	
	public static void AccountNumberAAHAC() {
		// Define the headers
	    Map<String, String> headers = new HashMap<>();
	    headers.put("accept", "application/json, text/plain, */*");
	    headers.put("content-type", "application/json");
	    headers.put("Authorization", "Bearer " + RegistrarWAToken);
	    headers.put("page-name", "Page_AccountDetails");
	    headers.put("component-name", "accountInformation");
	
	 // Send the POST request
	    Response response = RestAssured
	            .given()
	            .headers(headers)
	            .contentType(ContentType.JSON)
	            .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/accounts/"+ cAnnualAllocationId1)
	
	    .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	    
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> data = jsonPath.getMap("data");
        String accountNumber = (String) data.get("accountNumber");
        System.setProperty("AAHAC1", accountNumber);
        String storedAccountNumber = System.getProperty("AAHAC1");
        System.out.println("Stored Account Number: " + storedAccountNumber);
	}
	
	public static void AccountNumberAAHAG() {
		// Define the headers
	    Map<String, String> headers = new HashMap<>();
	    headers.put("accept", "application/json, text/plain, */*");
	    headers.put("content-type", "application/json");
	    headers.put("Authorization", "Bearer " + RegistrarWAToken);
	    headers.put("page-name", "Page_AccountDetails");
	    headers.put("component-name", "accountInformation");
	
	 // Send the POST request
	    Response response = RestAssured
	            .given()
	            .headers(headers)
	            .contentType(ContentType.JSON)
	            .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/accounts/"+ gAnnualAllocationId1)
	
	    .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	    
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> data = jsonPath.getMap("data");
        String accountNumber = (String) data.get("accountNumber");
        System.setProperty("AAHAG1", accountNumber);
        String storedAccountNumber = System.getProperty("AAHAG1");
        System.out.println("Stored Account Number: " + storedAccountNumber);
	}
	
	public static void AccountNumberCompliance() {
		// Define the headers
	    Map<String, String> headers = new HashMap<>();
	    headers.put("accept", "application/json, text/plain, */*");
	    headers.put("content-type", "application/json");
	    headers.put("Authorization", "Bearer " + RegistrarWAToken);
	    headers.put("page-name", "Page_AccountDetails");
	    headers.put("component-name", "accountInformation");
	
	 // Send the POST request
	    Response response = RestAssured
	            .given()
	            .headers(headers)
	            .contentType(ContentType.JSON)
	            .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/accounts/"+ complianceId1)
	
	    .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	    
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> data = jsonPath.getMap("data");
        String accountNumber = (String) data.get("accountNumber");
        System.setProperty("Compliance1", accountNumber);
        String storedAccountNumber = System.getProperty("Compliance1");
        System.out.println("Stored Account Number: " + storedAccountNumber);
	}
	
	public static void AccountNumberGeneral() {
		// Define the headers
	    Map<String, String> headers = new HashMap<>();
	    headers.put("accept", "application/json, text/plain, */*");
	    headers.put("content-type", "application/json");
	    headers.put("Authorization", "Bearer " + RegistrarWAToken);
	    headers.put("page-name", "Page_AccountDetails");
	    headers.put("component-name", "accountInformation");
	
	 // Send the POST request
	    Response response = RestAssured
	            .given()
	            .headers(headers)
	            .contentType(ContentType.JSON)
	            .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/accounts/"+ generalId1)
	
	    .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	    
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> data = jsonPath.getMap("data");
        String accountNumber = (String) data.get("accountNumber");
        System.setProperty("General1", accountNumber);
        String storedAccountNumber = System.getProperty("General1");
        System.out.println("Stored Account Number: " + storedAccountNumber);
	}
	
	public static void AccountNumberLUHA() {
		// Define the headers
	    Map<String, String> headers = new HashMap<>();
	    headers.put("accept", "application/json, text/plain, */*");
	    headers.put("content-type", "application/json");
	    headers.put("Authorization", "Bearer " + RegistrarWAToken);
	    headers.put("page-name", "Page_AccountDetails");
	    headers.put("component-name", "accountInformation");
	
	 // Send the POST request
	    Response response = RestAssured
	            .given()
	            .headers(headers)
	            .contentType(ContentType.JSON)
	            .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/accounts/"+ luhaId1)
	
	    .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	    
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> data = jsonPath.getMap("data");
        String accountNumber = (String) data.get("accountNumber");
        System.setProperty("LUHA1", accountNumber);
        String storedAccountNumber = System.getProperty("LUHA1");
        System.out.println("Stored Account Number: " + storedAccountNumber);
	}

}
