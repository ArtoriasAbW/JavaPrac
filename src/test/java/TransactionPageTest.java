import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TransactionPageTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    @Test
    public void addDeleteTransaction() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Транзакции")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("id")).sendKeys("11111111111");
        driver.findElement(By.id("amount")).sendKeys("4221");
        Select sender_slct = new Select(driver.findElement(By.id("sender")));
        sender_slct.selectByVisibleText("756363456346");
        driver.findElement(By.id("add")).click(); // некорректный данные
        Select receiver_slct = new Select(driver.findElement(By.id("receiver")));
        receiver_slct.selectByVisibleText("143242323432");
        driver.findElement(By.id("date")).sendKeys("11/24/2011");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.linkText("11111111111")).click();
        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

    @Test
    public void updateTransaction() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Транзакции")).click();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("id")).sendKeys("11111111111");
        driver.findElement(By.id("amount")).clear();
        driver.findElement(By.id("amount")).sendKeys("4221");
        Select sender_slct = new Select(driver.findElement(By.id("sender")));
        sender_slct.selectByVisibleText("756363456346");
        driver.findElement(By.id("add")).click(); // некорректный данные
        Select receiver_slct = new Select(driver.findElement(By.id("receiver")));
        receiver_slct.selectByVisibleText("143242323432");
        driver.findElement(By.id("date")).sendKeys("11/24/2011");
        driver.findElement(By.id("add")).click();
        driver.findElement(By.linkText("11111111111")).click();
        Assert.assertEquals(driver.findElement(By.id("id")).getText(), "Идентификатор транзакции: 11111111111");
        Assert.assertEquals(driver.findElement(By.id("amount")).getText(), "Сумма: 4221.0");
        Assert.assertEquals(driver.findElement(By.id("sender")).getText(), "756363456346");
        Assert.assertEquals(driver.findElement(By.id("receiver")).getText(), "143242323432");
        Assert.assertEquals(driver.findElement(By.id("date")).getText(), "Дата: 2011-11-24 12:00:00");
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("amount")).clear();
        driver.findElement(By.id("amount")).sendKeys("31213");
        sender_slct = new Select(driver.findElement(By.id("sender")));
        sender_slct.selectByVisibleText("754632342544");
        driver.findElement(By.id("update")).click();
        Assert.assertEquals(driver.findElement(By.id("id")).getText(), "Идентификатор транзакции: 11111111111");
        Assert.assertEquals(driver.findElement(By.id("amount")).getText(), "Сумма: 31213.0");
        Assert.assertEquals(driver.findElement(By.id("sender")).getText(), "754632342544");
        Assert.assertEquals(driver.findElement(By.id("receiver")).getText(), "143242323432");
        Assert.assertEquals(driver.findElement(By.id("date")).getText(), "Дата: 2011-11-24 12:00:00");
        driver.findElement(By.id("delete")).click();

    }
}