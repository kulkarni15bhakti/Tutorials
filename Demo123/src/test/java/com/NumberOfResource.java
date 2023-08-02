package com;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NumberOfResource {


    @Test
    public void verifyNoOfResource(){
        given().
                baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/posts").
                then().
                assertThat().statusCode(200).
                log().all();


    }
    @Test
    public void verifyNoOfResourceforPosts(){
        Response res=given().
                baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/posts");
        res.then().statusCode(200);
        JsonPath js = new JsonPath(res.asString());
        int size = js.getInt("data.size()");
        System.out.println("total no of resources in Posts endpoint are :"+size);
    }
    @Test
    public void verifyNoOfResourceforComments(){
        Response res=given().
                baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/comments");
        res.then().statusCode(200);
        JsonPath js = new JsonPath(res.asString());
        int size = js.getInt("data.size()");
        System.out.println("total no of resources in Comments endpoint are :"+size);
    }
    @Test
    public void verifyNoOfResourceforAlbums(){
        Response res=given().
                baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/albums");
        res.then().statusCode(200);
        JsonPath js = new JsonPath(res.asString());
        int size = js.getInt("data.size()");
        System.out.println("total no of resources in albums endpoint are :"+size);
    }
    @Test
    public void verifyNoOfResourceforPhotos(){
        Response res=given().
                baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/photos");
        res.then().statusCode(200);
        JsonPath js = new JsonPath(res.asString());
        int size = js.getInt("data.size()");
        System.out.println("total no of resources in Photos endpoint are :"+size);
    }
    @Test
    public void verifyNoOfResourceforTodos(){
        Response res=given().
                baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/todos");
        res.then().statusCode(200);
        JsonPath js = new JsonPath(res.asString());
        int size = js.getInt("data.size()");
        System.out.println("total no of resources in Todos endpoint are :"+size);
    }
    @Test
    public void verifyNoOfResourceforUsers(){
        Response res=given().
                baseUri("https://jsonplaceholder.typicode.com").
                when().
                get("/users");
        res.then().statusCode(200);
        JsonPath js = new JsonPath(res.asString());
        int size = js.getInt("data.size()");
        System.out.println("total no of resources in users endpoint are :"+size);
    }

}
