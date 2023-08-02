package com.Module3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class HomeTask2 {

    @Test
    public void verifyGetCall()
    {
        Response res=given().baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/users");
        res.then().statusCode(200);
        JsonPath j = new JsonPath(res.asString());
        int s = j.getInt("$.size()");

        for(int i = 0; i < s; i++) {
            String id = j.getString("["+i+"].id");
            String name = j.getString("["+i+"].name");
           if(name=="Ervin Howell") {
               System.out.println(id);
               System.out.println(name);
           }
            }
                //body("[3].name",notNullValue())


    }
//Implementated in another way
    @Test
    public void verifyGetCall1()
    {
      given().baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/users").
                then().statusCode(200).
                body("$.size",greaterThan(3)).
                body("name", hasItem("Ervin Howell"));

    }
}
