package pageObjects;

import base.BasePage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.logs.Log;

public class Login_Page_Objects extends BasePage {
    public Login_Page_Objects(WebDriver driver) {
        super(driver);
    }
    Faker faker = new Faker();
    String email, password;

    By giris_yap_btn = By.xpath("//*[@id='Main']/div/div/header/div/nav/div/div[2]/a/button");
    By hosgeldin_txt_title = By.xpath("//*[@id=\"Main\"]/div/div/main/div/div/h1");

    public Login_Page_Objects open_login_page(String liveURL){
        getUrl(liveURL);
        clickElement(giris_yap_btn);
        return this;
    }

    public Login_Page_Objects assert_login_page_is_open(){
        Assert.assertTrue(elementIsDisplayed(hosgeldin_txt_title),"Hoşgeldin başlığıu görüntülendmedi.");
        Log.info("Hoşgeldin Başlığı Başarılı görüntülendi");
        return this;
    }
}
