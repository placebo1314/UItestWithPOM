import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectMenuTest {

    WebDriver driver;
    SelectMenuPage sMenuPage;

    @BeforeClass
    public void SetUp()
    {
        driver = new ChromeDriver();
        sMenuPage = new SelectMenuPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void QuitDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    void CheckGroupOption() {
        sMenuPage.OpenPage();
        //GroupOption: G-2,O-1
        Utility.ClickElement(driver, sMenuPage.groupOption);
        Utility.ClickElementWithScript(driver, sMenuPage.groupOpt21);

        assertEquals(true, Utility.GetElement(driver, sMenuPage.groupOption).getText().contains("Group 2, option 1"));
    }
        //SelectOne: Mr.
        @Test
        void CheckSelectOne() {
        sMenuPage.OpenPage();
        Utility.ClickElement(driver, sMenuPage.selectOne);
        Utility.ClickElementWithScript(driver, sMenuPage.selectOneMr);

        assertEquals(true, Utility.GetElement(driver, sMenuPage.selectOne).getText().contains("Mr."));
}
        //OldSelectMenu: Black
        @Test
        void CheckOldSelectMenu() {
        sMenuPage.OpenPage();
        Utility.SelectDropDownElement(driver, sMenuPage.color, "Black");

        Select select = new Select(Utility.GetElement(driver, sMenuPage.color));
        assertEquals("Black", select.getFirstSelectedOption().getText());
        }

    @Test
    void HasOldSelectMenuAllRequiredOptions() {
        sMenuPage.OpenPage();

        String s= Utility.GetElement(driver, sMenuPage.color).getText();
        assertEquals(true, s.contains("Red"));
        assertEquals(true, s.contains("Blue"));
        assertEquals(true, s.contains("Green"));
        assertEquals(true, s.contains("Yellow"));
        assertEquals(true, s.contains("Purple"));
        assertEquals(true, s.contains("Black"));
        assertEquals(true, s.contains("White"));
        assertEquals(true, s.contains("Violet"));
        assertEquals(true, s.contains("Indigo"));
        assertEquals(true, s.contains("Magenta"));
        assertEquals(true, s.contains("Aqua"));
    }

    @Test
    void HasMultiSelectAllRequiredOption() {
        sMenuPage.OpenPage();
        Utility.ScrollToElement(driver, sMenuPage.multiSelect);
        Utility.ClickElement(driver, sMenuPage.multiSelect);

        assertEquals(true, Utility.isDisplay(driver, By.xpath("//*[text() = 'Green']")));
        assertEquals(true, Utility.isDisplay(driver, By.xpath("//*[text() = 'Red']")));
        assertEquals(true, Utility.isDisplay(driver, By.xpath("//*[text() = 'Black']")));
        assertEquals(true, Utility.isDisplay(driver, By.xpath("//*[text() = 'Blue']")));
    }

    //multiSelect: Blue, Black
    @Test
    void CheckMultiSelect() throws InterruptedException {
        sMenuPage.OpenPage();
                //assertEquals(true, Utility.isMultiple(driver, sMenuPage.multiSelect));
        Utility.ScrollToElement(driver, sMenuPage.multiSelect);
        Utility.ClickElement(driver, sMenuPage.multiSelect);
        Utility.ClickElementWithScript(driver, sMenuPage.multiSelectBlue);
        //assertEquals(true, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("option Blue, selected."));
        Utility.ClickElementWithScript(driver, sMenuPage.multiSelect);
        Utility.ClickElementWithScript(driver, sMenuPage.multiSelectBlack);
        //assertEquals(true, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("option Black, selected."));
        Utility.ClickElement(driver, sMenuPage.header);
        Thread.sleep(600);

        assertEquals(false, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("Green"));
        assertEquals(true, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("Black"));
        assertEquals(true, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("Blue"));
//        Select select = new Select(Utility.GetElement(driver, sMenuPage.multiSelect));
//        List<WebElement> option = select.getAllSelectedOptions();
//        String defaultItem1 = option.get(0).getText();
//        String defaultItem2 = option.get(1).getText();

//        assertEquals(true, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("option Blue, selected."));
//        assertEquals(true, driver.findElement(By.xpath("//*[text() = 'Green']")).isSelected());
    }

    @Test
    void CheckMultiSelectDeleteAnOption() throws InterruptedException {
        CheckMultiSelect();
        //scroll and deselect Black
        Utility.ScrollToElement(driver, By.cssSelector("div:nth-child(2) > .css-1rhbuit-multiValue path"));
        Utility.ClickElement(driver, By.cssSelector("div:nth-child(2) > .css-1rhbuit-multiValue path"));
        assertEquals(true, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("option Black, deselected"));
    }

    @Test
    void CheckMultiSelectDeleteAllOptions() throws InterruptedException {
        CheckMultiSelect();
        Utility.ScrollToElement(driver, sMenuPage.deleteAllFromMultiSelect);
        Utility.ClickElement(driver, sMenuPage.deleteAllFromMultiSelect);
        Utility.ClickElement(driver, sMenuPage.header);
        Thread.sleep(600);

        assertEquals(true, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("Select..."));
        assertEquals(false, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("Blue"));
        assertEquals(false, Utility.GetElement(driver, sMenuPage.multiSelect).getText().contains("Black"));
    }
}
