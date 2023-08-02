package com.Module3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class HomeTask1
{

    @BeforeClass
    private void setUp(){
    RestAssured.baseURI="https://petstore.swagger.io";
    RestAssured.basePath="/v2";
    RestAssured.requestSpecification= new RequestSpecBuilder().
            setContentType("application/json").build();
     }
    @Test
    public void testGetMethod()
    {
        get("/pet/12345").
        then().statusCode(200).
        contentType("application/json").
        body("category.name",equalTo("dog")).and().
        body("name",equalTo("snoopie")).
        body("status",equalTo("pending"));
    }
}
