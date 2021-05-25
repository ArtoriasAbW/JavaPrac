import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ClientPageTest {
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
    public void addDeleteClient() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Клиенты")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("name")).sendKeys("Тест");
        driver.findElement(By.id("address")).sendKeys("г.Москва ул.Победы д.9");
        driver.findElement(By.id("phone")).sendKeys("+7 900 312 32 12");
        driver.findElement(By.id("email")).sendKeys("email@ya.ru");
        driver.findElement(By.id("add")).click(); // некорректный данные
        driver.findElement(By.id("type")).sendKeys("Тестовое");
        driver.findElement(By.id("date")).sendKeys("11/24/2011");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.linkText("Тест")).click();
        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

    @Test
    public void updateClient() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Клиенты")).click();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("name")).sendKeys("Тест");
        driver.findElement(By.id("address")).sendKeys("г.Москва ул.Победы д.9");
        driver.findElement(By.id("phone")).sendKeys("+7 900 312 32 12");
        driver.findElement(By.id("email")).sendKeys("email@ya.ru");
        driver.findElement(By.id("add")).click(); // некорректный данные
        driver.findElement(By.id("type")).sendKeys("Тестовое");
        driver.findElement(By.id("date")).sendKeys("11/24/2011");
        driver.findElement(By.id("add")).click();
        driver.findElement(By.linkText("Тест")).click();
        Assert.assertEquals(driver.findElement(By.id("name")).getText(), "Имя клиента: Тест");
        Assert.assertEquals(driver.findElement(By.id("address")).getText(), "Адрес клиента: г.Москва ул.Победы д.9");
        Assert.assertEquals(driver.findElement(By.id("email")).getText(), "Email клиента: email@ya.ru");
        Assert.assertEquals(driver.findElement(By.id("phone")).getText(), "Телефон клиента: +7 900 312 32 12");
        Assert.assertEquals(driver.findElement(By.id("type")).getText(), "Тип клиента: Тестовое");
        Assert.assertEquals(driver.findElement(By.id("date")).getText(), "Дата регистрации клиента: 2011-11-24");
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("email@gmail.com");
        driver.findElement(By.id("type")).clear();
        driver.findElement(By.id("type")).sendKeys("частное лицо");
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("delete")).click();

    }
}
