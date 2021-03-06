package com.techproed.testData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String,Object> setUpTestData(){

        HashMap<String,Object> expectedData=new HashMap<String, Object>();

        expectedData.put("statusCode",200);
        expectedData.put("via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        return expectedData;
    }

    /*
{
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
 */

    public JSONObject setUpPostData(){

        JSONObject expectedRequest=new JSONObject();

        expectedRequest.put("statusCode",201);
        expectedRequest.put("userId",55);
        expectedRequest.put("title","Tidy your room");
        expectedRequest.put("completed",false);

        return expectedRequest;
    }

    /*
    {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false
 }
     */

    public JSONObject setUpPutData(){

        JSONObject expectedRequest = new JSONObject();

        expectedRequest.put("userId",21);
        expectedRequest.put("title","Wash the dishes");
        expectedRequest.put("completed",false);

        return expectedRequest;
    }

    public HashMap<String, Object> dataHomework02(){

        HashMap<String,Object> expected = new HashMap<>();

        expected.put("userId",5);
        expected.put("title","optio dolor molestias sit");

        return expected;
    }


    public HashMap<String, Object> dataHomework03(){

        List<Integer> idler = new ArrayList<>();
        idler.add(111);
        idler.add(121);
        idler.add(131);

        HashMap<String,Object> expected = new HashMap<>();
        expected.put("statusCode",200);
        expected.put("uzun","dignissimos quo nobis earum saepe");
        expected.put("dorduncu","et porro tempora");
        expected.put("sonuncu","ipsam aperiam voluptates qui");
        expected.put("arananIdler",idler);
        expected.put("titleSayisi",200);

        return expected;
    }

    public JSONObject setUpPatchRequestData(){

        JSONObject requestData=new JSONObject();
        requestData.put("title","API calismaliyim");

        return requestData;
    }

    public JSONObject setUpPatchExpectedData() {
        /*
        {
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
         */

        JSONObject expectedData=new JSONObject();
        expectedData.put("userId",10);
        expectedData.put("title","API calismaliyim");
        expectedData.put("completed",true);
        expectedData.put("id",198);

        return expectedData;
    }


}
