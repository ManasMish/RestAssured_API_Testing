package test.CRUD.PATCH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonBDDPatch {
    RequestSpecification rpatch;
    ValidatableResponse vrpatch;
    String token="850276fbee2ffd7";
    @Test
    void PatchReq()
    {
        String Payload="{\n" +
                "    \"firstname\" : \"Kartik\",\n" +
                "    \"lastname\" : \"Pandey\"\n" +
                "}";
        rpatch= RestAssured.given();
        rpatch.baseUri("https://restful-booker.herokuapp.com/");
        rpatch.basePath("booking/1990");
        rpatch.contentType(ContentType.JSON);
        rpatch.cookie("token",token);

        rpatch.body(Payload).log().all();
        Response reppatch=rpatch.when().patch();
        vrpatch=reppatch.then().log().all();
        vrpatch.statusCode(200);
        vrpatch.body("firstname", Matchers.equalTo("Kartik"));
        vrpatch.body("lastname", Matchers.equalTo("Pandey"));

    }
}
