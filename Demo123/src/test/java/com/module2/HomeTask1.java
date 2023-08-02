package com.module2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;

public class HomeTask1 {
    @BeforeClass
    private void setUp(){
        RestAssured.requestSpecification= new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                setContentType("application/json").build();

        RestAssured.responseSpecification= (ResponseSpecification) new ResponseSpecBuilder().
               build();
    }
    @Test
    public void verifyAllTestsforPosts()
    {
        Map<String,Object> postMap=new HashMap<>();
        postMap.put("title","foo");
        postMap.put("body","bar");
        postMap.put("userId",1);
        //post call
        String newId= given().body(postMap).when().post("/posts").path("id").toString();


    }
    @Test
    public void verifyGetForPosts() {
        Response response = get("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));
    }
    @Test
    public void verifyPostForPosts(){
        String reqBody="{\"userId\":87,\"id\":87,\"title\":\"newly created\",\"body\":\"simple test\"}";
        Response res=given().
                body(reqBody).
                when()
                .post("/posts");
        String id=res.path("id").toString();
        System.out.println("new resource has been created with id:"+ id);
        Assert.assertEquals(201, res.statusCode());
        Assert.assertEquals("newly created", res.jsonPath().getString("title"));
        Assert.assertEquals("simple test", res.jsonPath().getString("body"));
        Assert.assertEquals("87", res.jsonPath().getString("userId"));
        Assert.assertEquals("87", res.jsonPath().getString("id"));
    }

    @Test
    public void verifyPutForPosts()
    {
        String reqBody="{\"id\":87,\"userId\":87,\"title\":\"newly created and updated\",\"body\":\"simple test\"}";
        Response res=given().
                body(reqBody).
                when()
                .put("/posts/87");
        res.then().statusCode(200).body("title",equalTo("newly created and updated"));
        String id=res.path("id").toString();
        System.out.println("resource has been created with id:"+ id);

    }
    //it does not return any body in API response
    @Test
    public void verifydeleteForPosts(){
        delete("/posts/87").then().statusCode(200);
    }

    @Test
    public void verifyGetMethodForComments()
    {
        get("/comments").
        then().statusCode(200).
                body("postId",notNullValue()).
                body("[0].id",equalTo(1)).
                body("[0].name",equalTo("id labore ex et quam laborum")).
                body("$.size",greaterThan(5)).
                body("[0].email",equalTo("Eliseo@gardner.biz"));
                }

    @Test
    public void verifydeleteForComments(){
        delete("/comments/11").then().statusCode(200);
    }

    @Test
    public void verifyGetForAlbums() {
        get("/albums").
                then().statusCode(200).
                body("userId", notNullValue()).
                body("[1].id", equalTo(2)).
                body("[1].title", equalTo("sunt qui excepturi placeat culpa")).
                body("$.size", greaterThan(5));
    }
    @Test
    public void verifyPostForAlbum()
    {
        String reqBody="";
        given().
        body("{\n" +
                "    \"title\": \"qweo\",\n" +
                "    \"body\": \"abc\",\n" +
                "    \"userId\": 234\n" +
                "  }").
                when()
                .post("/albums").
                then().
                statusCode(201)
                .body("id",notNullValue());
    }
    //it does not return any body in API response
    @Test
    public void verifydeleteForAlbums(){
        delete("/albums/1").then().statusCode(200);
    }
    @Test
    public void verifyGetForPhotos() {
        get("/photos").
                then().statusCode(200).
                body("albumId", notNullValue()).
                body("[0].id", equalTo(1)).
                body("[0].title", equalTo("accusamus beatae ad facilis cum similique qui sunt")).
                body("$.size", greaterThan(5)).
                body("[0].url",equalTo("https://via.placeholder.com/600/92c952"));

    }
    @Test
    public void verifyPostForPhotos(){
    String reqBody="{\"albumId\":30,\"title\":\"natus nisi omnis corporis facere molestiae rerum in\",\"url\":\"https://via.placeholder.com/600/f66b97\",\"thumbnailUrl\":\"https://via.placeholder.com/150/f66b97\"}";
        Response res=given().
                body(reqBody).
                when()
                .post("/photos");
                String id=res.path("id").toString();
                System.out.println("new resource has been created with id:"+ id);
    }
    //it does not return any body in API response
    @Test
    public void verifydeleteForPhotos(){
        delete("/photos/1").then().statusCode(200);
    }

    @Test
    public void verifyGetForTodos() {
        get("/todos/10").
                then().statusCode(200).
                body("userId", notNullValue()).
                body("id", equalTo(10)).
                body("title", equalTo("illo est ratione doloremque quia maiores aut")).
                body("completed",equalTo(true));

    }
    @Test
    public void verifyPostForTodos(){
        String reqBody="{\"userId\":101,\"title\":\"created by user\",\"completed\":false}";
        Response res=given().
                body(reqBody).
                when()
                .post("/todos");
        res.then().statusCode(201);
        String id=res.path("id").toString();
        System.out.println("new resource has been created with id:"+ id);
    }
    @Test
    public void verifyPutForTodos()
    {
        String reqBody="{\"userId\":1,\"id\":1,\"title\":\"updated by user\",\"body\":\"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"}";
        Response res=given().
                body(reqBody).
                when()
                .put("/todos/1");
        res.then().statusCode(200).body("title",equalTo("updated by user"));
        String id=res.path("id").toString();
        System.out.println("resource has been created with id:"+ id);

    }
    @Test
    public void verifydeleteForTodos(){
        delete("/todos/201").then().statusCode(200);
    }
    @Test
    public void verifyGetForUsers() {
        get("/users").
                then().statusCode(200).
                body("[0].name", equalTo("Leanne Graham")).
                body("[0].id", equalTo(1)).
                body("[0].address.zipcode", equalTo("92998-3874")).
                body("[0].address.geo.lat",equalTo("-37.3159"));

    }
    @Test
    public void verifyPostForUsers(){
        String reqBody="{\"name\":\"Bhakti\",\"username\":\"Bhakti.Stanton\",\"email\":\"Rey.pathak@karina.biz\",\"address\":{\"street\":\"Kattie Turnpike\",\"suite\":\"Suite 198\",\"city\":\"Lebsackbury\",\"zipcode\":\"31428-2261\",\"geo\":{\"lat\":\"-38.2386\",\"lng\":\"57.2232\"}},\"phone\":\"024-648-3804\",\"website\":\"ambrose.net\",\"company\":{\"name\":\"Hoeger LLC\",\"catchPhrase\":\"Centralized empowering task-force\",\"bs\":\"target end-to-end models\"}}";
        Response res=given().
                body(reqBody).
                when()
                .post("/users");
        res.then().statusCode(201);
        String id=res.path("id").toString();
        System.out.println("new resource has been created with id:"+ id);
    }

    @Test
    public void verifyPutForUsers()
    {
        String reqBody="{\"id\":10,\"name\":\"Clementina \",\"username\":\"Moriah.Stanton\",\"email\":\"Rey.Padberg@karina.biz\",\"address\":{\"street\":\"Kattie Turnpike\",\"suite\":\"Suite 198\",\"city\":\"Lebsackbury\",\"zipcode\":\"31428-2261\",\"geo\":{\"lat\":\"-38.2386\",\"lng\":\"57.2232\"}},\"phone\":\"024-648-3804\",\"website\":\"ambrose.net\",\"company\":{\"name\":\"Hoeger LLC\",\"catchPhrase\":\"Centralized empowering task-force\",\"bs\":\"target end-to-end models\"}}";
        Response res=given().
                body(reqBody).
                when()
                .put("/users/10");
        res.then().statusCode(200).body("username",equalTo("Moriah.Stanton"));
        String id=res.path("id").toString();
        System.out.println("resource has been created with id:"+ id);

    }
    @Test
    public void verifydeleteForUsers(){
        delete("/users/10").then().statusCode(200);
    }

    }


