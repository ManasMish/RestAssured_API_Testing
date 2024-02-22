package test.CRUD.GET;

import io.restassured.RestAssured;

public class BDDpatternget {
    public static void main(String[] args) {
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us").basePath("/IN/273001")
                .when().log().all()
                .get()
                .then().log().all().statusCode(200);
    }
}
