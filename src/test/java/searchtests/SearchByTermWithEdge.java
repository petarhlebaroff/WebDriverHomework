package searchtests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchByTermWithEdge {
    private static WebDriver driver;

    private static WebDriverWait wait;

    @BeforeAll
    public static void classSetup () {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void classTearDown() {
        driver.close();
    }

    @Test
    public void searchWithBing() {

        driver.get("https://www.bing.com/");

        WebElement searchBar = driver.findElement(By.xpath("//input[@name='q' and @type='search']"));
        searchBar.sendKeys("Telerik Academy Alpha");

        WebElement searchButton = driver.findElement(By.xpath("//label[@id='search_icon']"));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//h2)[1]")));

        WebElement firstResult = driver.findElement(By.xpath("(//h2/a)[1]"));

        String expectedResult1 = "IT Career Start in 6 Months - Telerik Academy Alpha";
        String expectedResult2 = "Telerik Academy Alpha - IT Career Start in 6 Months";
        String actualResult = firstResult.getText();

        Assertions.assertTrue((actualResult.equals(expectedResult1)) || (actualResult.equals(expectedResult2)),
                "The searched result is not found");
    }

    @Test
    public void searchWithGoogle() {

        driver.get("https://www.google.com/");

        WebElement agreeButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        agreeButton.click();

        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys("Telerik Academy Alpha");


        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit' and @name='btnK'])[2]"));
        WebElement sideClick = driver.findElement(By.xpath("//img[@alt='Google' and @class='lnXdpd']"));
        sideClick.click();
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//a/h3)[1]")));

        WebElement firstResult = driver.findElement(By.xpath("(//a/h3)[1]"));

        String expectedResult1 = "IT Career Start in 6 Months - Telerik Academy Alpha";
        String expectedResult2 = "Telerik Academy Alpha - IT Career Start in 6 Months";
        String actualResult = firstResult.getText();

        Assertions.assertTrue((actualResult.equals(expectedResult1)) || (actualResult.equals(expectedResult2)),
                "The searched result is not found");

    }

}
