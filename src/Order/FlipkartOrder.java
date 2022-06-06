package Order;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class FlipkartOrder {

	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver",".\\Library\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		//Go to Home Page of Flipkart
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
		//Implicit Wait for loading element on each web page
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Searching Android phone 
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys("Android phone");	
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		//Click on first item from the list
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='_1AtVbE col-12-12'])[3]"))).click();
		
		
		//To handle multiple tabs
		Set<String> tab_handles=driver.getWindowHandles();
		Iterator<String> itr = tab_handles.iterator();
		itr.next();

		String next_window = itr.next();
		driver.switchTo().window(next_window);
		
		//To get Price of Mobile
		String price=driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']")).getText();
		System.out.println("Price of Mobile :"+price);
		
		//To add product into cart	
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
		
		//To increase Quantity by 1
		driver.findElement(By.xpath("//button[text()='+']")).click();

		//To get Increased price after adding quantity
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='_3LxTgx']//following-sibling::span//span")));
		String total_price=driver.findElement(By.xpath("//div[@class='_3LxTgx']//following-sibling::span//span")).getText();
		System.out.println("Total price of Mobiles :"+total_price);
		
		//close all tabs
		driver.quit();
		
	
	}

}
