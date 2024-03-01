package datadriventest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import util.readExcel;

public class ddt001 {
    RequestSpecification r2;
    ValidatableResponse vR2;
    Integer ID;
    Response res2;

    @Test(dataProvider = "getData", dataProviderClass = readExcel.class)
    public void test_login(String email,String password){
        System.out.println("--Login to App.vwo--");
        String payload="{\n" +
                "  \"username\":"+email+
                "  \"password\":"+password+
                "  \"remember\": false,\n" +
                "  \"recaptcha_response_field\": \"\"\n" +
                "}";
        r2= RestAssured.given();
        r2.baseUri("https://app.vwo.com");
        r2.basePath("/login");
        r2.contentType(ContentType.JSON);
        r2.body(payload).log().all();
        res2=r2.when().post();
        vR2=res2.then();
        String resString= res2.asString();
        System.out.println(resString);
        vR2.body("id", Matchers.notNullValue());
        vR2.statusCode(200);
        ID=res2.then().log().all().extract().path("id");
        System.out.println(ID);
    }
}
