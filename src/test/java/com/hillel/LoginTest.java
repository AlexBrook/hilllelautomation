package com.hillel;

import com.Hillel.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class LoginTest {

    private WebDriver driver;
    private User existingUser;

    @BeforeTest
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
        existingUser = new User();
        existingUser.setUsername("AlexeyB");
        existingUser.setFirtsName("Alex");
        existingUser.setLastName("Brook");
        existingUser.setEmail("alex@email.com");
        existingUser.setPassword("Pa55word");

    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(4000,TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(4000,TimeUnit.MILLISECONDS);


    }



    @Test
    public void loginTest() throws InterruptedException {
        driver.get("http://127.0.0.1/");

        By loginButtonLocator = By.cssSelector("#login>a");

        WebElement loginLink = visibilityOfElementLocated(loginButtonLocator);
        loginLink.click();

        WebElement loginModalWindow = driver.findElement(By.id("login-modal"));

        WebElement userNameInput = visibilityOf(loginModalWindow.findElement(By.id("username-modal")));
        userNameInput.clear();
        userNameInput.sendKeys(existingUser.getPassword());

        WebElement password = loginModalWindow.findElement(By.id("password-modal"));
        password.clear();
        password.sendKeys(existingUser.getPassword());

        WebElement signIn = loginModalWindow.findElement(By.cssSelector("#login-modal .fa-sign-in"));
        signIn.click();

        WebElement logOutBTN = driver.findElement(By.id("logout"));


        By loggedInInfoLocator = By.cssSelector("#howdy>a");
        WebElement loggedInInfo = visibilityOfElementLocated(loggedInInfoLocator);
        String infoText = loggedInInfo.getText();
        String expectedInfo = String.format("Logged in as %s %s",existingUser.getFirtsName(),existingUser.getLastName());
        assertEquals(infoText,expectedInfo);

    }

    private WebElement visibilityOfElementLocated(By locator){
        WebDriverWait wait = new WebDriverWait(driver,5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement visibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,5);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement elementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,5);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private boolean elementToBeClickable(WebElement element,String attribute,String value){
        WebDriverWait wait = new WebDriverWait(driver,5);
        return wait.until(ExpectedConditions.attributeContains(element,attribute,value));
    }


    @AfterMethod
    public void Teardown(){
        driver.quit();
    }


}
