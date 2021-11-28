package com.techproed.homeworks;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class homework03 extends JsonPlaceHolderTestBase {
    /*
		1) Create a class and name it as you wish :)
		2) When
		     I send a GET Request to https://jsonplaceholder.typicode.com/todos
		   Then
			 HTTP Status code should be "200"
			 And Content type should be in "JSON" format
			 And there should be 200 "title"
			 And "dignissimos quo nobis earum saepe"(uzun) should be one of the "title"s
			 And 111, 121, and 131 should be among the "id"s(arananIdler)
			 And 4th title is "et porro tempora"(dorduncu)
			 And last title is "ipsam aperiam voluptates qui"(sonuncu)
    */

    @Test
    public void test(){

        JsonPlaceHolderTestData object = new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedData = object.dataHomework03();

        spec01.pathParam("parametre","todos");

        Response response = given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre}");
        //response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        // 1-JsonPath yontemi
        JsonPath json = response.jsonPath();

        Assert.assertEquals(expectedData.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedData.get("titleSayisi"),json.getList("title").size());// ((List)json.get("title")).size()  boyle de yazabilirsin
        Assert.assertTrue(json.getList("title").contains(expectedData.get("uzun")));
        Assert.assertTrue(json.getList("id").containsAll( (List)expectedData.get("arananIdler") ));
        Assert.assertEquals(json.get("title[3]"),expectedData.get("dorduncu"));
        Assert.assertEquals(json.get("title[-1]"),expectedData.get("sonuncu"));


        // 2-De Serialization yontemi
        List<HashMap<String,Object>> actualDataList = response.as(List.class); //burda bize list dondurdugu icin boyle yaptim
        //map dondurseydi map olacakti

        //And there should be 200 "title"
        Assert.assertEquals(expectedData.get("titleSayisi"), actualDataList.size() );

        //And "dignissimos quo nobis earum saepe"(uzun) should be one of the "title"s
        List<Object> titleList = new ArrayList<>();
        for (int i=0; i< actualDataList.size(); i++){
            titleList.add(actualDataList.get(i).get("title"));
        }
        Assert.assertTrue(titleList.contains(expectedData.get("uzun")));

        //And 111, 121, and 131 should be among the "id"s(arananIdler)
        List<Object> idList = new ArrayList<>();
        for (int i=0; i< actualDataList.size(); i++){
            idList.add(actualDataList.get(i).get("id"));
        }
        Assert.assertTrue(idList.containsAll((List)expectedData.get("arananIdler")));

        //And 4th title is "et porro tempora"(dorduncu)
        Assert.assertEquals(actualDataList.get(3).get("title"),expectedData.get("dorduncu"));

        //And last title is "ipsam aperiam voluptates qui"(sonuncu)
        Assert.assertEquals(actualDataList.get(actualDataList.size()-1).get("title"),expectedData.get("sonuncu"));


        // 3-Matchers yontemi

        response.then().
                assertThat().
                body(String.valueOf(actualDataList.size()), hasSize((Integer) expectedData.get("titleSayisi")),
                        "title",hasItem(expectedData.get("uzun")),
                "title[3]", equalTo(expectedData.get("dorduncu")),
                "title[-1]",equalTo(expectedData.get("sonuncu")));
                //body("id",hasItems((List)expectedData.get("arananIdler")));  bu satir neden calismiyor?
    }
}
