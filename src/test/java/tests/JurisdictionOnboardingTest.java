package tests;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utility.ExcelDataProvider;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class JurisdictionOnboardingTest {

	@Test(priority=0)
	public void testInitiateAuth() throws IOException {
		String baseUri = "https://cognito-idp.us-west-2.amazonaws.com/";
		ExcelDataProvider ref = new ExcelDataProvider();
		String requestBody = ref.getStringData("APIDATA", 0, 0);
		EncoderConfig encoderConfig = encoderConfig().encodeContentTypeAs("application/x-amz-json-1.1",
				io.restassured.http.ContentType.TEXT);
		Response response = given().baseUri(baseUri).header("Content-Type", "application/x-amz-json-1.1")
				.header("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth").body(requestBody)
				.config(RestAssured.config().encoderConfig(encoderConfig)).when().post();
//		System.out.println("Response Body is: " + response.getBody().asString());
		String bearerToken = response.jsonPath().getString("AuthenticationResult.IdToken");
		System.out.println(bearerToken);
		System.setProperty("bearerTokenvar", bearerToken);
		saveBearerTokenToPropertiesFile(System.getProperty("user.dir") + "/exported.properties", "bearerToken",
				bearerToken);

	}

	private void saveBearerTokenToPropertiesFile(String fileName, String key, String value) throws IOException {
		Properties properties = new Properties();
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			properties.setProperty(key, value);
			System.out.println("Exported successfully");
			properties.store(outputStream, null);
		}
	}

	@Test(priority=1)
	public void testAddOnboardingJurisdiction() throws IOException {
//		 Load bearer token from properties file
//		String propertiesFilePath = System.getProperty("user.dir") + "/exported.properties";
		
//		
//		Properties properties = new Properties();
//		try (FileInputStream inputStream = new FileInputStream(propertiesFilePath)) {
//			properties.load(inputStream);
//		}
		String bearerToken = System.getProperty("bearerTokenvar");
		String baseUri = "https://api.qa2.wci-registry.org";
		String path = "/jurisdictions/api/jurisdictionOnboarding/addOnboardingJurisdiction";
		ExcelDataProvider ref = new ExcelDataProvider();
		String requestBody = "{\"jurisdictionName\":\"NEW_JURISDICTION_65572\",\"dateOfJoining\":\"2024-07-25T00:00:00-04:00\",\"languageDetailsIds\":[\"00f2b7fe-18fb-4ca7-81c5-9592f96ec5e2\"],\"currencyDetailIds\":[\"8f9a603a-863a-436a-9143-09460c944a32\"],\"jurisdictionDisplayName\":\"NEW JURISDICTION 65572\",\"shortCode\":\"N61\",\"purchaseLimitParameter\":\"Percentage\"}";
		Response response = RestAssured.given().baseUri(baseUri).basePath(path)
				.header("authorization", "Bearer " + bearerToken) // Essential for authentication
//	    	    .header("Content-Type", "application/json")        // Essential for specifying request body format
//	    	    .header("Accept", "application/json")               // Essential for specifying acceptable response format
				.body(requestBody).when().post();

		System.out.println("Response Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());
	}
}