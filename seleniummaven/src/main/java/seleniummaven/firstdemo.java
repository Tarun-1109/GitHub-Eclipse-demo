package seleniummaven;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.plaf.basic.BasicArrowButton;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v102.systeminfo.SystemInfo;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Link;

import io.github.bonigarcia.wdm.WebDriverManager;

public class firstdemo
{
	String URL = "https://opensource-demo.orangehrmlive.com/";
	String URL_2 = "https://www.youtube.com/";
    WebDriver driver;
    
    
	@BeforeTest
	public void BT() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	driver.get(URL);
	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	

	/* Logout from OrangeHRM */
	@Test (enabled = false,priority = 1)
	public void TestCase1() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='oxd-userdropdown']")).click();
		Thread.sleep(3000);
		WebElement logout= driver.findElement(By.xpath("//*[@class='oxd-dropdown-menu']/li[4]/a"));
		
		if(logout.isDisplayed())
		{
			logout.click();
		}
		System.out.println("Logout is Done");
	}
	
  /* Login */ 
	@Test (enabled = true ,priority = 0)
	public void TestCase2() throws InterruptedException
	{
		Thread.sleep(2000);
 	    WebElement username =driver.findElement(By.xpath("//*[@placeholder='Username']"));
	    
 	    if(username.isEnabled())
 	    {
 	    	username.sendKeys("Admin");
 	    	Thread.sleep(3000);
 	    }
 	    
 	    WebElement password = driver.findElement(By.xpath("//*[@type='password']"));
 	    if(password.isDisplayed())
 	    {
 	    	password.sendKeys("admin123");
 			WebElement click = driver.findElement(By.xpath("//*[@type='submit']"));
 			click.click();
 	    }
// 	   Actions act = new Actions(driver);
// 	  WebElement mouse_over = driver.findElement(By.linkText("Add Employee"));
//	    act.moveToElement(mouse_over).build().perform();
//	    Thread.sleep(3000);
//	   act.contextClick(mouse_over).build().perform(); //Right click
	   
	}

	
	
	/* click on Maintance and follow few steps*/
	@Test (enabled = false, priority = 2)
	public void TestCase3() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='oxd-main-menu']/li[10]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@type='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//*[@class='orangehrm-admin-access-button-container']/button[2]")).click();
	}
	
	
	
/* The below code is to click on admin, apply select and delete a selected data in the table by getting the size of it */
	@Test(enabled = false, priority = 3)
	public void TestCase4() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='oxd-main-menu']/li[1]/a")).click();
		Thread.sleep(4000);
		
		
		//Actions:
		//1.Keyboard Actions 
		//2.Mouse over Actions
		//3.Scroll up or down
		Actions act = new Actions(driver);
	    WebElement ele=driver.findElement(By.xpath("//*[@class='oxd-select-text-input']"));
	    act.click(ele).build().perform();
	    act.sendKeys(ele,Keys.ARROW_DOWN).click().build().perform();
	    act.sendKeys(ele,Keys.ARROW_DOWN).click().build().perform();
	    act.sendKeys(ele,Keys.ENTER).click().build().perform();
	    
//	    WebElement mouse_over = driver.findElement(By.linkText("Add Employee"));
//	    act.moveToElement(mouse_over).click().build().perform();
	    
	    WebElement search_btn = driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"));
	    Thread.sleep(2000);
	    act.click(search_btn).build().perform();
	    Thread.sleep(2000);
	  
	    //List:
	   List<WebElement> list = driver.findElements(By.xpath("//*[@class='oxd-table-card']"));
	   int count = list.size();
	   System.out.println("The size of the list :"+count);
	   
	   for(int i=0; i<=count;i++)
	   {
		   if(i==5)
		   {
			    Thread.sleep(3000);
			    WebElement data =  (WebElement) driver.findElements(By.xpath("//*[@class='oxd-icon bi-check oxd-checkbox-input-icon']")).get(i);
			    data.click();
			    Thread.sleep(3000);
			    
			    System.out.println(" First column data:"+driver.findElement(By.xpath("//*[@class='oxd-table-card'][6]/div[2]/div']")).getText());
			    
//			  WebElement name = driver.findElements(By.xpath("//*[@class='oxd-table-row oxd-table-row--with-border']")).get(i);
//			 String tr = name.getText();
//			 System.out.print(tr);
			  //name.click();
			   Thread.sleep(3000);
			   driver.findElements(By.xpath("//*[@class='oxd-icon bi-trash']")).get(i).click();
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();
			    Thread.sleep(2000);
		   }
	   }
	      
	
	}
	
	
	
	
	/*  This below code is to get all the links in the page */
	@Test (enabled = false, priority = 4)
	public void TestLinks()
	{
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total number of links: "+links.size()); 
		
		for(int i=0;i<=links.size();i++)
		{
			System.out.println(links.get(i).getText()); 
			System.out.println(links.get(i).getAttribute("href"));
		}
	}
	
	
	
	/* To check which links are working in that page */
	@Test (enabled = false, priority = 5)
	public void Test_Working_links()
	{
		List<WebElement> wlinks = driver.findElements(By.tagName("a"));
		String[] LS = new String [wlinks.size()];
		int i=0;
		
		for(WebElement  e : wlinks)
		{
			System.out.println(e.getText());
			LS[i]=e.getText();
			i++;
		}
		
	}
	
	/* Window Handle */
	@Test (enabled = true, priority = 6)
	public void windowHandling() throws InterruptedException
	{
		driver.getWindowHandle();
	
		
			
		// Opens a new tab and switches to new tab
		driver.switchTo().newWindow(WindowType.WINDOW);
		
		//Navigates to new tab and opens the new link
		driver.navigate().to(URL_2);
		
		Thread.sleep(3000);
		driver.quit();
			   
	}

		@AfterTest(enabled = false)
		public void AT() throws InterruptedException
		{
			Thread.sleep(4000);
		    driver.quit();
		}
}


