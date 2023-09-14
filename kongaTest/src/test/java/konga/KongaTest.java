package konga;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaTest {

    WebDriver driver;

    @BeforeTest
    public void launchSite() throws InterruptedException {
        /* There is no need to create resources folder
         and save a chromedriver version inside because
         we already have a webdrivermanager that can get any
         browser instance for us.
         Researched from the internet as best practices*/
        WebDriverManager.chromedriver().setup();
        // create an instance of a chromedriver to run the script
        driver = new ChromeDriver();
        // navigate to the website
        driver.get("https://www.konga.com");
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @Test(priority = 0)
    public void login() throws InterruptedException {
        //click the login/signup link
        driver.findElement(By.xpath("//div[1]/div/div/div[4]/a")).click();
        Thread.sleep(5000);
        //insert the username
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("abeniolu@gmail.com");
        //insert the password
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ebgg2012");
        Thread.sleep(5000);
        // click login button
        driver.findElement(By.xpath("//div[3]/button[@type='submit']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void clickComputerAndAccessories() throws InterruptedException {
        //click computer and accessories
        driver.findElement(By.xpath("//div/a[@href='/category/accessories-computing-5227']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void clickLaptops() throws InterruptedException {
        //click laptops
        driver.findElement(By.xpath("//li/a[@href='/category/accessories-computing-5227?menu=Computers and Accessories > Laptops']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void clickAppleMacBooks() throws InterruptedException {
        //click Apple and MacBooks
        driver.findElement(By.xpath("//li/a[@href='/category/accessories-computing-5227?menu=Computers and Accessories > Laptops > Apple MacBooks']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 4)
    public void clickAddToCart() throws InterruptedException {
        //click add to cart button
        driver.findElement(By.xpath("//li[2]/div/div/div[2]/form[@action='/cart/overview']/div[3]")).click();
        Thread.sleep(5000);
        //click the cart button to see the added item
        driver.findElement(By.xpath("//div[@id='nav-bar-fix']/div/div/div/a[@href='/cart/overview']")).click();
        Thread.sleep(5000);
        //click the checkout button to proceed to payment page
        driver.findElement(By.xpath("//aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 5)
    public void proceedToPayment() throws InterruptedException {
        /*An address was selected automatically,
        so I proceeded to click on the card payment*/
        driver.findElement(By.xpath("//input[@data-payment-method='kpaygateway']")).click();
        Thread.sleep(5000);
        //click the pay now button
        driver.findElement(By.xpath("//button[@name='placeOrder']")).click();
        Thread.sleep(5000);
    }

    @Test(priority = 6)
    public void insertPaymentDetails() throws InterruptedException {
        //switched to payment modal frame
        driver.switchTo().frame("kpg-frame-component");
        //clicked the card option for the payment
        driver.findElement(By.xpath("//button[@class='dashboard-card__button Card']")).click();
        Thread.sleep(5000);
        //inserted an invalid card number
        driver.findElement(By.xpath("//input[@id='card-number']")).sendKeys("4632777488890001");
        Thread.sleep(5000);
         //inserted an invalid expiry date
        driver.findElement(By.xpath("//input[@id='expiry']")).sendKeys("07/28");
        Thread.sleep(5000);
    }

    @Test(priority = 7)
    public void printError() throws InterruptedException {
        //identified the element that displays the error message
        WebElement errorMessage = driver.findElement(By.xpath("//label[@id='card-number_unhappy']"));
        //printed the error to console by getting the text attribute
        System.out.println("The Error Message is: "+errorMessage.getText());
        Thread.sleep(5000);
    }

    @Test(priority = 8)
    public void closePaymentDetailsFrame() {
        //closed the payment modal frame
        driver.switchTo().defaultContent();
    }

    @AfterTest
    public void tearDown() {
        //shutdown the browser
        driver.quit();
    }
}
