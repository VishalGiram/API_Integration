package RestAssuredScriptsToGenerateData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import POJOClasses.AllowanceIssuanceInformation;
import POJOClasses.AllowanceIssueanceApprove;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityAndTransfersTest {

	// Base URL
	private static final String BASE_URL = "https://api.qa2.wci-registry.org";

	// Access tokens
	public static String ACCESS_TOKEN; // WciIncAdmin
	public static String ACCESS_TOKEN1; // Juriadmin
	public static String ACCESS_TOKEN2; // Authority

	// Budget ID to be used across requests
	private static String transferId2;

	public static void main(String[] args) {
//		loginUserWciadmin();
		loginUserJuriAdmin();
//		loginUserAuthority();
//		addBudgetRecord();
//		proposeBudgetRecordsToAuthority();
//		approveBudgetRecordByAuthority();
		jurisdictionAccounts();
//		proposeAllowanceIssuance();
//		approveAllowance();
		Holdings();
	}

	public static void loginUserWciadmin() {
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
		authParameters.put("USERNAME", prop.getProperty("username"));
		authParameters.put("PASSWORD", prop.getProperty("password"));
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
		ACCESS_TOKEN = jsonPathEvaluator.getString("AuthenticationResult.IdToken");
		System.out.println("Wciadmin AccessToken: " + ACCESS_TOKEN);
		System.setProperty("ACCESS_TOKEN", ACCESS_TOKEN);
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

	public static void addBudgetRecord() {
		// Request Body
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("wciadmin.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject budgetRecord = new JSONObject();
		budgetRecord.put("budgetYear", prop.getProperty("budgetYear")); // Update the value if necessary
		budgetRecord.put("annualAllowanceBudget", "150000");
		budgetRecord.put("reserveAdjustedAllowanceBudget", "100000");

		JSONArray jurisdictionBudgetArray = new JSONArray();
		jurisdictionBudgetArray.put(budgetRecord);

		JSONObject requestBody = new JSONObject();
		requestBody.put("jurisdictionBudget", jurisdictionBudgetArray);

		// Make the request and capture the response
		Response response = RestAssured.given().header("Authorization", "Bearer " + ACCESS_TOKEN)
				.header("component-name", "jurisdictionBudget").header("page-name", "Page_ManageJurisdictionBudget")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody.toString()).log().all() // Log
				.post(BASE_URL+"/budgets/api/jurisdictions/25639c51-f558-440b-90ab-ee496dd32f27/budget-record");
		// Print the response
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
		// Extract the 'id' from the response
		String responseBody = response.getBody().asString();
		JSONObject jsonResponse = new JSONObject(responseBody);
		JSONArray dataArray = jsonResponse.getJSONArray("data");
		if (dataArray.length() > 0) {
			JSONObject dataObject = dataArray.getJSONObject(0);
			String id = dataObject.getString("id");
			System.out.println("Extracted ID: " + id);
			System.setProperty("budgetId1", id);
			// Declare or use 'id' as needed
		} else {
			System.out.println("No data found in response.");
		}
	}

	public static void proposeBudgetRecordsToAuthority() {
		JSONObject budgetRecord = new JSONObject();
		budgetRecord.put("id", System.getProperty("budgetId1")); // Update the value if necessary
		budgetRecord.put("comment", "Propose to Authority");
		budgetRecord.put("statusId", "312adf25-f2a3-4d51-8261-d8a1da2a2b1d");
		JSONArray JurisdictionBudgetRecord = new JSONArray();
		JurisdictionBudgetRecord.put(budgetRecord);
		JSONObject requestBody = new JSONObject();
		requestBody.put("jurisdictioBudgetRecord", JurisdictionBudgetRecord);
		Response response = RestAssured.given().header("Authorization", "Bearer " + ACCESS_TOKEN1)
				.header("component-name", "jurisdictioBudgetRecord").header("page-name", "Page_ManageJurisdictionBudget")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody.toString()).log().all() // Log
				.patch(BASE_URL+"/budgets/api/jurisdictions/25639c51-f558-440b-90ab-ee496dd32f27/budget-record");
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());

	}

	public static void approveBudgetRecordByAuthority() {
		JSONObject budgetRecord = new JSONObject();
		budgetRecord.put("id", System.getProperty("budgetId1")); // Update the value if necessary
		budgetRecord.put("comment", "Propose to Authority");
		budgetRecord.put("statusId", "071646c5-5bba-4f84-82cb-9e38c14c44b5");
		JSONArray JurisdictionBudgetRecord = new JSONArray();
		JurisdictionBudgetRecord.put(budgetRecord);
		JSONObject requestBody = new JSONObject();
		requestBody.put("jurisdictioBudgetRecord", JurisdictionBudgetRecord);
		Response response = RestAssured.given().header("Authorization", "Bearer " + ACCESS_TOKEN2)
				.header("component-name", "jurisdictioBudgetRecord").header("page-name", "Page_ManageJurisdictionBudget")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody.toString()).log().all() // Log
				.patch(BASE_URL+"/budgets/api/jurisdictions/25639c51-f558-440b-90ab-ee496dd32f27/budget-record");
		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
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
			}
		}
	}

	public static void proposeAllowanceIssuance() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("wciadmin.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AllowanceIssuanceInformation allowanceIssuance = new AllowanceIssuanceInformation();
		allowanceIssuance.setQuantity("1000");
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
	}
}
