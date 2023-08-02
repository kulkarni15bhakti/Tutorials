package com.module2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class HomeTask1Demo {

    private RequestSpecification requestSpecification = new RequestSpecBuilder().
            setBaseUri("https://jsonplaceholder.typicode.com").
            setContentType("application/json").
            build();
    private ResponseSpecification responseSpecification = new ResponseSpecBuilder().
            expectContentType("application/json").build();


    @Test
    public void verifyStatusCodeAndBodyForPosts(){

      given().
        spec(requestSpecification).
                when().
                get("/posts").
                then().
                spec(responseSpecification).
                statusCode(200).
                body("userId",notNullValue()).
                body("[0].id",equalTo(1)).
                body("[0].title",equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }
}
