package com.module4;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class HomeTask1 {

    @BeforeClass
            public void setUp()
    {
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://dummy.restapiexample.com/api/").
                setBasePath("/v1").
                setContentType("application/json").
                build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().
                expectHeader("Content-Encoding", "gzip").
                expectContentType("application/json").
                build();
    }


    @Test
    public void verifyAllMethodsForEmployee() throws InterruptedException {
        //step 1
        Response res=get("/employees");
        res.then().statusLine("HTTP/1.1 200 OK").log().all();
        JsonPath js = new JsonPath(res.asString());
        int size = js.getInt("data.size()");
        System.out.println("total no of resources are :"+size);

        //step2
        Map<String, Object> empMap=new HashMap<>();
        empMap.put("name","John abc");
        empMap.put("salary",23000);
        empMap.put("age",21);
       // empMap.put("id",56);
        String empId=given().body(empMap).when().post("/create").path("data.id").toString();
        //String empId= res1.jsonPath().getString("data.id");
        //Response res1=given().body(empMap).when().post("/create");
        //String empId= res1.jsonPath().getString("data.id");
        System.out.println("emp: "+empId);
        /*res1.then().statusCode(200).
        body("status",equalTo("success"));
*/
        //step 3
        get("/employee/"+empId).then().statusLine("HTTP/1.1 200 OK").
                body("status",equalTo("success")).
                body("data",nullValue());

     //step 4
        get("/employee/10").then().statusLine("HTTP/1.1 200 OK").
                body("data.employee_name",equalTo("Sonya Frost")).
                body("data.employee_salary",equalTo(103600)).
                body("data.age",equalTo(23));

        Response res1=get("/employees");
        res.then().statusLine("HTTP/1.1 200 OK").log().all();
        JsonPath js1 = new JsonPath(res1.asString());
        int size1 = js1.getInt("data.size()");
        System.out.println("total no of resources  are :"+size1);

        //step 5 updating the existing resource
        empMap.put("name","Rahul");
        given().body(empMap).when().put("/update/"+empId).then().statusCode(200);
        //step 6
        delete("/delete/"+empId).then().statusCode(200).
                body("status",equalTo("success")).
                body("data",equalTo(empId));
    }
}
