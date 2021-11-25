package com.techproed.homeworks;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class homework02 extends JsonPlaceHolderTestBase {
    /*
        https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
        donen Response'in
        status code'unun 200,
        ve content type'inin Aplication.JSON,
        ve response body'sinde bulunan userId'nin 5,
        ve response body'sinde bulunan title'in "optio dolor molestias sit"
        oldugunu test edin.
         */
    @Test
    public void test(){

        JsonPlaceHolderTestData obje = new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedData = obje.dataHomework02();

        spec01.pathParams("parametre1","posts","parametre2","44");

        Response response = given().
                accept("application/json").
                spec(spec01).when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //status code'unun 200, ve content type'inin Aplication.JSON oldugunu test et
        response.then().assertThat().statusCode(200).contentType("application/json");


        //JsonPath yontemi
        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedData.get("userId"),json.get("userId"));
        Assert.assertEquals(expectedData.get("title"),json.get("title"));

        //DeSerializiation yontemi
        HashMap<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));

        //Matchers Class
        response.then().assertThat().
                body("userId", Matchers.equalTo(expectedData.get("userId")),
                        "title",Matchers.equalTo(expectedData.get("title")));

    }
}
