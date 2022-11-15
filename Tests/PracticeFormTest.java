import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PracticeFormTest {
    WebDriver driver;
    PracticeFormPage practiceFormPage;
    ModalPage modalPage;

    @BeforeClass
    public void SetUp()
    {
        //System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver.exe");
        driver = new ChromeDriver();
        practiceFormPage = new PracticeFormPage(driver);
        modalPage = new ModalPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void QuitDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    public void ValidatePageLayout()
    {
        practiceFormPage.OpenPage();
        assertThat(Utility.isDisplay(driver, practiceFormPage.firstName)).isTrue();
        assertThat(Utility.isDisplay(driver, practiceFormPage.lastName)).isTrue();
        assertThat(Utility.isDisplay(driver, practiceFormPage.maleGender)).isTrue();
        assertThat(Utility.isDisplay(driver, practiceFormPage.mobileNumber)).isTrue();
        assertThat(Utility.isDisplay(driver, practiceFormPage.submitButton)).isTrue();
    }

    @Test
    void submitWithEmptyFields() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitForm();

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SuccesfullSubmit() {
        practiceFormPage.OpenPage();
        practiceFormPage.FillAllRequiredField();
        practiceFormPage.SubmitForm();
        //Actions actions = new Actions(p.driver);
        //actions.moveToElement(p.male).click().build().perform();

        assertEquals( "Alpha Beta", Utility.GetElement(driver, modalPage.name).getText());
        assertEquals("Male", Utility.GetElement(driver, modalPage.gender).getText());
        assertEquals("0101010101", Utility.GetElement(driver, modalPage.mobile).getText());
    }

    @Test
    void SubmitDigitsInName() {
        practiceFormPage.OpenPage();
        practiceFormPage.FillFirstName("1212");
        practiceFormPage.FillLastName("2121");
        practiceFormPage.FillMobileNumber("0000000100");
        practiceFormPage.SelectGender();
        practiceFormPage.SubmitForm();

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SubmitSpecialCharsInName() {
        practiceFormPage.OpenPage();
        practiceFormPage.FillFirstName("aA@/*lk");
        practiceFormPage.FillLastName("CCg=!f?D");
        practiceFormPage.FillMobileNumber("0000000100");
        practiceFormPage.SelectGender();
        practiceFormPage.SubmitForm();

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SubmitStringInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("0000aN0100");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }


    @Test
    void SubmitNegativeNumber10DigitsInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("-9992121212");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SubmitNegativeNumber9DigitsInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("-991215212");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SubmitBigestNumberInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("9999999999");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SubmitFloatNumber10DigitsInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("12.12199212");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SubmitFloatNumber9DigitsInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("12.1299212");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void Submit11DigitsInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("23912992412");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void Submit9DigitsInMobile() {
        practiceFormPage.OpenPage();
        practiceFormPage.SubmitAllRequiredWithCustomMobile("239122412");

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SetValidBirthDate() {
        String validDate = "2006";
        practiceFormPage.OpenPage();
        practiceFormPage.FillAllRequiredField();
        practiceFormPage.SelectDate(validDate);
        Utility.ScrollToElement(driver, practiceFormPage.submitButton);
        practiceFormPage.SubmitForm();

        assertEquals("01 " + practiceFormPage.getActualMonth() + ","+validDate, Utility.GetElement(driver, modalPage.dateOfBirth).getText());
    }

    @Test
    void SetInvalidBirthDate() {
        practiceFormPage.OpenPage();
        practiceFormPage.FillAllRequiredField();
        practiceFormPage.SelectDate("2055");
        practiceFormPage.SubmitForm();

        assertEquals(true, Utility.GetElements(driver, modalPage.modalHeader).isEmpty());
    }

    @Test
    void SetStateBasicWay() {
        practiceFormPage.OpenPage();
        practiceFormPage.FillAllRequiredField();
        Utility.ScrollToElement(driver, practiceFormPage.stateSetter);
        //Egybefogni
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(practiceFormPage.stateSetter)).click().build().perform();
        actions.moveToElement(driver.findElement(practiceFormPage.selectUttarSate)).click().build().perform();
        actions.moveToElement(driver.findElement(practiceFormPage.citySetter)).click().build().perform();
        actions.moveToElement(driver.findElement(practiceFormPage.selectAgraCity)).click().build().perform();
        practiceFormPage.SubmitForm();

        assertEquals( "Uttar Pradesh Agra", Utility.GetElement(driver, modalPage.stateAndCity).getText());
    }

    @Test
    void SetStateWithKeys() {
        practiceFormPage.OpenPage();
        practiceFormPage.FillAllRequiredField();
        Utility.ZoomOut(driver);
        Utility.GetElement(driver, practiceFormPage.stateSetter).sendKeys("U");
        Utility.GetElement(driver, practiceFormPage.stateSetter).sendKeys(Keys.ENTER);
        Utility.GetElement(driver, practiceFormPage.citySetter).sendKeys("A");
        Utility.GetElement(driver, practiceFormPage.citySetter).sendKeys(Keys.ENTER);
        practiceFormPage.SubmitForm();
        Utility.ZoomRestoration(driver);

        assertEquals( "Uttar Pradesh Agra", Utility.GetElement(driver, modalPage.stateAndCity).getText());
    }

    @Test
    void UploadPic() {
        String pic = "C:\\Users\\Krisz\\Desktop\\pic1.jpg";
        practiceFormPage.OpenPage();
        practiceFormPage.FillAllRequiredField();
        Utility.GetElement(driver, practiceFormPage.uploadPicture).sendKeys(pic);
        practiceFormPage.SubmitForm();

        assertEquals( "pic1.jpg", Utility.GetElement(driver, modalPage.picture).getText());
    }

}
