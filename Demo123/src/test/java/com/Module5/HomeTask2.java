package com.Module5;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;


public class HomeTask2 {
    @Test
            public void test() {
        Response res=given().queryParam("q", "hyderabad").
                queryParam("appid", "ec716622eb0ffe4b1639c45ea1378d73").
                get("http://api.openweathermap.org/data/2.5/weather").then()
                .extract().response();
        Assert.assertEquals(res.statusCode(),200);
        String lon=res.path("coord.lon").toString();
        System.out.println(lon);
        String lat=res.path("coord.lat").toString();
        System.out.print(lat);

        Response res1=given().queryParam("lat",lat).
                queryParam("lon",lon).
                queryParam("appid","ec716622eb0ffe4b1639c45ea1378d73").
                get("http://api.openweathermap.org/data/2.5/weather").then()
                .extract().response();
        Assert.assertEquals(res.statusCode(),200);

        res1.then().body("sys.country",equalTo("IN")).
                body("name",equalTo("Hyderabad")).
                body("main.temp_min",greaterThan(0F)).
                body("main.temp",greaterThan(0F));
    }

}
