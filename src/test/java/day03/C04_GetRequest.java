package day03;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static day03.BaseUrl.userIDHerokuapp;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C04_GetRequest {

        /*
      TASK : Verify
    > Status Code : 200,
    > Content Type : application/json; charset=utf-8
    > firstname : Josh
    > lastname : Allen
    > totalprice : 111
    > depositpaid: true
    > bookingdates(checkin) : 2018-01-01
    > bookingdates(checkout) : 2019-01-01
     */
        @Test
        public void userReqresID3Exist() {
            Response response = given().when().get(userIDHerokuapp(4));
            response.prettyPrint();
            response
                    .then()
                    .contentType("application/json")
                    .statusCode(200)
                    .body("data.first_name", equalTo("Eve"),
                            "data.last_name", equalTo("Holt"),
                            "data.email", equalTo("eve.holt@reqres.in"),
                            "support.url", equalTo("https://reqres.in/#support-heading"),
                            "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        }
}
