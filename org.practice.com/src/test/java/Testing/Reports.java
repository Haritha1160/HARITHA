package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Demo.Demolog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Reports {
	public static ExtentHtmlReporter obj1=new ExtentHtmlReporter("./reports/extentreport.html");
	public static ExtentHtmlReporter duplicatehtmlfile=new ExtentHtmlReporter("./reports/extentreport.html");
	public static ExtentReports obj2 = new ExtentReports();
	public static WebDriver driver;
	                                                 //extent report = n no of files
	                                    //cucmber =1 report (many test case)
	
	@BeforeClass
	public static void setDriver() {
		Demolog.log(2);
		obj2.attachReporter(obj1);
		ExtentTest obj3= obj2.createTest("Opening the Test");
		obj3.log(Status.INFO, "Opening  the browser");
		
		try {
			Demolog.log(1);
			WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    obj3.log(Status.PASS, "The browser open");
	}
		catch(Exception e) {
			Demolog.log(3);
			System.out.println("before report block");
			obj3.log(Status.FAIL, "The browser does not open");
		}
		
		obj2.flush();  //LOCKED(write)IN html // flush = lock the msg in html file
	}
	
	@Test
	public static void search() {
		Demolog.log(2);
		obj2.attachReporter(obj1);  //attach html file .... obj1= onject for html file ... obj2= object for html reports(multiple html files)
		//obj2.attachReporter(duplicatehtmlfile);
		// object has to include inside a method ,inorder to include html file
		ExtentTest obj4= obj2.createTest("Opening the Application and searching data");
		obj4.log(Status.INFO, "Opening  the google site");
		
		try {
			Demolog.log(1);
		 driver.get("https://www.google.com/");
		 obj4.log(Status.PASS, "The application opend in the browser");
		 
	WebElement	 search =driver.findElement(By.name("q"));
			search.sendKeys("java");
			search.sendKeys(Keys.ENTER);
			try {
				Demolog.log(1);
				Assert.assertEquals(driver.getTitle(),"java - Google Search");
			}
			catch(Exception e) {
				Demolog.log(4);
				System.out.println("tittle not found");
			}
			Demolog.log(2);
			Screenshot.screenShot(1);
			Thread.sleep(3000);
			obj4.log(Status.PASS, "The application navigated to  next page");
			//obj3.addScreenCaptureFromPath("C:\\Users\\HDEGALA\\eclipse-workspace\\org.practice.com\\Screenshot1.png");
			//obj3.addScreenCaptureFromPath("./Screenshot/Screenshot1.png");
			
			String  filepath=System.getProperty("user.dir")+"//Screenshot//Screenshot1.png";
			obj4.addScreenCaptureFromPath(filepath);
	}
		catch(Exception e) {
			Demolog.log(3);
			System.out.println(e);
			obj4.log(Status.FAIL, "The application fail");
		}
		obj2.flush();
	}
	@AfterClass
   public static void closewindow() {
		Demolog.log(2);
		obj2.attachReporter(obj1);
		ExtentTest obj5= obj2.createTest("closing browser");
		obj5.log(Status.INFO, "closing the application");
		try {
			Demolog.log(1);
    driver.close();
    obj5.log(Status.PASS, "The application CLOSED");
}
		catch(Exception e) {
		System.out.println(e);
		obj5.log(Status.FAIL, "The application fail TO CLOSE");
		Demolog.log(3);
		}
		obj2.flush();
	}
}	
			
			
	
