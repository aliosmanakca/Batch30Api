package com.techproed.homeworks;

import com.techproed.testBase.HerokuAppTestBase;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class homework01 extends HerokuAppTestBase {
    // https://restful-booker.herokuapp.com/booking/10 url'ine bir GET request gonderdigimizde donen Response'un,
    //        status code'unun 200,
    //        ve content type'inin application/json; charset=utf-8,
    //        ve Server isimli Header'in degerinin Cowboy,
    //        ve status Line'in HTTP/1.1 200 OK
    //        ve response suresinin 5 sn'den kisa oldugunu manuel olarak test ediniz.
    @Test
    public void test() throws InterruptedException {
        spec02.pathParams("parametre1","booking","parametre2",10);

        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);

        Response response = given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(now2);

        Duration dur = Duration.between(now1,now2);
        System.out.println(dur);

        long saniye = dur.toSeconds();
        System.out.println(saniye);

        Assert.assertTrue("5 saniyeden uzun suruyor",saniye<=5);

        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType("application/json");
        response.then().assertThat().header("Server", equalTo("Cowboy"));
        response.then().assertThat().statusLine("HTTP/1.1 200 OK");

        /* bu sekilde yazmak daha professional
        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                header("Server", equalTo("Cowboy")).
                statusLine("HTTP/1.1 200 OK");  */


    }

}
