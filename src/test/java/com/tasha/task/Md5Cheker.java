package com.tasha.task;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by tasha on 29.06.17.
 */
public class Md5Cheker {

    private WebDriver driver;
    private WebDriverWait wait;

    private String md5FingerprintActual = "ef9ecb289838bf9ff5a2fac936fc6b52";
    private String md5FingerprintTest;

    @Before
    public void start() {

        //FirefoxDriverManager.getInstance().setup();
        ChromeDriverManager.getInstance().setup();
        //InternetExplorerDriverManager.getInstance().setup();

        //driver = new InternetExplorerDriver();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void MyTest() {

        driver.get("localhost:8080");
        //driver.findElement(By.name("q")).sendKeys("automated testing");
        wait.until(ExpectedConditions.titleIs("Checksum It!"));
        driver.findElement(By.name("upload")).sendKeys("/home/tasha/Desktop/QA_Test/Files/Test_QA.pdf");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h3")));
        md5FingerprintTest = driver.findElement(By.tagName("td")).getText();
        //Assert.assertTrue(md5FingerprintActual.equals(md5FingerprintTest));
        Assert.assertTrue("Md5sum fingerprint is incorrect", md5FingerprintActual.equals(md5FingerprintTest));
        driver.findElement(By.tagName("a")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
