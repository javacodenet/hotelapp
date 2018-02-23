package com.acceptance.tests.selenium;


import com.hoteling.hotelApp.HotelAppApplication;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(
        classes = HotelAppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AppInitialiser {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setup() {
        SharedWebDriver.getInstance().createBaseUrl(port);
        SharedWebDriver.getInstance().cleanUp();
        SharedWebDriver.getInstance().home();
        SharedWebDriver.getInstance().waitForAngular();
    }
}
