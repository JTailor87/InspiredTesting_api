package org.example;

import static java.time.LocalDate.*;
import static org.junit.Assert.assertEquals;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppTest
{
    @Test
    public void testPOST()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        JSONObject r = new JSONObject();
        r.put("PostId", 1);
        r.put("name", "Jigar");
        r.put("email", "jigar.7195@gmail.com");
        r.put("date", formatter.format(date));
        System.out.println(r.toJSONString());
        baseURI = "https://jsonplaceholder.typicode.com";
        given()
            .header("Content-Type", "application/json")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(r.toJSONString())
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
                .body("name", equalTo("Jigar"))
                .body("PostId", equalTo(1))
                .body("email", equalTo("jigar.7195@gmail.com"))
                .body("date", equalTo("15-May-2023"))
            .log()
            .all();

    }
}
