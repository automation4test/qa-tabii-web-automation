package pageTests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.listeners.Retry;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Landing_Page_Tests extends BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method) {
        startTest(method.getName(), "Tabii Landing Page Tests");
        landing_page_objects.open_landing_page(properties.getProperty("liveURL"));
    }

    @Test(retryAnalyzer = utilities.listeners.Retry.class)
    public void TC001_Open_Landing_Page() {
        landing_page_objects
                .assert_landing_page_is_open();
    }

}
