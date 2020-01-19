package com.bae.tests.selenium.tests;

import com.bae.persistence.repo.BoulderRepo;
import com.bae.tests.selenium.constants.Constants;
import com.bae.tests.selenium.pages.BucketListPage;
import com.bae.tests.selenium.pages.IndexPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoulderTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private static ExtentReports extent = new ExtentReports("report.html, true");

    @Autowired
    private BoulderRepo repo;

    @Before
    public void startup() {
        System.setProperty(Constants.PROPERTY, Constants.PATH);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        this.driver.get("http://localhost:" + port + "/index.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().setSize(new Dimension(1600, 700));
        IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
        indexPage.addUser("barry");
        indexPage.login();
    }


    @Test
    public void addBoulderTest() {
        ExtentTest test = extent.startTest("Add boulder test");
        BucketListPage bucketListPage = PageFactory.initElements(driver, BucketListPage.class);
        bucketListPage.addBoulder("rock","place","6B","Completed","01012020","02012020");
        if (bucketListPage.checkBoulder("Rock", "Place", "6B", "Completed", "01/01/2020", "02/01/2020")) {
            test.log(LogStatus.PASS,"Boulder addition success");
        } else {
            test.log(LogStatus.FAIL,"Boulder addition failed");
            extent.endTest(test);
            fail("Add boulder failed");
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
