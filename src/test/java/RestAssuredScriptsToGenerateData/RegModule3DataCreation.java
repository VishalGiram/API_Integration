package RestAssuredScriptsToGenerateData;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class RegModule3DataCreation {
	private static final Logger logger = LoggerFactory.getLogger(RegModule3DataCreation.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("config.properties");
            properties.load(input);
        } catch (IOException e) {
            logger.error("Error loading properties file", e);
            return;
        }
        RestAssured.baseURI = properties.getProperty("baseURI");
        RestAssured.config = RestAssured.config().encoderConfig(
                EncoderConfig.encoderConfig().encodeContentTypeAs("application/x-amz-json-1.1", io.restassured.http.ContentType.JSON));
        String requestBody = String.format("{\"AuthFlow\":\"%s\",\"ClientId\":\"%s\",\"AuthParameters\":{\"USERNAME\":\"%s\",\"PASSWORD\":\"%s\"},\"ClientMetadata\":{\"clientTokenCandidate\":\"%s\"}}",
                properties.getProperty("authFlow"),
                properties.getProperty("clientId"),
                properties.getProperty("username"),
                properties.getProperty("password"),
                properties.getProperty("clientTokenCandidate"));
        try {
            Response response = given()
                    .header("Content-Type", properties.getProperty("contentType"))
                    .header("X-Amz-Target", properties.getProperty("xAmzTarget"))
                    .body(requestBody)
                    .post();
            logger.info("Response status code: " + response.getStatusCode());
            String responseBody = response.getBody().asString();
            logger.info("Response body: " + responseBody);
            if (response.getStatusCode() == 200) {
                String idToken = response.jsonPath().getString("AuthenticationResult.IdToken");
                logger.info("IdToken: " + idToken);
                properties.setProperty("idToken", idToken);
                FileOutputStream output = new FileOutputStream("config2.properties");
                properties.store(output, "Updated properties with IdToken");
                output.close();
                logger.info("IdToken saved to config2.properties successfully.");
        
            } else {
                logger.error("Failed to authenticate. Status code: " + response.getStatusCode());
            }
            response.then().statusCode(200);
        } catch (Exception e) {
            logger.error("Error making API call", e);
        }
    }
}
	

