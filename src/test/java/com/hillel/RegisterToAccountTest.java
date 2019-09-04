package com.hillel;

import com.Hillel.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterToAccountTest {

    private WebDriver driver;
    private User newUser;

    @BeforeClass
    public void setUpBeforeClass(){
        WebDriverManager.chromedriver().setup();
        newUser = new User();
        newUser.setUsername("Niko");
        newUser.setFirtsName("Niko");
        newUser.setLastName("Belic");
        newUser.setEmail("belic@mail.com");
        newUser.setPassword("qwerty123");
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(4000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(4000,TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void RegisterTest(){
        driver.get("http://127.0.0.1/");

        By registerBtnLocator = By.cssSelector("#register>a");

        WebElement registerLink = visibilityOfElementLocated(registerBtnLocator);
        registerLink.click();

        WebElement registerModalWindow = driver.findElement(By.id("register-modal"));

        WebElement userNameInput = visibilityOf(registerModalWindow.findElement(By.id("register-username-modal")));
        userNameInput.clear();
        userNameInput.sendKeys(newUser.getUsername());

        WebElement firstNameInput = registerModalWindow.findElement(By.id("register-first-modal"));
        firstNameInput.clear();
        firstNameInput.sendKeys(newUser.getFirtsName());

        WebElement lastNameInput = registerModalWindow.findElement(By.id("register-last-modal"));
        lastNameInput.clear();
        lastNameInput.sendKeys(newUser.getLastName());

        WebElement emailInput = registerModalWindow.findElement(By.id("register-email-modal"));
        emailInput.clear();
        emailInput.sendKeys(newUser.getEmail());

        WebElement passwordInput = registerModalWindow.findElement(By.id("register-password-modal"));
        passwordInput.clear();
        passwordInput.sendKeys(newUser.getPassword());

        WebElement registerBTN = registerModalWindow.findElement(By.cssSelector(". fa fa-sign-in"));
        registerBTN.click();

        WebElement logoutBtn = driver.findElement(By.id("logout"));
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

}
