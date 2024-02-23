package test.CRUD.DELETE;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDDelete {
    RequestSpecification rdel;
    ValidatableResponse vrdel;
    String token="99d9caba23adb0e";
    @Test
    void delreq()
    {
        rdel= RestAssured.given();
        rdel.baseUri("https://restful-booker.herokuapp.com/");
        rdel.basePath("booking/1576");
        rdel.contentType(ContentType.JSON);
        rdel.cookie("token",token);
        Response resdel=rdel.when().delete();
        vrdel= resdel.then().log().all();
        vrdel.statusCode(201);

    }
}
