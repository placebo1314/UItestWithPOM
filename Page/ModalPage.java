import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ModalPage {
    WebDriver driver;

    By modalHeader = By.xpath("/html/body/div[4]/div/div/div[1]");
    By name = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]");
    By email = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[2]");
    By gender = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]");
    By mobile = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]");
    By dateOfBirth = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[5]/td[2]");
    By subjects = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[6]/td[2]");
    By hobbies = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[7]/td[2]");
    By picture = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[8]/td[2]");
    By address = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[9]/td[2]");
    By stateAndCity = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[10]/td[2]");

    public ModalPage(WebDriver driver) {
        this.driver = driver;
    }


}
