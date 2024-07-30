package RestAssuredScriptsToGenerateData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateRegistrationStatus {
	private static final Logger logger = LoggerFactory.getLogger(RegModule3DataCreation.class);

	public static void main(String[] args) {
		// Set base URI for RestAssured
		Properties properties = new Properties();
		try {
			FileInputStream input = new FileInputStream("usersdata.properties");
			properties.load(input);
		} catch (IOException e) {
			logger.error("Error loading properties file", e);
			return;
		}
		RestAssured.baseURI = "https://api.qa2.wci-registry.org";
		String registrarUID = "3c6169d9-17e5-45b1-904b-11b3965e67dd";
        String requestBody = "{\n" +
                "    \"is_role_update\": false,\n" +
                "    \"RoleDetail\": {\n" +
                "        \"RoleType\": [\n" +
                "            {\n" +
                "                \"name\": \"Jurisdiction Users\",\n" +
                "                \"id\": \"d0832b6e-55be-4ca6-8c92-9d3b2dd345c8\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Role\": [\n" +
                "            {\n" +
                "                \"name\": \"Registrar\",\n" +
                "                \"id\": \"ROLE#00101\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"Comment\": \"\",\n" +
                "    \"RequestedBy\": \"U#" + registrarUID + "\",\n" +
                "    \"LanguageCode\": \"L001\"\n" +
                "}";
		// Authorization token
		String bearerToken = "eyJraWQiOiJRUHk2WDhUOEJPc0Vxak5ZWTN1RXJlSWN4WVQ4QVNuN0ZmR2w3QkJxd0pBPSIsImFsZyI6IlJTMjU2In0.eyJjdXN0b206ZW1wbG95ZXIiOiJbe1widGl0bGVcIjpcInRlc3RcIn1dIiwic3ViIjoiM2M2MTY5ZDktMTdlNS00NWIxLTkwNGItMTFiMzk2NWU2N2RkIiwiY3VzdG9tOmVtYWlsTGFuZ3VhZ2UiOiJFbmdsaXNoIiwiYmlydGhkYXRlIjoiMjAwMy0wMS0wMiIsImdlbmRlciI6Ik1hbGUiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtd2VzdC0yLmFtYXpvbmF3cy5jb21cL3VzLXdlc3QtMl9HcFlFUEtGanUiLCJjdXN0b206bWFpbFN0cmVldDEiOiJ0ZXN0IiwiY3VzdG9tOnJlc0NpdHkiOiJwdW5lIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiY2EucmVnaXN0cmFyIiwiY3VzdG9tOm1haWxDb3VudHJ5IjoiVW5pdGVkIFN0YXRlcyIsImN1c3RvbTpyZXNQaW5Db2RlIjoiMzUzNDY1NCIsImN1c3RvbTphY2NlcHRUQW5kQyI6InRydWUiLCJhdXRoX3RpbWUiOjE3MjEzMDgzMjksImN1c3RvbTpyZXNTdGF0ZSI6IkFsYXNrYSIsImV4cCI6MTcyMTM5MzEwOSwiaWF0IjoxNzIxMzA4MzI5LCJqdGkiOiJkYjM0ZjIyNy1mY2RlLTRmOWEtYjY3OC0wOTRlYmM3YWFjYmYiLCJlbWFpbCI6ImNhLnJlZ2lzdHJhckBlcWFuaW0tcWEud2NpLXZhbGlkYXRlLm9yZyIsImN1c3RvbTpyZXNTdHJlZXQxIjoidGVzdCIsImN1c3RvbTptYWlsU3RhdGUiOiJBbGFza2EiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiY3VzdG9tOnJlc0NvdW50cnkiOiJVbml0ZWQgU3RhdGVzIiwiY3VzdG9tOm1haWxQaW5Db2RlIjoiMzUzNDY1NCIsImN1c3RvbTpzZWN1cml0eVExIjoiSW4gd2hpY2ggY2l0eSBvciB0b3duIHdhcyB5b3VyIGZpcnN0IGpvYj8iLCJjdXN0b206c2VjdXJpdHlRMiI6IldoYXQgaXMgeW91ciBtYXRlcm5hbCBncmFuZG1vdGhlcidzIG1haWRlbiBuYW1lPyIsImNvZ25pdG86dXNlcm5hbWUiOiIyOTQyNTgtMTcwNDg3MTI3OTg5MyIsImN1c3RvbTpzZWN1cml0eVEzIjoiV2hhdCB3YXMgdGhlIG1vZGVsIG9mIHlvdXIgZmlyc3QgY2FyPyIsImN1c3RvbTptb2JpbGVQaG9uZSI6IjQzNjU0NjY2NjYiLCJvcmlnaW5fanRpIjoiMWY1MTQ1MmMtN2VjNC00YjI0LWIwMmItZWVlZTJiZTVhN2Q5IiwiY3VzdG9tOnBfdXNlcm5hbWUiOiJjYS5yZWdpc3RyYXIiLCJhdWQiOiI2bnBzNGhrNTNyN3A5aGtiOGJvYmwzaHIyNiIsInRva2VuX3VzZSI6ImlkIiwiY3VzdG9tOm1haWxDaXR5IjoicHVuZSIsImN1c3RvbTpzdGF0dXMiOiJQZW5kaW5nIiwibmFtZSI6InJlZ2lzdHJhciIsImN1c3RvbTpzZWN1cml0eUExIjoiYWFhYSIsImN1c3RvbTpzZWN1cml0eUEyIjoiYmJiYiIsImN1c3RvbTpzZWN1cml0eUEzIjoiY2NjYyIsImN1c3RvbTpvcmdJZCI6Ik9SRyMwMDEwMSIsImZhbWlseV9uYW1lIjoiY2EifQ.teISdfqUccSMs-4_HJKX86f1iYOhJXsJLwZbcxwb_o0WuYu7XccH9JoFl0CRtLKOs_ONaQV6pr5JXpg_JQnmgI_9X2jVNHAAptzRFHcF0bDK0EAhiLOA_jK_Hg7Xwtc3vmUeqBABX0AUKBZOslQTVt9VfNqFdVvocHv4dtL7PouRVtt26TMC5iwQqHGF6Zx0dbDbdSk5_80CYNxdQAJCJMgdAoZg50IiAY4xaV-UraQJAFFw7n10KPwfR44U7C9KuU47Qk3ApAeXlTLMT0pkbXB3mZFBjlp_5Q2INJ-6KDbpko7xi6R7KUKlnDEKi1yiqXVAEPpWeRgxqhuUWlRs4w";
		// Perform the request using Rest Assured
		System.out.println("userid"+properties.getProperty("userId"));
		Response response = given().contentType(ContentType.JSON).header("Authorization", "Bearer " + bearerToken)
				.body(requestBody).when()
				.put("/registration/registrationstatus/U%23"+ properties.getProperty("userId") +"/Approved").then()
				.extract().response();
		// Print the response body to the console
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:");
		System.out.println(responseBody);
	}
}
