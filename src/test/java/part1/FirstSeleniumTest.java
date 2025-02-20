package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        Reporter.log("Step 1: Launching Chrome browser", true);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Reporter.log("Step 2: Navigating to OrangeHRM login page", true);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                Reporter.log("Waiting for 3 seconds before closing the browser...", true);
                Thread.sleep(3000);
                Reporter.log("Closing browser", true);
                driver.quit();
            } catch (InterruptedException e) {
                Reporter.log("Error during teardown: " + e.getMessage(), true);
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testLoggingIntoApp() {
        // Wait for page to load and print URL
        Reporter.log("Current URL: " + driver.getCurrentUrl(), true);

        // Step 3: Enter username
        Reporter.log("Step 3: Entering username 'Admin'", true);
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.clear();
        username.sendKeys("Admin");

        // Step 4: Enter password
        Reporter.log("Step 4: Entering password", true);
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.clear();
        password.sendKeys("admin123");

        // Step 5: Click login button
        Reporter.log("Step 5: Clicking login button", true);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));
        button.click();

        // Step 6: Wait for dashboard page
        Reporter.log("Step 6: Waiting for dashboard to load", true);
        wait.until(ExpectedConditions.urlContains("/dashboard"));

        // Print final URL to confirm navigation
        Reporter.log("Login successful!");
    }
}