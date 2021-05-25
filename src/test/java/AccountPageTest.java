import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AccountPageTest {
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
    public void addDeleteAccount() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Счета")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("number")).sendKeys("11111111111");
        driver.findElement(By.id("balance")).clear();
        driver.findElement(By.id("balance")).sendKeys("42341321");
        driver.findElement(By.id("status")).sendKeys("активный");
        driver.findElement(By.id("add")).click(); // некорректный данные
        Select client_slct = new Select(driver.findElement(By.id("client")));
        client_slct.selectByVisibleText("Попов Алексей Дмитриевич");
        Select department_slct = new Select(driver.findElement(By.id("department")));
        department_slct.selectByVisibleText("Новое");
        driver.findElement(By.id("date")).sendKeys("11/24/2011");
        Select type_slct = new Select(driver.findElement(By.id("type")));
        type_slct.selectByVisibleText("Базовый");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.linkText("11111111111")).click();
        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

    @Test
    public void updateAccount() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Счета")).click();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("number")).sendKeys("11111111111");
        driver.findElement(By.id("balance")).clear();
        driver.findElement(By.id("balance")).sendKeys("2121.0");
        driver.findElement(By.id("status")).sendKeys("активный");
        driver.findElement(By.id("add")).click(); // некорректный данные
        Select client_slct = new Select(driver.findElement(By.id("client")));
        client_slct.selectByVisibleText("Попов Алексей Дмитриевич");
        Select department_slct = new Select(driver.findElement(By.id("department")));
        department_slct.selectByVisibleText("Новое");
        driver.findElement(By.id("date")).sendKeys("11/24/2011");
        Select type_slct = new Select(driver.findElement(By.id("type")));
        type_slct.selectByVisibleText("Базовый");
        driver.findElement(By.id("add")).click();
        driver.findElement(By.linkText("11111111111")).click();
        Assert.assertEquals(driver.findElement(By.id("number")).getText(), "Номер счета: 11111111111");
        Assert.assertEquals(driver.findElement(By.id("balance")).getText(), "Баланс счета: 2121.0");
        Assert.assertEquals(driver.findElement(By.id("status")).getText(), "Статус счета: активный");
        Assert.assertEquals(driver.findElement(By.id("client")).getText(), "Попов Алексей Дмитриевич");
        Assert.assertEquals(driver.findElement(By.id("department")).getText(), "Новое");
        Assert.assertEquals(driver.findElement(By.id("type")).getText(), "Базовый");
        Assert.assertEquals(driver.findElement(By.id("date")).getText(), "Дата создания: 2011-11-24");
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("balance")).clear();
        driver.findElement(By.id("balance")).sendKeys("12345.0");
        department_slct = new Select(driver.findElement((By.id("department"))));
        department_slct.selectByVisibleText("Центральное");
        driver.findElement(By.id("update")).click();
        Assert.assertEquals(driver.findElement(By.id("number")).getText(), "Номер счета: 11111111111");
        Assert.assertEquals(driver.findElement(By.id("balance")).getText(), "Баланс счета: 12345.0");
        Assert.assertEquals(driver.findElement(By.id("status")).getText(), "Статус счета: активный");
        Assert.assertEquals(driver.findElement(By.id("client")).getText(), "Попов Алексей Дмитриевич");
        Assert.assertEquals(driver.findElement(By.id("department")).getText(), "Центральное");
        Assert.assertEquals(driver.findElement(By.id("type")).getText(), "Базовый");
        Assert.assertEquals(driver.findElement(By.id("date")).getText(), "Дата создания: 2011-11-24");
        driver.findElement(By.id("delete")).click();
    }
}