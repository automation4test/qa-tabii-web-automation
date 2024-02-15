package pageTests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static utilities.extentReport.ExtentTestManager.startTest;

public class Login_Page_Tests extends BaseTest {
    @BeforeMethod
    public void beforeMethod(Method method) {
        startTest(method.getName(), "Tabii Login Page Tests");
        login_page_objects.open_login_page(properties.getProperty("liveURL"));
    }

    @Test(description = "Login Sayfasını Başarılı Görüntüleme Testi")
    public void TC001_Open_Login_Page() {
        login_page_objects
                .assert_login_page_is_open();
    }//

}
