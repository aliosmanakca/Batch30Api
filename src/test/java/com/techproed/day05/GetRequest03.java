package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest03 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
accept type'i "application/json" olan GET request'i yolladigimda
gelen response'un
status kodunun 200
ve content type'inin "application/json"
ve firstname'in "Mary"
ve lastname'in "Jones"
ve checkin date'in 2018-10-07"
ve checkout date'in 2020-09-30 oldugunu test edin
     */
    @Test
    public void test(){
        String url="https://restful-booker.herokuapp.com/booking/7";

        Response response= given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Susan")).
                body("lastname", equalTo("Jackson")).
                body("totalprice", equalTo(811)).
                body("depositpaid", equalTo(true)).
                body("bookingdates.checkin", equalTo("2021-08-11")).
                body("bookingdates.checkout", equalTo("2021-10-18"));

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Susan"),
                        "lastname", equalTo("Jackson"),
                        "totalprice", equalTo(811),
                        "bookingdates.checkin",equalTo("2021-08-11"),
                        "bookingdates.checkout",equalTo("2021-10-18"));
    }

}
