package kwf2;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class RedmiMethods 
{
public AndroidDriver<MobileElement> driver=null;
	
	
	public String launch(String l,String d,String c) throws Exception
	{
		if(l.equalsIgnoreCase("android"))
		{
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723 \" ");
			
			URL u =new URL("http://0.0.0.0:4723/wd/hub");
			
			//Set the Desired Capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "Redmi prime 2 ");
			caps.setCapability(CapabilityType.BROWSER_NAME,"");
			caps.setCapability("udid", "33ac4017d52"); //Give Device ID of your mobile phone
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "5.1.1");
			caps.setCapability("appPackage", "io.selendroid.testapp");
			caps.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");
			
			
			while(2>1)
			{
				try
				{
					driver=new AndroidDriver<MobileElement>(u,caps);
					break;
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			
		}
		
		else
		{
			System.out.println("Sorry!!!");
		}
		
		
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
	
	
	
	
	public String close(String l,String d,String c) throws Exception
	{
		driver.closeApp();
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		Thread.sleep(5000);
		return("Done");
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
