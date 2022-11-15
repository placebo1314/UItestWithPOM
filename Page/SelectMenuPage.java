import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectMenuPage {
    WebDriver driver;

    By groupOption = By.xpath("//*[@id=\"withOptGroup\"]");
    By selectOne = By.xpath("//*[@id=\"selectOne\"]");
    By color = By.xpath("//*[@id=\"oldSelectMenu\"]");
    By multiSelect = By.xpath("//*[@id=\"selectMenuContainer\"]/div[7]/div/div");
    By StandardMultiSelect = By.xpath("//*[@id=\"cars\"]");
    By groupOpt21 = By.xpath("//div[@id='react-select-2-option-1-0']");
    By selectOneMr = By.xpath("//*[text() = \"Mr.\"]");
    By multiSelectBlue = By.xpath("//div[@id='react-select-4-option-1']");
    By multiSelectBlack = By.xpath("//div[@id='react-select-4-option-2']");
    By header = By.xpath("//*[@id=\"app\"]/div/div/div[1]/div");
    By deleteAllFromMultiSelect = By.xpath("//*[@id=\"selectMenuContainer\"]/div[7]/div/div/div/div[2]/div[1]");


    public SelectMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void OpenPage()
    {
        driver.get("https://demoqa.com/select-menu");
    }
}
