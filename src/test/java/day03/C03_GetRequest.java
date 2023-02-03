package day03;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C03_GetRequest {

    private String bookingHerokuapp = "https://restful-booker.herokuapp.com/booking/";
    private String bookingID240Herokuapp = "https://restful-booker.herokuapp.com/booking/240";
    private String userID2ReqresIn = "https://reqres.in/api/users/2";
    private String userID3RegresIn = "https://regres.in/api/users/3";

    @Test
    public void getBooking(){
        Response response = given().when().get(bookingHerokuapp);
        response.prettyPeek();
    }

    /*                    NOTE: some info may change, check postman if not asserts true
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
    public void bookingIdExists(){
         Response response = given().when().get(bookingID240Herokuapp);
         response
                 .then()
                 .statusCode(200)
                 .contentType("application/json; charset=utf-8")
                 .body("firstname", equalTo("Josh"),
                            "lastname", equalTo("Allen"),
                            "totalprice", equalTo(111),
                            "depositpaid", equalTo(true),
                            "bookingdates.checkin", equalTo("2018-01-01"),
                            "bookingdates.checkout", equalTo("2019-01-01"));
     }

    /*                  NOTE: There are 2 Jason in 1 body
        TASK : Verify
    > Status Code : 200,
    > Content Type : application/json; charset=utf-8
    > email : janet.weaver@reqres.in
    > first_name : Janet
    > last_name : Weaver
    > url : https://reqres.in/#support-heading
    > text : To keep ReqRes free, contributions towards server costs are appreciated!
    */

    @Test
    public void userRegresId2Exist(){
        Response response = given().when().get(userID2ReqresIn);
        response
                .then()
                .contentType("application/json")
                .statusCode(200)
                .body("data.first_name",equalTo("Janet"),
                        "data.last_name", equalTo("Weaver"),
                       "data.email",equalTo("janet.weaver@reqres.in"),
                        "support.url",equalTo("https://reqres.in/#support-heading"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
