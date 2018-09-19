//package base;
//
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.LogStatus;
//import org.apache.commons.exec.OS;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.annotations.*;
//import reporting.ExtentManager;
//import reporting.ExtentTestManager;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class CommonAPI {
//
//    //ExtentReport
//    public static ExtentReports extent;
//    @BeforeSuite
//    public void extentSetup(ITestContext context) {
//        ExtentManager.setOutputDirectory(context);
//        extent = ExtentManager.getInstance();
//    }
//    @BeforeMethod
//    public void startExtent(Method method) {
//        String className = method.getDeclaringClass().getSimpleName();
//        String methodName = method.getName().toLowerCase();
//        ExtentTestManager.startTest(method.getName());
//        ExtentTestManager.getTest().assignCategory(className);
//    }
//    protected String getStackTrace(Throwable t) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        t.printStackTrace(pw);
//        return sw.toString();
//    }
//
//    @AfterSuite
//    public void generateReport() {
//        extent.close();
//    }
//    private Date getTime(long millis) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(millis);
//        return calendar.getTime();
//    }
//
//    public WebDriver driver = null;
//    public String browserstack_username= "your user name";
//    public String browserstack_accesskey = "your access key";
//    public String saucelabs_username = "";
//    public String saucelabs_accesskey = "";
//
//    @Parameters({"os","browserName","url"})
//    @BeforeMethod
//
//    public WebDriver getLocalDriver(@Optional("mac") String OS, String browserName){
//        if(browserName.equalsIgnoreCase("chrome")){
//            if(OS.equalsIgnoreCase("OS X")){
//                System.setProperty("webdriver.chrome.driver", "../Generic/browser-driver/chromedriver");
//            }else if(OS.equalsIgnoreCase("Windows")){
//                System.setProperty("webdriver.chrome.driver", "../Generic/browser-driver/chromedriver.exe");
//            }
//            driver = new ChromeDriver();
//        } else if(browserName.equalsIgnoreCase("chrome-options")){
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--disable-notifications");
//            if(OS.equalsIgnoreCase("OS X")){
//                System.setProperty("webdriver.chrome.driver", "../Generic/browser-driver/chromedriver");
//            }else if(OS.equalsIgnoreCase("Windows")){
//                System.setProperty("webdriver.chrome.driver", "../Generic/browser-driver/chromedriver.exe");
//            }
//            driver = new ChromeDriver(options);
//        }
//
//        else if(browserName.equalsIgnoreCase("firefox")){
//            if(OS.equalsIgnoreCase("OS X")){
//                System.setProperty("webdriver.gecko.driver", "../Generic/browser-driver/geckodriver");
//            }else if(OS.equalsIgnoreCase("Windows")) {
//                System.setProperty("webdriver.gecko.driver", "../Generic/browser-driver/geckodriver.exe");
//            }
//            driver = new FirefoxDriver();
//
//        } else if(browserName.equalsIgnoreCase("ie")) {
//            System.setProperty("webdriver.ie.driver", "../Generic/browser-driver/IEDriverServer.exe");
//            driver = new InternetExplorerDriver();
//        }
//        return driver;
//
//    }
//
//
//    public WebDriver getCloudDriver(String envName,String envUsername, String envAccessKey,String os, String os_version,String browserName,
//                                    String browserVersion)throws IOException {
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability("browser",browserName);
//        cap.setCapability("browser_version",browserVersion);
//        cap.setCapability("os", os);
//        cap.setCapability("os_version", os_version);
//        if(envName.equalsIgnoreCase("Saucelabs")){
//            //resolution for Saucelabs
//            driver = new RemoteWebDriver(new URL("http://"+envUsername+":"+envAccessKey+
//                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
//        }else if(envName.equalsIgnoreCase("Browserstack")) {
//            cap.setCapability("resolution", "1024x768");
//            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
//                    "@hub-cloud.browserstack.com/wd/hub"), cap);
//        }
//        return driver;
//    }
//
//    @AfterMethod
//    public void cleanUp(){
//        driver.close();
//    }
//
//    public void clickOnCss(String locator){
//        driver.findElement(By.cssSelector(locator)).click();
//    }
//    public void clickOnElement(String locator){
//        try {
//            driver.findElement(By.cssSelector(locator)).click();
//        }catch(Exception ex1){
//            try {
//                driver.findElement(By.xpath(locator)).click();
//            }catch(Exception ex2){
//                driver.findElement(By.id(locator)).click();
//            }
//        }
//    }
//    public void typeOnCss(String locator, String value){
//        driver.findElement(By.cssSelector(locator)).sendKeys(value);
//    }
//    public void typeOnInputField(String locator, String value){
//        try {
//            driver.findElement(By.cssSelector(locator)).sendKeys(value);
//        }catch (Exception ex){
//            driver.findElement(By.id(locator)).sendKeys(value);
//        }
//
//    }
//    public void clickByXpath(String locator) {
//        driver.findElement(By.xpath(locator)).click();
//    }
//
//    public void typeByCss(String locator, String value) {
//        driver.findElement(By.cssSelector(locator)).sendKeys(value);
//    }
//    public void typeByCssNEnter(String locator, String value) {
//        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
//    }
//
//    public void typeByXpath(String locator, String value) {
//        driver.findElement(By.xpath(locator)).sendKeys(value);
//    }
//
//    public void takeEnterKeys(String locator) {
//        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
//    }
//
//    public void clearInputField(String locator){
//        driver.findElement(By.cssSelector(locator)).clear();
//    }
//    public List<WebElement> getListOfWebElementsById(String locator) {
//        List<WebElement> list = new ArrayList<WebElement>();
//        list = driver.findElements(By.id(locator));
//        return list;
//    }
//    public List<String> getTextFromWebElements(String locator){
//        List<WebElement> element = new ArrayList<WebElement>();
//        List<String> text = new ArrayList<String>();
//        element = driver.findElements(By.cssSelector(locator));
//        for(WebElement web:element){
//            String st = web.getText();
//            text.add(st);
//        }
//
//        return text;
//    }
//    public List<WebElement> getListOfWebElementsByCss(String locator) {
//        List<WebElement> list = new ArrayList<WebElement>();
//        list = driver.findElements(By.cssSelector(locator));
//        return list;
//    }
//    public List<WebElement> getListOfWebElementsByXpath(String locator) {
//        List<WebElement> list = new ArrayList<WebElement>();
//        list = driver.findElements(By.xpath(locator));
//        return list;
//    }
//    public String  getCurrentPageUrl(){
//        String url = driver.getCurrentUrl();
//        return url;
//    }
//    public void navigateBack(){
//        driver.navigate().back();
//    }
//    public void navigateForward(){
//        driver.navigate().forward();
//    }
//    public String getTextByCss(String locator){
//        String st = driver.findElement(By.cssSelector(locator)).getText();
//        return st;
//    }
//    public String getTextByXpath(String locator){
//        String st = driver.findElement(By.xpath(locator)).getText();
//        return st;
//    }
//    public String getTextById(String locator){
//        return driver.findElement(By.id(locator)).getText();
//    }
//    public String getTextByName(String locator){
//        String st = driver.findElement(By.name(locator)).getText();
//        return st;
//    }
//
//    public List<String> getListOfString(List<WebElement> list) {
//        List<String> items = new ArrayList<String>();
//        for (WebElement element : list) {
//            items.add(element.getText());
//        }
//        return items;
//    }
//
//    public void selectOptionByVisibleText(WebElement element, String value) {
//        Select select = new Select(element);
//        select.selectByVisibleText(value);
//    }
//    public static void sleepFor(int sec)throws InterruptedException{
//        Thread.sleep(sec * 1000);
//    }
//    public void mouseHoverByCSS(String locator){
//        try {
//            WebElement element = driver.findElement(By.cssSelector(locator));
//            Actions action = new Actions(driver);
//            Actions hover = action.moveToElement(element);
//        }catch(Exception ex){
//            System.out.println("First attempt has been done, This is second try");
//            WebElement element = driver.findElement(By.cssSelector(locator));
//            Actions action = new Actions(driver);
//            action.moveToElement(element).perform();
//
//        }
//
//    }
//    public void mouseHoverByXpath(String locator){
//        try {
//            WebElement element = driver.findElement(By.xpath(locator));
//            Actions action = new Actions(driver);
//            Actions hover = action.moveToElement(element);
//        }catch(Exception ex){
//            System.out.println("First attempt has been done, This is second try");
//            WebElement element = driver.findElement(By.cssSelector(locator));
//            Actions action = new Actions(driver);
//            action.moveToElement(element).perform();
//
//        }
//
//    }
//    //handling Alert
//    public void okAlert(){
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
//    }
//    public void cancelAlert(){
//        Alert alert = driver.switchTo().alert();
//        alert.dismiss();
//    }
//
//    //iFrame Handle
//    public void iframeHandle(WebElement element){
//        driver.switchTo().frame(element);
//    }
//
//    public void goBackToHomeWindow(){
//        driver.switchTo().defaultContent();
//    }
//
//    //get Links
//    public void getLinks(String locator){
//        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
//    }
//
//    public void clearInput(String locator){
//        driver.findElement(By.cssSelector(locator)).clear();
//    }
//    public void keysInput(String locator){
//        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
//    }
//    public static String convertToString(String st){
//        String splitString ;
//        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
//        return splitString;
//    }
//    //Handling New Tabs
//    public static WebDriver handleNewTab(WebDriver driver1){
//        String oldTab = driver1.getWindowHandle();
//        List<String> newTabs = new ArrayList<String>(driver1.getWindowHandles());
//        newTabs.remove(oldTab);
//        driver1.switchTo().window(newTabs.get(0));
//        return driver1;
//    }
//    public static boolean isPopUpWindowDisplayed(WebDriver driver1, String locator){
//        boolean value = driver1.findElement(By.cssSelector(locator)).isDisplayed();
//        return value;
//    }
//
//    public void typeOnInputBox(String locator, String value) {
//        try{
//            driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
//        }catch (Exception ex1) {
//            System.out.println("ID locator didn't work");
//        }
//        try{
//            driver.findElement(By.name(locator)).sendKeys(value, Keys.ENTER);
//        }catch (Exception ex2){
//            System.out.println("Name locator didn't work");
//        }
//        try{
//            driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
//        }catch (Exception ex3){
//            System.out.println("CSS locator didn't work");
//        }
//    }
//
//}

package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    public WebDriver driver;

    /**
     * Using parameters to make it more customizable, User is able to
     * provide desired information about @params[platform, browser, url]
     * in their TestNG.xml file rather than hard coding it.
     * @param platform
     * @param browser
     * @param url
     */

    @BeforeClass
    @Parameters({"platform", "browser", "url"})
    public void setUp(String platform, String browser, String url) {
        localDriver(platform, browser);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get(url);
    }

    public void localDriver(String platform, String browser) {
        if (platform.contains("Mac")) {
            if (browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver");
                driver = new FirefoxDriver();
            }
        } else if (platform.contains("Win")) {
            if (browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }
    }

    /**
     * AfterMethod captures Screenshots with the help of 'captureScreenshot' method
     * Which get saved under screenshots directory under Users individual modules
     * and to make the names stand out from each other, Current Date is added
     * so User can distinguish the screenshots.
     * @param result
     */

    @AfterMethod
    public void captureScreenshotsIfFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to capture screenshot and provide current date
    private void captureScreenshot(String name) {
        DateFormat dateFormat = new SimpleDateFormat("(HH.mm.yyyy-HH;mma)");
        Date date = new Date();

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = null;

        if (System.getProperty("os.name").contains("Mac")) {
            screenshot = new File(System.getProperty("user.dir") +
                    "/screenshots/" + name + " " + dateFormat.format(date) + ".png");
        } else if (System.getProperty("os.name").contains("Win")) {
            screenshot = new File(System.getProperty("user.dir") +
                    "\\screenshots\\" + name + " " + dateFormat.format(date) + ".png");
        }
        try {
            FileUtils.copyFile(file, screenshot);
        } catch (IOException e) {

        }
    }
}