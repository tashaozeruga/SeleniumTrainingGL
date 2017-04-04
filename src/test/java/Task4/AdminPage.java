package Task4;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Travel-user on 4/3/2017.
 */
public class AdminPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By childContainerSelector = By.cssSelector("ul[class=docs]");

    @Before
    public void start() {

        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    public void clickAllSelectedChildren(By parentselector, By childselector){

        WebElement element = driver.findElement(parentselector);
        //element.click();
        int size = element.findElements(childselector).size();
        for (int i = 0; i < size; i++) {
            WebElement h = driver.findElement(parentselector);
            List<WebElement> webElements = h.findElements(childselector);
            webElements.get(i).click();
        }

    }

    public boolean checkeElemetPresence(By selector){

        int size = driver.findElements(selector).size();
        boolean result = size != 0;
        return result;

    }

    private boolean validateHeader(String header) {

        WebElement h = (WebElement) driver.findElements(By.cssSelector("h1[style=margin-top: 0px;]"));
        String title = h.getText();
        return header.equals(title);

        //return false;
    }


    @Test
    public void testSelenium() throws IOException {
        System.out.println("HELLO");
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement parentul = driver.findElement(By.cssSelector("ul[class=list-vertical]"));
        //List<WebElement> parentList = parentul.findElements(By.cssSelector("li[id=app-]"));

        int size = parentul.findElements(By.cssSelector("li[id=app-]")).size();

        for (int i = 0; i < size; i++) {


            WebElement container = driver.findElement(By.cssSelector("ul[class=list-vertical]"));
            List<WebElement> pl = container.findElements(By.cssSelector("li[id=app-]"));
            WebElement parentMenuItem = pl.get(i);
            //String header = parentMenuItem.getText();
            //  header.equals(title);

            parentMenuItem.click();
            wait.withTimeout(5000, TimeUnit.MILLISECONDS);
          //  Assert.assertTrue("Header incorrect", validateHeader(header));

            if (checkeElemetPresence(childContainerSelector)) {

                clickAllSelectedChildren(childContainerSelector, By.tagName("li"));

            } else {
                System.out.println("no children");
                //clickAllSelectedChildren(By.cssSelector("table[class=dataTable]"), By.tagName("tr[class=row]"));
            }

        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}