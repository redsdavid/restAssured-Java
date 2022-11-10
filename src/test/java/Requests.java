import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Requests {

    @Test
    public void getUsersList(){
        baseURI = "https://reqres.in/api";

        given()
        .when()
           .get("/users")
        .then()
            .log().all()
            .statusCode(200)
            .body("data[1].first_name", equalTo("Janet"))
            .assertThat().body(matchesJsonSchemaInClasspath("getUserSchema.json"));
    }


    @Test
    public void postCreateUser(){
        baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","Vivi");
        map.put("job","Designer");

        given()
                .log().all()
                .body(map.toString())
        .when()
                .post("/users")
        .then()
                .log().all()
                .statusCode(201)
                // .assertThat().body(matchesJsonSchemaInClasspath("createUserSchema.json"));
                .assertThat().body(matchesJsonSchemaInClasspath("createUser.json"));

    }
}
