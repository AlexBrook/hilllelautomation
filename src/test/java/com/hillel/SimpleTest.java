package com.hillel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class SimpleTest {

    @Test
    public void hillelsimpleTest(){
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://dnipro.ithillel.ua/");
        String url = "https://dnipro.ithillel.ua/";

        driver.navigate().to("https://dnipro.ithillel.ua/");
        driver.navigate().to("https://kharkiv.ithillel.ua/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        String getCurrentUrl = driver.getCurrentUrl();
        System.out.println("current URL: " + getCurrentUrl);
        assertEquals(url,getCurrentUrl,"Wrong opened URL");

        String title = driver.getTitle();
        System.out.println("title" + title);
        //  assertFalse(title.isEmpty(), "Wrong URL");
        assertEquals("",title,"Wrong title");

        String windowHandle = driver.getWindowHandle();




        driver.getCurrentUrl();

        driver.quit();



    }
}
