package Mobile_AppiumAssignment2.AppiumAssignment2;


import java.net.MalformedURLException;
import org.testng.annotations.Test;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.*;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class AppTest 
{
    
DesiredCapabilities capability= new DesiredCapabilities();
	
	public AndroidDriver driver;
	@BeforeMethod 
		public void Beforemethod() throws MalformedURLException, InterruptedException {
			//DesiredCapabilities capability= new DesiredCapabilities();
	        capability.setCapability("deviceName", "Manzoor");
	        //capability.setCapability(MobileCapabilityType.NO_RESET, true);
	        capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	        capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");
	        capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "org.khanacademy.android.ui.library.MainActivity");
	        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        Thread.sleep(15000);
	        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Dismiss\")")).click();
	        Thread.sleep(5000);	        
	}
	@Test
    public void Register_logout() throws InterruptedException, IOException
    {
		File file =    new File("C:\\Users\\PinkiSingh\\Documents\\SDET training\\Appium\\TestData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sh=wb.getSheet("Sheet1");
		int rows=sh.getLastRowNum();
		for(int i=1;i<=rows;i++) {
			String Firstname=sh.getRow(i).getCell(0).getStringCellValue();
			String Lastname=sh.getRow(i).getCell(1).getStringCellValue();
			//String Birthday=sh.getRow(i).getCell(2).getStringCellValue();
			String Email=sh.getRow(i).getCell(2).getStringCellValue();
			String Password=sh.getRow(i).getCell(3).getStringCellValue();
			 driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
		        Thread.sleep(5000);
		        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign up with email\")")).click();
		        Thread.sleep(5000);
		        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"First name\")")).sendKeys(Firstname);
		        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Last name\")")).sendKeys(Lastname);
		        driver.findElementByAccessibilityId("Birthday").click();
		        Thread.sleep(5000);
		        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"OK\")")).click();
		        Thread.sleep(5000);
		        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Email address\")")).sendKeys(Email);
		        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Password\")")).sendKeys(Password);
		        driver.hideKeyboard();
		        driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"CREATE\")")).click();
		        Thread.sleep(15000);
		      try {  
		       Assert.assertFalse (driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sorry, we saw an unexpected error! Please try again later.\")")).isDisplayed());
		      System.out.println("User is not able to create the account due to error message");
		       driver.findElement(MobileBy.className("android.widget.ImageView")).click();
		      } 
		      catch(Exception e){
		    	  System.out.println("Exception has been handled" + e);
		      }
		       driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Settings\"]")).click();
		       Thread.sleep(5000);
		       Assert.assertTrue(driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign out\")")).isDisplayed());
		       {
			       System.out.println(Firstname+" is registered successfully");
		       driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign out\")")).click();
		       Thread.sleep(5000);
		       driver.findElement(MobileBy.className("android.widget.ImageView")).click();
		       }		
		}       
}

}

