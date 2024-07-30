package RestAssuredScriptsToGenerateData;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class LoginWCIINCadmin {

    public static void main(String[] args) {
        new LoginWCIINCadmin().loginUser();
    }

    public void loginUser() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("wciadmin.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI = prop.getProperty("base.uri");
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/x-amz-json-1.1", io.restassured.http.ContentType.JSON));
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
        Response response = given()
            .header("Content-Type", prop.getProperty("content.type"))
            .header("X-Amz-Target", prop.getProperty("x.amz.target"))
            .body(requestParams.toString())
            .when()
            .post("/");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String accessToken = jsonPathEvaluator.getString("AuthenticationResult.AccessToken");
        System.out.println("AccessToken: " + accessToken);
    }
}
