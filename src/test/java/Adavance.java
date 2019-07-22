import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class Adavance {
	WebDriver driver;
	@BeforeTest
	public void zebra() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\jagdishbhatt\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://10.0.1.86/tatoc");
		WebElement link=driver.findElement(By.xpath("//a[@href=\"/tatoc/advanced\"]"));
		link.click();
	}
	@Test
	public void f() throws InterruptedException {
		WebElement e=driver.findElement(By.xpath("//div[@class=\"menutop m2\"]"));
		Actions a=new Actions(driver);
		a.moveToElement(e).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),\"Go Next\")]")).click();
		Thread.sleep(7000);
		driver.close();
	}
}
