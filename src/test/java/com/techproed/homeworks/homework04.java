package com.techproed.homeworks;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class homework04 extends DummyTestBase {
    /*http://dummy.restapiexample.com/api/v1/employee/3 url'ine bir GET request gonderdigimizde
    donen response'un asagidaki gibi oldugunu test edin.
    Response Body
    {
            "status":"success",
            "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                    },
            "message":"Successfully! Record has been fetched."
    }*/

    @Test
    public void test(){

        DummyTestData object = new DummyTestData();
        HashMap<String,Object> expectedData = object.dataHomework04();

        spec03.pathParams("parametre1","employee","parametre2",3);
        Response response = given().
                accept("application/json").
                when().
                spec(spec03).
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // 1) JsonPath yontemi
        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedData.get("status"),json.get("status"));
        Assert.assertEquals(expectedData.get("id"),json.get("data.id"));
        Assert.assertEquals(expectedData.get("employee_name"),json.get("data.employee_name"));
        Assert.assertEquals(expectedData.get("employee_salary"),json.get("data.employee_salary"));
        Assert.assertEquals(expectedData.get("employee_age"),json.get("data.employee_age"));
        Assert.assertEquals(expectedData.get("message"),json.get("message"));

        // 2) De Serialization yontemi
        HashMap<String,Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("status"),actualData.get("status"));
        Assert.assertEquals(expectedData.get("id"), ((Map)actualData.get("data")).get("id")  );
        Assert.assertEquals(expectedData.get("employee_name"), ((Map)actualData.get("data")).get("employee_name")  );
        Assert.assertEquals(expectedData.get("employee_salary"), ((Map)actualData.get("data")).get("employee_salary")  );
        Assert.assertEquals(expectedData.get("employee_age"), ((Map)actualData.get("data")).get("employee_age")  );
        Assert.assertEquals(expectedData.get("message"),actualData.get("message"));

        // 3) Matchers Class yontemi

        response.then().assertThat().body("status", equalTo(expectedData.get("status")),
                "data.id",equalTo(expectedData.get("id")),
                "data.employee_name",equalTo(expectedData.get("employee_name")),
                "data.employee_salary",equalTo(expectedData.get("employee_salary")),
                "data.employee_age",equalTo(expectedData.get("employee_age")),
                "message",equalTo(expectedData.get("message")));


    }
}
