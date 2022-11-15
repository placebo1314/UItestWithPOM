import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class PracticeFormPage {
    WebDriver driver;
    By firstName = By.xpath("//*[@id=\"firstName\"]");
    By lastName = By.xpath("//*[@id=\"lastName\"]");
    By maleGender = By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]");
    By mobileNumber = By.xpath("//*[@id=\"userNumber\"]");
    By submitButton = By.xpath("//*[@id=\"submit\"]");
    By dateSetter = By.xpath("//*[@id=\"dateOfBirthInput\"]");
    By yearSetter = By.className("react-datepicker__year-select");
    By stateSetter = By.xpath("//input[@id='react-select-3-input']");
    By selectUttarSate = By.xpath("//div[@id='react-select-3-option-1']");
    By citySetter = By.xpath("//input[@id='react-select-4-input']");
    By selectAgraCity = By.xpath("//div[@id='react-select-4-option-0']");
    By uploadPicture = By.xpath("//*[@id=\"uploadPicture\"]");

    public PracticeFormPage(WebDriver driver)
    {
        this.driver=driver;
    }


    public void OpenPage()
    {
        driver.get("https://demoqa.com/automation-practice-form");
    }
    public void FillAllRequiredField()
    {
        FillFirstName("Alpha");
        FillLastName("Beta");
        SelectGender();
        FillMobileNumber("0101010101");
    }

    public void SubmitAllRequiredWithCustomMobile(String mobile)
    {
        driver.findElement(firstName).sendKeys("Alpha");
        driver.findElement(lastName).sendKeys("Beta");
        driver.findElement(maleGender).click();
        driver.findElement(mobileNumber).sendKeys(mobile);
    }

    public void FillFirstName(String name)
    {
        driver.findElement(firstName).sendKeys(name);
    }

    public void FillLastName(String name)
    {
        driver.findElement(lastName).sendKeys(name);
    }

    public void FillMobileNumber(String mobile)
    {
        driver.findElement(mobileNumber).sendKeys(mobile);
    }

    public void SelectGender()
    {
        driver.findElement(maleGender).click();
    }

    public void SelectYear(String year)
    {
        year = "//*[text()='"+year+"']";
        driver.findElement(By.xpath(year)).click();
    }

    public void SubmitForm()
    {
        driver.findElement(submitButton).submit();
    }

    public String getActualMonth()
    {
        String currentMonth = LocalDate.now().getMonth().toString();
        return (currentMonth.substring(0, 1).toUpperCase() + currentMonth.substring(1).toLowerCase());
    }

    public void SelectDate(String year)
    {
        Utility.ScrollToElement(driver, dateSetter);
        Utility.ClickElement(driver, dateSetter);
        Utility.ClickElement(driver, yearSetter);
        SelectYear(year);
        Utility.ClickElement(driver, SetDay(year));
    }

    private By SetDay(String year)
    {
        String label = "//div[contains(@aria-label,' 1st, "+year+"')]";
        return By.xpath(label);
        //By.xpath("//div[contains(@aria-label, ' 1st, 2006')]");
    }

    public void SelectState() {
        Utility.ClickElement(driver, stateSetter);
    }
}
