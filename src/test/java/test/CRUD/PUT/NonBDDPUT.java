package test.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonBDDPUT {
    RequestSpecification rp;
    ValidatableResponse vrp;
    String token="543ff89292da33c";
    @Test
    void PUTReq()
    {
        String payload="{\n" +
                "    \"firstname\" : \"Harshit\",\n" +
                "    \"lastname\" : \"Malviya\",\n" +
                "    \"totalprice\" : 1063000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-11-25\",\n" +
                "        \"checkout\" : \"2025-11-26\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : [\"Breakfast\",\"Lunch\",\"Dinner\",\"Gym\"]\n" +
                "}";
        rp= RestAssured.given();
        rp.baseUri("https://restful-booker.herokuapp.com/");
        rp.basePath("booking/1990");
        rp.contentType(ContentType.JSON);
        rp.cookie("token",token);
        rp.body(payload).log().all();
        Response res=rp.when().put();
        vrp= res.then().log().all();
        vrp.statusCode(200);
        vrp.body("firstname", Matchers.equalTo("Harshit"));
        vrp.body("lastname", Matchers.equalTo("Malviya"));


    }
}
