package searchtests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.List;

public class SearchByTerm {

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup () {
//        EdgeOptions options = new EdgeOptions();
//        options.addArguments("--headless");
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void classTearDown() {
        driver.close();
    }

    @Test
    public void searchWithBing() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");


        driver.get("https://www.bing.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement searchBar = driver.findElement(By.xpath("//input[@name='q' and @type='search']"));
        searchBar.sendKeys("Telerik Academy Alpha");

        WebElement searchButton = driver.findElement(By.xpath("//label[@id='search_icon']"));
        searchButton.click();

        WebElement firstResult = driver.findElement(By.xpath("(//h2/a)[1]"));

        String expectedResult1 = "IT Career Start in 6 Months - Telerik Academy Alpha";
        String expectedResult2 = "Telerik Academy Alpha - IT Career Start in 6 Months";
        String actualResult = firstResult.getText();

        Assertions.assertTrue((actualResult.equals(expectedResult1)) || (actualResult.equals(expectedResult2)),
                "The searched result is not found");

    }

    @Test
    public void searchWithGoogle() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");


        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        agreeButton.click();

        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys("Telerik Academy Alpha");

        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit' and @name='btnK'])[2]"));
        WebElement sideClick = driver.findElement(By.xpath("//img[@alt='Google' and @class='lnXdpd']"));
        sideClick.click();
        searchButton.click();

        WebElement firstResult = driver.findElement(By.xpath("(//a/h3)[1]"));

        String expectedResult1 = "IT Career Start in 6 Months - Telerik Academy Alpha";
        String expectedResult2 = "Telerik Academy Alpha - IT Career Start in 6 Months";
        String actualResult = firstResult.getText();


        Assertions.assertTrue((actualResult.equals(expectedResult1)) || (actualResult.equals(expectedResult2)),
                "The searched result is not found");

    }

}
