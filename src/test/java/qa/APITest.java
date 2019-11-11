package qa;

import org.testng.annotations.Test;
import dataProvider.ConfigFileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import qa.Token;


public class APITest {
  ConfigFileReader configFileReader;

  @Test
  public void testStatusCode() {
    given().auth().oauth2(Token.getToken())
            .get("https://www.wrike.com/api/v4/workflows")
            .then()
            .assertThat()
            .statusCode(200)
            .body("kind", equalTo("workflows"))
            .body("data[0].name", equalTo("Default Workflow"));
  }


}
