package pageObjects;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Landing_Page_Objects extends BasePage {
    public Landing_Page_Objects(WebDriver driver) {
        super(driver);
    }

    public Landing_Page_Objects open_landing_page(String liveURL){
        getUrl(liveURL);
        return this;
    }
    public Landing_Page_Objects assert_landing_page_is_open() {
        Assert.assertTrue(getCurrentUrl().equals("https://www.tabii.com/"), "Landing page didn't open.");
        getInfoMessage("Landing Page opened.");
        return this;
    }
}
