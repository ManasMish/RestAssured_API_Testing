package test.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class nonBDDGET {
    public static void main(String[] args) {
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/2213").log().all();
        r.when().get();
        r.then().log().all().statusCode(201);

        RequestSpecification r1=RestAssured.given();
        r1.baseUri("https://api.zippopotam.us");
        r1.basePath("/IN/273001").log().all();
        r1.when().get();
        r1.then().log().all().statusCode(201);
    }
}
