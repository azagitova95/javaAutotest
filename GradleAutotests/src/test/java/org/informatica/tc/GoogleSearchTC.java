package org.informatica.tc;

import java.time.Duration;

import org.informatica.po.googlepo.GoogleMainPage;
import org.informatica.po.yandexpo.YandexMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleSearchTC {
	
	WebDriver driver = null;
	

	@BeforeTest
	@Parameters({"browser", "url"})
	public void setUpDriver(String browser, String url) {
		//choosing the browser
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium_tools\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\selenium_tools\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\selenium_tools\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else {
			Assert.assertTrue(false, "No Browser Type Sent");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Reporter.log("Navigated to the Browser: " + browser + "URL: " + url, true);		
	}
	
	@Parameters("searchText")
	@Test
	public void GoogleSearchImageTest(String searchText) {
		String title = driver.getTitle();
		System.out.println("Title is: " + title);
		if (title.equalsIgnoreCase("google")) {
			GoogleMainPage objGoogleMainPage = new GoogleMainPage(driver);
			objGoogleMainPage.SearchAProduct(searchText);
		} else if (title.equalsIgnoreCase("яндекс")) {
			YandexMainPage objYandexMainPage = new YandexMainPage(driver);
			objYandexMainPage.SearchAProduct(searchText);
		}		
		
	}
	
	@AfterTest
	public void closeTheBrowser() {
		driver.quit(); 
	
	}

}
