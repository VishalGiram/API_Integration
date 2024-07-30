package RestAssuredScriptsToGenerateData;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class Module1UsersCreation {
	private static final Logger logger = LoggerFactory.getLogger(RegModule3DataCreation.class);
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("user.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        RestAssured.baseURI = "https://cognito-idp.us-west-2.amazonaws.com/";
        JSONObject requestBody = new JSONObject();
        requestBody.put("ClientId", properties.getProperty("ClientId"));
        requestBody.put("Username", properties.getProperty("Username"));
        requestBody.put("Password", properties.getProperty("Password"));
        JSONArray userAttributes = new JSONArray();
        userAttributes.put(new JSONObject().put("Name", "custom:p_username").put("Value", properties.getProperty("custom_p_username")));
        userAttributes.put(new JSONObject().put("Name", "email").put("Value", properties.getProperty("email")));
        userAttributes.put(new JSONObject().put("Name", "name").put("Value", properties.getProperty("name")));
        userAttributes.put(new JSONObject().put("Name", "middle_name").put("Value", properties.getProperty("middle_name")));
        userAttributes.put(new JSONObject().put("Name", "family_name").put("Value", properties.getProperty("family_name")));
        userAttributes.put(new JSONObject().put("Name", "birthdate").put("Value", properties.getProperty("birthdate")));
        userAttributes.put(new JSONObject().put("Name", "phone_number").put("Value", properties.getProperty("phone_number")));
        userAttributes.put(new JSONObject().put("Name", "gender").put("Value", properties.getProperty("gender")));
        userAttributes.put(new JSONObject().put("Name", "custom:salutation").put("Value", properties.getProperty("custom_salutation")));
        userAttributes.put(new JSONObject().put("Name", "custom:suffix").put("Value", properties.getProperty("custom_suffix")));
        userAttributes.put(new JSONObject().put("Name", "custom:mobilePhone").put("Value", properties.getProperty("custom_mobilePhone")));
        userAttributes.put(new JSONObject().put("Name", "custom:emailLanguage").put("Value", properties.getProperty("custom_emailLanguage")));
        userAttributes.put(new JSONObject().put("Name", "custom:employer").put("Value", properties.getProperty("custom_employer")));
        userAttributes.put(new JSONObject().put("Name", "custom:department").put("Value", properties.getProperty("custom_department")));
        userAttributes.put(new JSONObject().put("Name", "custom:faxNumber").put("Value", properties.getProperty("custom_faxNumber")));
        userAttributes.put(new JSONObject().put("Name", "custom:orgId").put("Value", properties.getProperty("custom_orgId")));
        userAttributes.put(new JSONObject().put("Name", "custom:ext").put("Value", properties.getProperty("custom_ext")));
        userAttributes.put(new JSONObject().put("Name", "custom:jobTitle").put("Value", properties.getProperty("custom_jobTitle")));
        userAttributes.put(new JSONObject().put("Name", "custom:resStreet1").put("Value", properties.getProperty("custom_resStreet1")));
        userAttributes.put(new JSONObject().put("Name", "custom:resStreet2").put("Value", properties.getProperty("custom_resStreet2")));
        userAttributes.put(new JSONObject().put("Name", "custom:resCity").put("Value", properties.getProperty("custom_resCity")));
        userAttributes.put(new JSONObject().put("Name", "custom:resState").put("Value", properties.getProperty("custom_resState")));
        userAttributes.put(new JSONObject().put("Name", "custom:resRegion").put("Value", properties.getProperty("custom_resRegion")));
        userAttributes.put(new JSONObject().put("Name", "custom:resCountry").put("Value", properties.getProperty("custom_resCountry")));
        userAttributes.put(new JSONObject().put("Name", "custom:resPinCode").put("Value", properties.getProperty("custom_resPinCode")));
        userAttributes.put(new JSONObject().put("Name", "custom:mailStreet1").put("Value", properties.getProperty("custom_mailStreet1")));
        userAttributes.put(new JSONObject().put("Name", "custom:mailStreet2").put("Value", properties.getProperty("custom_mailStreet2")));
        userAttributes.put(new JSONObject().put("Name", "custom:mailCity").put("Value", properties.getProperty("custom_mailCity")));
        userAttributes.put(new JSONObject().put("Name", "custom:mailState").put("Value", properties.getProperty("custom_mailState")));
        userAttributes.put(new JSONObject().put("Name", "custom:mailRegion").put("Value", properties.getProperty("custom_mailRegion")));
        userAttributes.put(new JSONObject().put("Name", "custom:mailCountry").put("Value", properties.getProperty("custom_mailCountry")));
        userAttributes.put(new JSONObject().put("Name", "custom:mailPinCode").put("Value", properties.getProperty("custom_mailPinCode")));
        userAttributes.put(new JSONObject().put("Name", "custom:securityQ1").put("Value", properties.getProperty("custom_securityQ1")));
        userAttributes.put(new JSONObject().put("Name", "custom:securityQ2").put("Value", properties.getProperty("custom_securityQ2")));
        userAttributes.put(new JSONObject().put("Name", "custom:securityQ3").put("Value", properties.getProperty("custom_securityQ3")));
        userAttributes.put(new JSONObject().put("Name", "custom:securityA1").put("Value", properties.getProperty("custom_securityA1")));
        userAttributes.put(new JSONObject().put("Name", "custom:securityA2").put("Value", properties.getProperty("custom_securityA2")));
        userAttributes.put(new JSONObject().put("Name", "custom:securityA3").put("Value", properties.getProperty("custom_securityA3")));
        userAttributes.put(new JSONObject().put("Name", "custom:acceptTAndC").put("Value", properties.getProperty("custom_acceptTAndC")));
        userAttributes.put(new JSONObject().put("Name", "custom:status").put("Value", properties.getProperty("custom_status")));
        requestBody.put("UserAttributes", userAttributes);
        requestBody.put("ValidationData", JSONObject.NULL);
        EncoderConfig encoderConfig = new EncoderConfig().encodeContentTypeAs("application/x-amz-json-1.1", ContentType.TEXT);
        Response response = given()
                .config(RestAssured.config().encoderConfig(encoderConfig))
                .header("Content-Type", "application/x-amz-json-1.1")
                .header("X-Amz-Target", "AWSCognitoIdentityProviderService.SignUp")
                .body(requestBody.toString())
                .when()
                .post("/")
                .then()
              //  .statusCode(200) 
                .extract().response();
        String responseBody = response.getBody().asString();
        System.out.println("Response Body:");
        System.out.println(responseBody);
        // Extract the UserSub from the response body
        String userId = response.jsonPath().getString("UserSub");
        logger.info("userId: " + userId);
        properties.setProperty("userId", userId);
        FileOutputStream output = new FileOutputStream("usersdata.properties");
        properties.store(output, "Updated properties with IdToken");
        output.close();
        logger.info("userId saved to user.properties successfully.");
        // Write the UserSub to an Excel file
    }
}