package RestAssuredScriptsToGenerateData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Module5DataCreation {

	public void onboardJurisdiction() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("M5.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit if properties file can't be loaded
        }
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-04:00"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        String formattedDateTime = now.format(formatter);
        System.out.println("Current Date and Time: " + formattedDateTime);
        RestAssured.baseURI = prop.getProperty("base.uri");
        // Construct the request payload
        JSONObject requestParams = new JSONObject();
        requestParams.put("jurisdictionName", prop.getProperty("jurisdictionName"));
        requestParams.put("dateOfJoining", formattedDateTime);
        requestParams.put("languageDetailsIds", new String[]{prop.getProperty("languageDetailsIds")});
        requestParams.put("currencyDetailIds", new String[]{prop.getProperty("currencyDetailIds")});
        requestParams.put("jurisdictionDisplayName", prop.getProperty("jurisdictionDisplayName"));
        requestParams.put("shortCode", prop.getProperty("shortCode"));
        requestParams.put("purchaseLimitParameter", prop.getProperty("purchaseLimitParameter"));
        // Make the POST request
        Response response = RestAssured.given()
            .header("accept", "application/json, text/plain, */*")
            .header("accept-language", "en-US,en;q=0.9")
            .header("authorization", prop.getProperty("authorizationToken"))
            .header("component-name", "setupNewJurisdiction")
            .header("content-type", "application/json")
            .header("origin", "https://qa2.wci-registry.org")
            .header("page-name", "Page_SetupNewJurisdiction")
            .header("priority", "u=1, i")
            .header("referer", "https://qa2.wci-registry.org/")
            .header("sec-ch-ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"Windows\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-site")
            .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
            .body(requestParams.toString())
        .when()
            .post("/addOnboardingJurisdiction");
        // Log response details
        response.then()
            .log().all();
        // Optionally check status code
        if (response.getStatusCode() != 200) {
            System.out.println("Request failed with status code: " + response.getStatusCode());
        }
    }    
	    
	    public static void main(String[]args) {
	    	Module5DataCreation ref = new Module5DataCreation();
	    	ref.onboardJurisdiction();
	    }
	}
