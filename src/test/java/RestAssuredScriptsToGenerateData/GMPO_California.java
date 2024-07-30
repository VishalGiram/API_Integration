package RestAssuredScriptsToGenerateData;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.text.StringSubstitutor;
import org.json.JSONObject;

import POJOClasses.Address;
import POJOClasses.AllowanceIssuanceInformation;
import POJOClasses.AllowanceIssueanceApprove;
import POJOClasses.ApproveBatch;
import POJOClasses.AddBatch;
import POJOClasses.BatchTransferRequest;
import POJOClasses.Contact;
import POJOClasses.Entity;
import POJOClasses.Identifiers;
import POJOClasses.Mailing;
import POJOClasses.ProposeBatch;
import POJOClasses.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GMPO_California {
	
	private static final String BASE_URL = "https://api.qa2.wci-registry.org";
	// Access tokens
	public static String AR1; // causerb
	public static String AR2; // nsuserc
	public static String RegistrarToken; //RegToken
	public static String ACCESS_TOKEN1; // Juriadmin
	public static String ACCESS_TOKEN2; // Authority

	public static void main(String[] args) {
//		loginUserAR1();
//		loginUserAR2();
		LoginRegistrar();
		loginUserJuriAdmin();
		loginUserAuthority();
//		EntityType();
//		DataGMPOEntity();
//		AddRepresentative();
//		AlternateContact();
//		IsSubmitted();
//		ApproveRequest();
		EntityAccounts();
		AccountNumberECHA();
		jurisdictionAccounts();
//		proposeAllowanceIssuance();
//		approveAllowance();
		Holdings();
		TransferType();
		CreateBatch();
		HoldingDetails();
		AddBatchRecord();
		ProposeBatchRecord();
		ApproveBatchRecord();	}

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
		authParameters.put("USERNAME", prop.getProperty("RegistrarUSERNAME"));
		authParameters.put("PASSWORD", prop.getProperty("RegistrarPASSWORD"));
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
		RegistrarToken = jsonPathEvaluator.getString("AuthenticationResult.IdToken");
		System.out.println("Registrar AccessToken: " + RegistrarToken);
		System.setProperty("RegistrarToken", RegistrarToken);
	}
	
	public static void loginUserJuriAdmin() {
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
		authParameters.put("USERNAME", prop.getProperty("JuriadminUSERNAME"));
		authParameters.put("PASSWORD", prop.getProperty("JuriadminPASSWORD"));
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
		ACCESS_TOKEN1 = jsonPathEvaluator.getString("AuthenticationResult.IdToken");
		System.out.println("Juriadmin AccessToken: " + ACCESS_TOKEN1);
		System.setProperty("ACCESS_TOKEN1", ACCESS_TOKEN1);
	}
	
	public static void loginUserAuthority() {
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
		authParameters.put("USERNAME", prop.getProperty("AuthorityUserName"));
		authParameters.put("PASSWORD", prop.getProperty("AuthorityPassword"));
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
		ACCESS_TOKEN2 = jsonPathEvaluator.getString("AuthenticationResult.IdToken");
		System.out.println("Authority AccessToken: " + ACCESS_TOKEN2);
		System.setProperty("ACCESS_TOKEN2", ACCESS_TOKEN2);
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
//				System.setProperty("GMPO_Entity_ID", gmpoEntityID);
				System.out.println("GMPO_Entity_ID: " + gmpoEntityID);
			}
		}
	}

	public static void DataGMPOEntity() {

		// Create the request body
		Entity entity = new Entity();
		entity.setJurisdiction("25639c51-f558-440b-90ab-ee496dd32f27");
		entity.setEntityType("bd71a69e-a521-4ca1-a7ac-7a0b11155b2a");
		entity.setLegalName("california");
		entity.setOperatingName("california");
		entity.setTypeOfOrganization("7b01e3d1-15ba-41d2-a196-22d466934325");

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

		Identifiers identifiers = new Identifiers();
		identifiers.setIncorporationCountry("3ee7cc2b-517e-4cd0-87a7-f7d991df9283");
		identifiers.setIncorporationStateOrProvince("b58cfa78-42d7-4fa2-a8a9-83a405ad0bb6");
		identifiers.setDateOfIncorporation("2024-07-18T18:30:00.000Z");
		identifiers.setIncorporationRegion("");
		identifiers.setGovernmentIssedTaxpayerOrEmployerIdentificationNumber("california");
		identifiers.setIdNumberAssignedByTheCaliforniaSecretaryOfState("3434534343434");
		identifiers.setIdNumberAssignedByIncorporatingAgency("411052");
		identifiers.setDunsNumber("54654656565");

		RequestBody requestBody = new RequestBody();
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
	
	public static void AddRepresentative() {
					
		 String jsonBody = "{\r\n" +
		            "  \"representative\": [\r\n" +
		            "    {\r\n" +
		            "      \"role\": \"4bb95c9d-c8cd-48c9-9eec-39bcd9854a31\",\r\n" +
		            "      \"userReferenceCode\": \"OGE16HCCRXZG\",\r\n" +
		            "      \"isAllowedAccess\": true,\r\n" +
		            "      \"name\": \"causerb Testone\",\r\n" +
		            "      \"entityId\": \"5657a087-fd70-4576-adec-bf6492dcdab2\",\r\n" +
		            "      \"email\": \"test123456789@eqanim-qa.wci-validate.org\",\r\n" +
		            "      \"status\": \"21533c59-af85-4d29-af8a-1d97508e211e\"\r\n" +
		            "    },\r\n" +
		            "    {\r\n" +
		            "      \"role\": \"5152cc54-cb46-43bb-b019-9131884dc358\",\r\n" +
		            "      \"userReferenceCode\": \"2WZ0Y41EGCOI\",\r\n" +
		            "      \"isAllowedAccess\": false,\r\n" +
		            "      \"name\": \"NovaScotia User\",\r\n" +
		            "      \"entityId\": \"5657a087-fd70-4576-adec-bf6492dcdab2\",\r\n" +
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
                "    \"entityId\": \"5657a087-fd70-4576-adec-bf6492dcdab2\"\r\n" +
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
                .put("https://api.qa2.wci-registry.org/entityCommon/api/isSubmitted/5657a087-fd70-4576-adec-bf6492dcdab2")

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("APPLICATION_SUBMITTED_SUCCESSFULLY")).extract().response();
		
	}
	
	public static void ApproveRequest() {
		
		String jsonBody = "{\r\n" +
                "  \"entityId\": \"5657a087-fd70-4576-adec-bf6492dcdab2\",\r\n" +
                "  \"comment\": \"\",\r\n" +
                "  \"requestStatusId\": \"31c57b95-ec68-4588-a17d-1b4601e485b9\",\r\n" +
                "  \"statusId\": \"a6ce56e5-cf22-43b2-adad-a8e0f507a24b\",\r\n" +
                "  \"entitySpecialPurposeId\": \"69d36f6f-fbe7-403d-ba66-d75dfbd775e9\"\r\n" +
                "}";
		
		// Define the headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + RegistrarToken);

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
	
	private static String ECHAID;
	
public static void EntityAccounts() {
		
		// Define the headers
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + RegistrarToken);

     // Send the POST request
        Response response = RestAssured
                .given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/5657a087-fd70-4576-adec-bf6492dcdab2/accounts")

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
        
        JsonPath jsonPath = response.jsonPath();
        String accountId = jsonPath.getString("data[0].id");

        // Check if account ID is not null or empty and print the result
        if (accountId != null && !accountId.isEmpty()) {
        	ECHAID = accountId;
            System.out.println("ECHA ID: " + ECHAID);
        } else {
            System.out.println("Account ID is missing or invalid.");
        }
	}

private static String ECHA1;
	public static void AccountNumberECHA() {
		// Define the headers
	    Map<String, String> headers = new HashMap<>();
	    headers.put("accept", "application/json, text/plain, */*");
	    headers.put("content-type", "application/json");
	    headers.put("Authorization", "Bearer " + RegistrarToken);
	    headers.put("page-name", "Page_AccountDetails");
	    headers.put("component-name", "accountInformation");
	
	 // Send the POST request
	    Response response = RestAssured
	            .given()
	            .headers(headers)
	            .contentType(ContentType.JSON)
	            .get("https://api.qa2.wci-registry.org/entityCommon/api/entity/accounts/"+ ECHAID)
	
	    .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	    
	    JsonPath jsonPath = response.jsonPath();
	    Map<String, Object> data = jsonPath.getMap("data");
	    String accountNumber = (String) data.get("accountNumber");
	    ECHA1 = accountNumber;
	    System.out.println("Batch Transfer Issuance Account: " + ECHA1);
		System.setProperty("ECHA1", ECHA1);
	}
	
	private static String IssuanceAccount;
	public static void jurisdictionAccounts() {
		Response response = RestAssured.given().header("Authorization", "Bearer " + ACCESS_TOKEN1)
				.header("component-name", "jurisdictionAccounts").header("page-name", "Page_JurisdictionAccounts")
				.contentType(ContentType.JSON).accept(ContentType.JSON).log().all() // Log
				.get(BASE_URL+"/issuance/api/compliance/jurisdictionAccounts/25639c51-f558-440b-90ab-ee496dd32f27");
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
		List<Map<String, Object>> data = response.jsonPath().getList("data");
		for (Map<String, Object> account : data) {
			if ("Issuance".equals(account.get("accountType"))) {
				String accountNumber = (String) account.get("accountNumber");
				IssuanceAccount = accountNumber;
				System.out.println("Batch Transfer Issuance Account: " + IssuanceAccount);
				System.setProperty("IssuanceAccount", IssuanceAccount);
			}else {
				System.out.println("Account not found");
			}
		}
	}

	private static String transferId2;
	public static void proposeAllowanceIssuance() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("wciadmin.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AllowanceIssuanceInformation allowanceIssuance = new AllowanceIssuanceInformation();
		allowanceIssuance.setQuantity("10");
		allowanceIssuance.setVintageYear(prop.getProperty("budgetYear"));
		allowanceIssuance.setComment("Propose");
		allowanceIssuance.setAccountType("Issuance");
		Response response = RestAssured.given().header("Authorization", "Bearer " + ACCESS_TOKEN1)
				.header("component-name", "allowanceIssuanceInformation").header("page-name", "Page_AllowanceIssuance")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(allowanceIssuance).post(BASE_URL+"/issuance/api/compliance/proposeAllowanceIssuance/25639c51-f558-440b-90ab-ee496dd32f27");
		response.then().log().all();
		transferId2 = response.jsonPath().getString("data.transfer_id");
		System.out.println("Transfer ID: " + transferId2);
		System.setProperty("transferId2", transferId2);
	}

	public static void approveAllowance() {
		AllowanceIssueanceApprove approveAllowance = new AllowanceIssueanceApprove();
		approveAllowance.setjurisdictionid("25639c51-f558-440b-90ab-ee496dd32f27");
		approveAllowance.setissuanceType("Allowance");
		approveAllowance.setComment("Approve");
		Response response = RestAssured.given().header("Authorization", ACCESS_TOKEN2)
				.header("Content-Type", "application/json")
				.body(approveAllowance).post(BASE_URL+"/issuance/api/compliance/approveAllowanceIssuance/"+ System.getProperty("transferId2"));
				response.then().log().all();
	}
	
	private static String Batch_HoldingID;
	public static void Holdings() {
		Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + ACCESS_TOKEN1);
        headers.put("page-name", "Page_ComplianceAccountDetails");
        headers.put("component-name", "holdings");

        Response response = RestAssured
                .given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("https://api.qa2.wci-registry.org/issuance/api/compliance/holdings/"+ IssuanceAccount)

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
        
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> dataList = jsonPath.getList("data");
        List<String> validIds = new ArrayList<>();
        for (Map<String, Object> item : dataList) {
            Integer vintage = item.get("vintage") != null ? (Integer) item.get("vintage") : null;
            String type = item.get("type") != null ? item.get("type").toString() : null;

            if (vintage != null && vintage == 2024 && "Allowance".equals(type)) {
                validIds.add(item.get("id").toString());
            }
        }
        String validIdsStr = String.join(",", validIds);
        Batch_HoldingID = validIdsStr;
        System.out.println("Batch_HoldingID: " + Batch_HoldingID);
        System.setProperty("Batch_HoldingID", Batch_HoldingID);
	}
	
	private static String TransferTypeId;
	public static void TransferType() {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + ACCESS_TOKEN1);

        Response response = RestAssured
                .given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .get("https://api.qa2.wci-registry.org/transfers/api/batchTransfer/getTransferTypeDetails?category=BATCH_TRANSFER")

        .then().log().all().assertThat()
		.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
        
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> dataList = jsonPath.getList("data");
        for (Map<String, Object> data : dataList) {
            if ("JURISDICTION_BATCH_TRANSFER".equals(data.get("name"))) {
                String batchTransferTypeId = data.get("id").toString();
                TransferTypeId = batchTransferTypeId;
                System.out.println("Batch_transferTypeId: " + TransferTypeId);
                System.setProperty("TransferTypeId", TransferTypeId);
                break;
            }else {
            	System.out.println("TransferTypeId not found");
            }
        }
	}
	
	private static String Batch_Transfer_ID;
	public static void CreateBatch() {
		BatchTransferRequest batchTransferRequest = new BatchTransferRequest();
        batchTransferRequest.setBatchName("Batch");
        batchTransferRequest.setComment("Saved");
        batchTransferRequest.setSelectedHoldingIds(Arrays.asList(System.getProperty("Batch_HoldingID")));
        batchTransferRequest.setAccountNumber(System.getProperty("IssuanceAccount"));
        batchTransferRequest.setTransferTypeId(System.getProperty("TransferTypeId"));
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + ACCESS_TOKEN1);
        Response response = given()
            .headers(headers)
            .contentType(ContentType.JSON)
            .body(batchTransferRequest).log().all()
            .post("https://api.qa2.wci-registry.org/transfers/api/batchTransfer/jurisdiction/25639c51-f558-440b-90ab-ee496dd32f27/batchTransferDetail")
            .then().log().all().assertThat().statusCode(200).body("messageKey", equalTo("SUCCESS")).extract()
            .response();
        String id = response.jsonPath().getString("data.id");
        Batch_Transfer_ID = id;
        System.out.println("Batch Transfer ID: " + Batch_Transfer_ID);	
	}
	
	private static String batchTransferHoldingId1;
	public static void HoldingDetails() {
		 Map<String, String> headers = new HashMap<>();
	        headers.put("accept", "application/json, text/plain, */*");
	        headers.put("content-type", "application/json");
	        headers.put("Authorization", "Bearer " + ACCESS_TOKEN1);
	        Response response = RestAssured
	                .given()
	                .headers(headers)
	                .contentType(ContentType.JSON)
	                .get("https://api.qa2.wci-registry.org/transfers/api/batchTransfer/jurisdiction/25639c51-f558-440b-90ab-ee496dd32f27/batchTransferHoldingsDetails?batchId="+ Batch_Transfer_ID)
	        .then().log().all().assertThat()
			.statusCode(200).and().body("messageKey", equalTo("SUCCESS")).extract().response();
	        String batchTransferHoldingId = response.jsonPath().getString("data[0].batchTransferHoldingId");
	        if (batchTransferHoldingId != null && !batchTransferHoldingId.isEmpty()) {
	        	batchTransferHoldingId1 = batchTransferHoldingId;
	            System.out.println("batchTransferHoldingId stored: " + batchTransferHoldingId1);
	            System.setProperty("batchTransferHoldingId1", batchTransferHoldingId1);
	        } else {
	        	System.out.println("batchTransferHoldingId1 not found");
	        }
	}
	
	public static void AddBatchRecord() {
		
		AddBatch AddBatchRecord = new AddBatch();
		AddBatchRecord.setBatchHoldingDetailsId(System.getProperty("batchTransferHoldingId1"));
		AddBatchRecord.setHoldingId(System.getProperty("Batch_HoldingID"));
		AddBatchRecord.setReceivingAccountNumber(System.getProperty("ECHA1"));
		AddBatchRecord.setProposedQuantity("200");

        List<AddBatch> batchHoldingDetailsList = Collections.singletonList(AddBatchRecord);
        
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + ACCESS_TOKEN1);
        headers.put("page-name", "Page_ManageBatchTransfer");
        headers.put("component-name", "createBatchtransferRecord");

        Response response = given()
            .headers(headers)
            .contentType(ContentType.JSON)
            .body(batchHoldingDetailsList).log().all()
            .post("https://api.qa2.wci-registry.org/transfers/api/batchTransfer/jurisdiction/25639c51-f558-440b-90ab-ee496dd32f27/batch/"+ Batch_Transfer_ID +"/batchTransferRecord?transferTypeId="+ TransferTypeId)
            
            .then().log().all().assertThat().statusCode(200).body("messageKey", equalTo("SUCCESS")).extract()
            .response();
	}
	
	public static void ProposeBatchRecord() {
		
		ProposeBatch ProposeBatchRecord = new ProposeBatch();
		ProposeBatchRecord.setbatchName("Add batch");
		ProposeBatchRecord.setcomment("Propose Batch Transfer");
		ProposeBatchRecord.setstatus("95eeb826-808d-4813-856f-9e1ab58a5aa5");
		
		Map<String, String> headers = new HashMap<>();
		headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + ACCESS_TOKEN1);
//        headers.put("page-name", "Page_ManageBatchTransfer");
//        headers.put("component-name", "editBatchTransferDetail");
        
        Response response = given()
        	.headers(headers)
        	.contentType(ContentType.JSON)
        	.body(ProposeBatchRecord).log().all()
        	.patch("https://api.qa2.wci-registry.org/transfers/api/batchTransfer/jurisdiction/25639c51-f558-440b-90ab-ee496dd32f27/batch/"+ Batch_Transfer_ID +"/batchTransferRecord?transferTypeId="+ TransferTypeId)
        	.then().log().all().assertThat().statusCode(200).body("messageKey", equalTo("SUCCESS")).extract()
            .response();
	}
	
	public static void ApproveBatchRecord() {
		
		ApproveBatch ApproveBatchRecord = new ApproveBatch();
		ApproveBatchRecord.setcomment("Approve Batch Transfer");
		ApproveBatchRecord.setstatus("55a042b8-598c-4b6d-b981-feaf847f651a");
		
		Map<String, String> headers = new HashMap<>();
		headers.put("accept", "application/json, text/plain, */*");
        headers.put("content-type", "application/json");
        headers.put("Authorization", "Bearer " + ACCESS_TOKEN2);
        
        Response response = given()
        	.headers(headers)
        	.contentType(ContentType.JSON)
        	.body(ApproveBatchRecord).log().all()
        	.patch("https://api.qa2.wci-registry.org/transfers/api/batchTransfer/jurisdiction/25639c51-f558-440b-90ab-ee496dd32f27/batch/"+ Batch_Transfer_ID +"/batchTransferRecord?transferTypeId="+ TransferTypeId)
        	.then().log().all().assertThat().statusCode(200).body("messageKey", equalTo("SUCCESS")).extract()
            .response();
	}
}
