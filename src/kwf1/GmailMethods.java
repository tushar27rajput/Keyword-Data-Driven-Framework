package kwf1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class GmailMethods 
{
	public WebDriver driver=null;
	
	
	public String launch(String l,String d,String c) throws Exception
	{
		if(l.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "E://swd//chromedriver.exe");
			
			driver=new ChromeDriver();
			
		}
		else if(l.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "E://swd//geckodriver.exe");
			
			driver=new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.opera.driver", "E://swd//operadriver.exe");
			
			driver=new OperaDriver();
		}
		
		driver.get(d);
		Thread.sleep(5000);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		return("Done");
	}
	
	
	public String fill(String l,String d,String c) throws Exception
	{
		driver.findElement(By.xpath(l)).sendKeys(d);
		Thread.sleep(5000);
		return("Done");
	}
	
	
	public String click(String l,String d,String c) throws Exception
	{
		driver.findElement(By.xpath(l)).click();
		Thread.sleep(5000);
		return("Done");
	}
	
	
	public String validateuserid(String l,String d,String c) throws Exception
	{
		try
		{
			if(c.equals("blank") && driver.findElement(By.linkText("Sign up for an account.")).isDisplayed())
			{
				Thread.sleep(5000);
				return("Userid blank Test passed");
				
			}
			else if(c.equalsIgnoreCase("valid") && driver.findElement(By.xpath("//a[@role='button'][contains(text(),'Recover Your Account')]")).isDisplayed())
			{
				Thread.sleep(5000);
				return("Valid Userid Test passed");
			}
				else if(c.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//a[@href='/r.php'][contains(text(),'Sign up for an account.')]")).isDisplayed())
				{
					Thread.sleep(5000);
					return("Invalid Userid Test passed");
				}
			else
			{
				String x=this.screenshot();
				return("Userid Test failed & Screenshot is :"+x);
			}
		}
		catch(Exception e)
		{
			String x = this.screenshot();
			return("Userid Test was interrupted and screenshot is :"+x);
		}
	}
	
	public String close(String l,String d,String c) throws Exception
	{
		driver.quit();
		Thread.sleep(5000);
		return("Done");
	}
	
	public String validatepwd(String l,String d,String c) throws Exception
	{
		try
		{
			if(c.equalsIgnoreCase("blank") && driver.findElement(By.xpath("//div[@class='_4rbf _53ij']")).isDisplayed())
			{
				Thread.sleep(5000);
				return("Blank pwd test passed");
			}
			else if(c.equalsIgnoreCase("valid") && driver.findElement(By.xpath("//*[@class='_1frb']")).isDisplayed())
			{
				Thread.sleep(5000);
				return("Valid pwd Test passed");
			}
			else if(c.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//div[@class='_4rbf _53ij']")).isDisplayed())
			{
				Thread.sleep(5000);
				return("Invalid pwd Test passed");
			}
			else
			{
				String x=this.screenshot();
				return("Pwd test failed & Screenshot is:"+x);
				
			}
		}
		catch(Exception ex)
		{
			String x=this.screenshot();
			return("pwd test interrupted & Screenshot is:"+x);
		}
	}
	
	
	
	public String screenshot() throws Exception 
	{
		Date d=new Date();
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
		String fname=df.format(d);
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File dest=new File(fname+".png");
		FileUtils.copyFile(src, dest);
		return(fname);
	}
	
}
