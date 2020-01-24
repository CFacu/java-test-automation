package restassured;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Testing {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://swapi.co/api";
    }

    @Test
    public void getFirstPeopleAndVerifyGender() {
        when().get("/people/1").then().assertThat().body("gender", equalTo("male"));
    }


    @Test
    public void getAllPeople() {
        given().log().all().when().get("/people").then().statusCode(100);
    }
}
