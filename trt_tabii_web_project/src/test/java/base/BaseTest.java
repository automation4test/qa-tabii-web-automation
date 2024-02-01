package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pageObjects.Landing_Page_Objects;
import pageObjects.Login_Page_Objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;

    public Landing_Page_Objects landing_page_objects;
    public Login_Page_Objects login_page_objects;
    public Properties properties;
    FileInputStream fileInputStream;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        if (false) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
        } else {
            WebDriverManager.chromedriver().setup();
           /** WebDriverManager.chromedriver().clearDriverCache().setup();
            WebDriverManager.chromedriver().clearResolutionCache().setup();**/
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() throws IOException {
        getReadPropFile();

        landing_page_objects = new Landing_Page_Objects(driver);
        login_page_objects = new Login_Page_Objects(driver);
    }

    public void getReadPropFile() throws IOException {
        properties = new Properties();
        fileInputStream = new FileInputStream("src/test/resources/config.properties");
        properties.load(fileInputStream);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.quit();
    }

}
