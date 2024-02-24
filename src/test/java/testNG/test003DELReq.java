package testNG;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test003DELReq  {
    RequestSpecification reqsp;
    ValidatableResponse vres;
    String token;
    Integer bookingID;
    Response res;
    String payload;
    @BeforeTest
    void token()
    {
        reqsp= RestAssured.given();
        payload="{\n" +
                "        \"username\": \"admin\",\n" +
                "        \"password\": \"password123\"\n" +
                "}";
        reqsp.baseUri("https://restful-booker.herokuapp.com/");
        reqsp.basePath("auth");
        reqsp.contentType(ContentType.JSON);
        reqsp.body(payload);
        res=reqsp.post();
        vres=res.then();
        vres.body("token", Matchers.notNullValue());
        token=res.then().log().all().extract().path("token");
        Assert.assertNotNull(token);
        System.out.println(token);
    }
    @BeforeTest
    void bookingID()
    {
        reqsp=RestAssured.given();
        payload="{\n" +
                "    \"firstname\" : \"Harshit\",\n" +
                "    \"lastname\" : \"Malviya\",\n" +
                "    \"totalprice\" : 53000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-07-25\",\n" +
                "        \"checkout\" : \"2024-07-26\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : [\"Breakfast\",\"Lunch\",\"Dinner\",\"GYM\"]\n" +
                "}";
        reqsp.baseUri("https://restful-booker.herokuapp.com/");
        reqsp.basePath("booking");
        reqsp.contentType(ContentType.JSON);
        reqsp.body(payload);
        res=reqsp.when().post();
        vres=res.then();
        String resSTR= res.asString();
        System.out.println(resSTR);
        vres.statusCode(200);
        bookingID=res.then().log().all().extract().path("bookingid");
        System.out.println(bookingID);
    }
    @Test
    void delReq()
    {
        reqsp=RestAssured.given();
        reqsp.baseUri("https://restful-booker.herokuapp.com");
        reqsp.basePath("/booking/"+bookingID);
        reqsp.contentType(ContentType.JSON);
        reqsp.cookie("token",token);
        res=reqsp.when().delete();
        vres=res.then().log().all();
        vres.statusCode(201);
    }
}
