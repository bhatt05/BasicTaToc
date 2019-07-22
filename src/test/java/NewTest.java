import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class NewTest {
	WebDriver driver;
	@BeforeTest
	public void a() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\jagdishbhatt\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://10.0.1.86/tatoc");
		WebElement link=driver.findElement(By.xpath("//a[@href=\"/tatoc/basic\"]"));
		link.click();
	}

	@Test
	public void b() {
		WebElement box=driver.findElement(By.cssSelector("div[class=\"greenbox\"]"));
		box.click();
	}
	@Test
	public void c() {
		driver.switchTo().frame("main");
		WebElement box1=driver.findElement(By.xpath("//div[@id=\"answer\"]"));
		String color=box1.getAttribute("class");
		String color1;
		while(true)
		{
			driver.switchTo().frame("child");
			WebElement box2=driver.findElement(By.xpath("//div[@id=\"answer\"]"));
			color1=box2.getAttribute("class");
			if(color.equals(color1)) {
				break;
			}
			else {
				driver.switchTo().parentFrame();
				driver.findElement(By.xpath("//a[contains(text(),\"Repaint\")]")).click();
			}
		}
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//a[contains(text(),\"Proceed\")]")).click();
	}
	@Test
	public void d() throws InterruptedException {
		WebElement dest=driver.findElement(By.xpath("//div[@id=\"dropbox\"]"));
		WebElement source=driver.findElement(By.xpath("//div[@id=\"dragbox\"]"));
		Actions act=new Actions(driver);
		act.dragAndDrop(source, dest).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),\"Proceed\")]")).click();
	}
	@Test
	public void e() {
		driver.findElement(By.xpath("//a[contains(text(),\"Launch\")]")).click();
		String main=driver.getWindowHandle();
		Set <String>child=driver.getWindowHandles();
		Iterator<String> i=child.iterator();
		while(i.hasNext()) {
			String child1=i.next();
			if(!main.equalsIgnoreCase(child1)) {
				driver.switchTo().window(child1);
				driver.findElement(By.xpath("//input[@id=\"name\"]")).sendKeys("Jagdish");
				driver.findElement(By.xpath("//input[@id=\"submit\"]")).click();
			}
		}
		driver.switchTo().window(main);
		driver.findElement(By.xpath("//a[contains(text(),\"Proceed\")]")).click();
	}
	@Test
	public void f() {
		driver.findElement(By.xpath("//a[contains(text(),\"Generate\")]")).click();
		String token=driver.findElement(By.xpath("//span[@id=\"token\"]")).getText();
		String str[]=token.split(" ");
		String value=str[1];
		Cookie c=new Cookie("Token",value);
		driver.manage().addCookie(c);
		driver.findElement(By.xpath("//a[contains(text(),\"Proceed\")]")).click();
	} 
}
