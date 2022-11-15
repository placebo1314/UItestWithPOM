import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Utility {

    public static List<WebElement> GetElements(WebDriver driver, By element)
    {
        return driver.findElements(element);
    }

    public static WebElement GetElement(WebDriver driver, By element)
    {
        return driver.findElement(element);
    }

    public static void ClickElement(WebDriver driver, By element) { driver.findElement(element).click();}
    public static void ClickElementWithScript(WebDriver driver, By element)
    {
        WebElement e = driver.findElement(element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", e);
    }

    public static boolean isDisplay(WebDriver driver, By element)
    {
        return driver.findElement(element).isDisplayed();
    }

    public static void ScrollToElement(WebDriver driver, By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }

    public static void ZoomOut(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom = '0.5'");
    }

    public static void ZoomRestoration(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom = '1'");
    }

    public static void SelectDropDownElement(WebDriver driver, By by, String s) {
        Select se = new Select(driver.findElement(by));
        se.selectByVisibleText(s);
    }

    public static boolean isMultiple(WebDriver driver, By element) {
        Select s = new Select(GetElement(driver, element));
        return s.isMultiple();
    }
}
