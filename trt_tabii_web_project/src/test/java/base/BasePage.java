package base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.extentReport.ExtentTestManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    protected JavascriptExecutor js;
    List<String> getElementTexts = new ArrayList<>();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clearTextField(By elementBy) {
        untilElementIsVisible(elementBy);
        HighlightElement(driver.findElement(elementBy));
        driver.findElement(elementBy).clear();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    public void fillInputTextBox(By elementBy, String text) {
        untilElementIsVisible(elementBy);
        HighlightElement(driver.findElement(elementBy));
        clearTextField(elementBy);
        waitForLoad(500);
        driver.findElement(elementBy).sendKeys(text);
    }

    protected void sendTextPressEnter(By elementBy, String text) {
        HighlightElement(driver.findElement(elementBy));
        driver.findElement(elementBy).sendKeys(text);
        driver.findElement(elementBy).sendKeys(Keys.ENTER);
    }

    protected void clickElement(By elementBy) {
        untilElementIsVisible(elementBy);
        HighlightElementClick(driver.findElement(elementBy));
        driver.findElement(elementBy).click();
    }

    protected void uploadFile(By elementBy, String path) {
        untilElementIsVisible(elementBy);
        HighlightElementClick(driver.findElement(elementBy));
        driver.findElement(elementBy).sendKeys(path);
    }

    protected String getInfoMessage(String message) {
        ExtentTestManager.getTest().log(Status.INFO, message);
        return message;
    }

    protected boolean elementIsPresent(By element) {
        waitForLoad(2000);
        return driver.findElements(element).size() > 0;
    }

    protected int elementSize(By element) {
        waitForLoad(2000);
        return driver.findElements(element).size();
    }

    protected WebElement findWebElement(By elementBy) {
        WebElement el = driver.findElement(elementBy);
        HighlightElement(el);
        return el;
    }

    protected int sizeOfList(By element) {
        return driver.findElements(element).size();
    }

    protected List<String> getTextList(By element) {
        getElementTexts.clear();
        List<WebElement> listElement = driver.findElements(element);
        for (int i = 0; i < listElement.size(); i++) {
            waitForLoad(100);
            getElementTexts.add(listElement.get(i).getText());
        }
        return getElementTexts;
    }

    protected List<String> getTextListLowerCase(By element) {
        getElementTexts.clear();
        List<WebElement> listElement = driver.findElements(element);
        for (int i = 0; i < listElement.size(); i++) {
            waitForLoad(100);
            getElementTexts.add(listElement.get(i).getText().toLowerCase());
        }
        return getElementTexts;
    }

    protected void clickElementIsPresent(By element) {
        if (elementIsPresent(element)) {
            clickElement(element);
        } else {
            waitForLoad(3000);
            clickElement(element);
        }
    }

    protected WebElement untilElementIsVisible(By elementBy) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
            HighlightElement(element);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    public void HighlightElement(WebElement el) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", el,
                " border: 3px solid red;");
    }

    protected void HighlightElementClick(WebElement el) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", el,
                " border: 3px solid blue;");
    }

    protected void waitForLoad(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchWindow(int index) {
        driver.switchTo().frame(index);
    }

    public void switchTab(int index) {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(index));
    }

    public boolean pageSourceContain(String message) {
        waitForLoad(2000);
        return driver.getPageSource().contains(message);
    }

    protected String getTextofElement(By elementBy) {
        waitForLoad(3000);
        untilElementIsVisible(elementBy);
        return driver.findElement(elementBy).getText();
    }

    protected boolean elementIsDisplayed(By elementBy) {
        return driver.findElement(elementBy).isDisplayed();
    }

    protected boolean elementIsEnabled(By elementBy) {
        return driver.findElement(elementBy).isEnabled();
    }

    protected boolean untilElementIsClickable(By elementBy) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(elementBy));
            HighlightElement(driver.findElement(elementBy));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected List findElementsOfList(By elementBy) {
        return driver.findElements(elementBy);
    }

    protected void scrollToElement(By elementBy) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(elementBy);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void scrollInView(By elementBy) {
        // Create an object of actions class and pass reference of WebDriver as a parameter to its constructor.
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(elementBy);
        // Call moveToElement() method of actions class to move mouse cursor to a WebElement A.
        actions.moveToElement(element);
        actions.clickAndHold();

        actions.moveToElement(element);
        actions.release().perform();
    }

    /**
     * WebElement element = driver.findElement(By.id("id_of_element"));
     * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
     * Thread.sleep(500);
     */
    protected void scrollDown() {
        waitForLoad(1000);
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,450)", "");
    }

    protected void scrollUp() {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,-450)", "");
    }

    protected void switchContent() {
        driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
    }
}
