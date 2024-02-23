package test.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class NonBDDPOSTReq {
    @Test
    void PosScePOST()
    {
        String payload="{\n" +
                "        \"username\": \"admin\",\n" +
                "        \"password\": \"password123\"\n" +
                "}";
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payload);
        Response response=r.when().post();
        ValidatableResponse vr= response.then();
        String rs=response.asString();
        System.out.println(rs);
        vr.statusCode(200);
    }
}
