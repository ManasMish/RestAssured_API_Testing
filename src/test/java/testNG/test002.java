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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class test002 {
    RequestSpecification reqSpec;
    ValidatableResponse valRes;
    String token;
    Integer bookingID;
    Response res;
    @BeforeTest
    void GETtoken()
    {
        System.out.println("------GET Token-------");
        reqSpec= RestAssured.given();
        String payload="{\n" +
                "        \"username\": \"admin\",\n" +
                "        \"password\": \"password123\"\n" +
                "}";
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("auth");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(payload);
        res=reqSpec.post();
        valRes=res.then();

        // Rest Assured -> Matchers (Hamcrest) - 1-2% Times you will be using it
        valRes.body("token", Matchers.notNullValue());

        //TestNG Assertion
        token = res.then().log().all().extract().path("token");
        Assert.assertNotNull(token);

        // AssertJ
       // assertThat(token).isNotNull().isNotBlank().isNotEmpty();

        System.out.println(token);
    }
    @BeforeTest
    void GETbookingId()
    {
        System.out.println("----GET BookingID----");
        reqSpec = RestAssured.given();
        String payload="{\n" +
                "    \"firstname\" : \"Manas\",\n" +
                "    \"lastname\" : \"Mishra\",\n" +
                "    \"totalprice\" : 53000,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-07-25\",\n" +
                "        \"checkout\" : \"2024-07-26\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : [\"Breakfast\",\"Lunch\",\"Dinner\",\"GYM\"]\n" +
                "}";
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("booking");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(payload);

        //Making POST Req for new booking ID
        res=reqSpec.when().post();

        //Validation Part
        valRes=res.then();
        String resString= res.asString();
        System.out.println(resString);

        valRes.statusCode(200);

        bookingID=res.then().log().all().extract().path("bookingid");
        System.out.println(bookingID);
    }
    @Test
    void testPUTReq()
    {
        System.out.println("----PUT Request----");
        reqSpec=RestAssured.given();
       String payload="{\n" +
               "    \"firstname\" : \"Kartik\",\n" +
               "    \"lastname\" : \"Pandey\",\n" +
               "    \"totalprice\" : 111,\n" +
               "    \"depositpaid\" : true,\n" +
               "    \"bookingdates\" : {\n" +
               "        \"checkin\" : \"2018-01-01\",\n" +
               "        \"checkout\" : \"2019-01-01\"\n" +
               "    },\n" +
               "    \"additionalneeds\" : \"Breakfast\"\n" +
               "}";
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("booking/"+bookingID);
        reqSpec.contentType(ContentType.JSON);
        reqSpec.cookie("token",token);
        reqSpec.body(payload).log().all();

        //PUT req
        res=reqSpec.when().put();

        //Validatable response
        valRes=res.then().log().all();
        valRes.statusCode(200);
        valRes.body("firstname",Matchers.equalTo("Kartik"));
        valRes.body("lastname",Matchers.equalTo("Pandey"));
    }
}
