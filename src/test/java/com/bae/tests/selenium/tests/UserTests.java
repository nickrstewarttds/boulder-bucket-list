package com.bae.tests.selenium.tests;

import com.bae.tests.selenium.constants.Constants;
import com.bae.tests.selenium.pages.IndexPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private static ExtentReports extent = new ExtentReports("report.html, true");

    @Before
    public void startup() {
        System.setProperty(Constants.PROPERTY, Constants.PATH);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1600, 700));
    }

    @Test
    public void userSetupTest() {
        this.driver.get("http://localhost:" + port + "/index.html");
        ExtentTest test = extent.startTest("Add user test");
        IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
        indexPage.addUser("barry");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/table/tr/td")));
        if (indexPage.checkUser("Barry")) {
            test.log(LogStatus.PASS, "User registration success");
        } else {
            test.log(LogStatus.FAIL,"User Registration failed");
            extent.endTest(test);
            fail("Registration failed");
        }
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @AfterClass
    public static void flushReport() {
        extent.flush();
    }
}
