package com.altice.alticci.integration;

import com.altice.alticci.utils.PathUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashMap;

import static com.altice.alticci.utils.PathUtils.RESOURCE_ALTICCI;
import static io.restassured.RestAssured.when;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlticciIntegrationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    void initialSetup() {
        RestAssured.port = port;
    }

    @Test
    void whenRequestGetAllInCache_thenOk() {
        Response response = when().request(GET.name(), RESOURCE_ALTICCI)
                .then()
                .extract().response();

        assertEquals(OK.value(), response.getStatusCode());
    }

    @Test
    void whenRequestGetByIndex0_thenOkAndReturning0() {
        Long index = 0L;
        String id = String.valueOf(index);

        String result = when()
                .request(GET.name(), PathUtils.joinStringsURL(RESOURCE_ALTICCI, id))
                .then()
                .statusCode(OK.value())
                .extract().response().body().asString();

        assertEquals(0, Long.valueOf(result));
    }

    @Test
    void whenRequestGetByIndex_thenOkAndReturningStaticValue() {
        HashMap<Long, Long> mapMocked = new HashMap<>();
        mapMocked.put(1L, 1L);
        mapMocked.put(2L, 1L);

        Long index = nextLong(1, mapMocked.size());
        String id = String.valueOf(index);

        String result = when()
                .request(GET.name(), PathUtils.joinStringsURL(RESOURCE_ALTICCI, id))
                .then()
                .statusCode(OK.value())
                .extract().response().body().asString();

        assertEquals(mapMocked.get(index), Long.valueOf(result));
    }

    @Test
    void whenRequestGetByIndex_thenOkAndReturningDynamicValue() {

        Long index = nextLong(10000, 100000);
        String id = String.valueOf(index);

        Long firstIndex = index - 3;
        String firstId = String.valueOf(firstIndex);

        Long secondIndex = index - 2;
        String secondId = String.valueOf(secondIndex);

        String result = when()
                .request(GET.name(), PathUtils.joinStringsURL(RESOURCE_ALTICCI, id))
                .then()
                .statusCode(OK.value())
                .extract().response().body().asString();
        BigDecimal resultValue = new BigDecimal(result);

        String resultFirst = when()
                .request(GET.name(), PathUtils.joinStringsURL(RESOURCE_ALTICCI, firstId))
                .then()
                .statusCode(OK.value())
                .extract().response().body().asString();
        BigDecimal firstValue = new BigDecimal(resultFirst);

        String resultSecond = when()
                .request(GET.name(), PathUtils.joinStringsURL(RESOURCE_ALTICCI, secondId))
                .then()
                .statusCode(OK.value())
                .extract().response().body().asString();
        BigDecimal secondValue = new BigDecimal(resultSecond);

        assertEquals(firstValue.add(secondValue), resultValue);
    }

}
