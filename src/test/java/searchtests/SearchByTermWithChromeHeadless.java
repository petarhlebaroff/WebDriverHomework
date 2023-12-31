package searchtests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helpers.Constants.*;
import static helpers.XPaths.*;
import static helpers.XPaths.GOOGLE_FIRST_RESULT;

public class SearchByTermWithChromeHeadless {

    private static WebDriver driver;

    private static WebDriverWait wait;

    @BeforeAll
    public static void classSetup () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(HEADLESS);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void classTearDown() {
        driver.close();
    }

    @Test
    public void resultFound_when_searchTermProvided_inBing() {

        driver.get(BING_URL);

        WebElement searchBar = driver.findElement(By.xpath(BING_SEARCH_BAR));
        searchBar.sendKeys(SEARCHED_TERM);

        WebElement searchButton = driver.findElement(By.xpath(BING_SEARCH_BTN));
        searchButton.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(BING_FIRST_RESULT)));

        WebElement firstResult = driver.findElement(By.xpath(BING_FIRST_RESULT));

        String expectedResult1 = SEARCHED_RESULT_1;
        String expectedResult2 = SEARCHED_RESULT_2;
        String actualResult = firstResult.getText();

        Assertions.assertTrue((actualResult.equals(expectedResult1)) || (actualResult.equals(expectedResult2)),
                FAIL_MESSAGE);
    }

    @Test
    public void resultFound_when_searchTermProvided_inGoogle() throws InterruptedException {


        driver.get(GOOGLE_URL);

        WebElement agreeButton = driver.findElement(By.xpath(GOOGLE_AGREE_BTN));
        agreeButton.click();

        WebElement searchField = driver.findElement(By.xpath(GOOGLE_SEARCH_FIELD));
        searchField.sendKeys(SEARCHED_TERM);

        Thread.sleep(500);
        searchField.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(GOOGLE_FIRST_RESULT)));

        WebElement firstResult = driver.findElement(By.xpath(GOOGLE_FIRST_RESULT));

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(GOOGLE_FIRST_RESULT)));

        String expectedResult1 = SEARCHED_RESULT_1;
        String expectedResult2 = SEARCHED_RESULT_2;
        String actualResult = firstResult.getText();

        Assertions.assertTrue((actualResult.equals(expectedResult1)) || (actualResult.equals(expectedResult2)),
                FAIL_MESSAGE);

    }
}
