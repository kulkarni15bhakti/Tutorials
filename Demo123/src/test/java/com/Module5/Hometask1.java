package com.Module5;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class Hometask1 {

    @BeforeClass
    private void setUp(){
        RestAssured.baseURI="https://wearecommunity.io/api";
        RestAssured.basePath="/v2";
        RestAssured.requestSpecification= new RequestSpecBuilder().
                setContentType("application/json").build();
    }
    @Test
    public void verifyEventName()
    {
        Response res=get("/parts/events/popular").then()
            .extract().response();
        Assert.assertEquals( res.statusCode(),200);

        // finding all event names
        List<String> eventNames=res.path("title");
        System.out.println("Count of events:"+eventNames.size());
        Assert.assertEquals(4, eventNames.size());
        Assert.assertEquals(eventNames.get(0),"DoNut Conversations (July)");
        Assert.assertEquals(eventNames.get(1),"ParentSmart Summer Camp (Ru)");
        Assert.assertEquals(eventNames.get(2),"English Speaking Events");
        Assert.assertEquals(eventNames.get(3),"Mobile People Community Day 2023");
    }
}
