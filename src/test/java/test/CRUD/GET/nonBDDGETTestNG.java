package test.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class nonBDDGETTestNG {
    @Test
    public void NegSceGETALLBooking()
    {
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/-1").log().all();
        r.when().get();
        r.then().log().all().statusCode(404);
    }
    @Test
    public void NegativeSce2GETALLBooking()
    {
        RequestSpecification r2=RestAssured.given();
        r2.baseUri("https://restful-booker.herokuapp.com");
        r2.basePath("/booking/abc").log().all();
        r2.when().get();
        r2.then().log().all().statusCode(404);
    }
    @Test(enabled = false)
    public void NegSce3GETAlLBooking()
    {
        RequestSpecification r3=RestAssured.given();
        r3.baseUri("https://restful-booker.herokuapp.com");
        r3.basePath("/booking/;;;").log().all();
        r3.when().get();
        r3.then().log().all().statusCode(404);
    }
    @Test
    public void PosSceGETALLBooking()
    {
        RequestSpecification r4=RestAssured.given();
        r4.baseUri("https://restful-booker.herokuapp.com");
        r4.basePath("/booking").log().all();
        r4.when().get();
        r4.then().log().all().statusCode(200);
    }
}
