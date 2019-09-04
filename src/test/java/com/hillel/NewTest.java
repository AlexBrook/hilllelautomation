package com.hillel;

import com.Hillel.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;



import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewTest {

    private WebDriver driver;
    private User existingUser;

    @BeforeTest
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        existingUser = new User();
        existingUser.setUsername("AlexeyB");
        existingUser.setFirtsName("Alex");
        existingUser.setLastName("Brook");
        existingUser.setEmail("alex@email.com");
        existingUser.setPassword("Pa55word");

    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();


    }

    @Test
    public void doubleRegistertest(){
        driver.get("http://127.0.0.1/");


    }


    @AfterMethod
    public void Teardown() {
        driver.quit();
    }

}



