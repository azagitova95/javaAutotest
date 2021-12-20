package org.informatica.po.yandexpo;

import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class YandexMainPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"text\"]")
	WebElement txtbx_search;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/ul/li[2]/div[1]/a/span")
	WebElement image_tab;
	
	@FindAll(@FindBy(how = How.TAG_NAME, using = "a"))
	List<WebElement> listImages;
	
	public YandexMainPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Methods
	
	public void SetSearchTextBox(String arg) {
		txtbx_search.sendKeys(arg, Keys.ENTER);
		Reporter.log("Text entered in Search Box: " + arg, true);
	}
	
	public void SwitchToImagesTab() {
		image_tab.click();
		Reporter.log("Switched to the Images Tab", true);	
	}
	
	public void VerifyTheSearchIsNotEmpty() {
		int count = listImages.size();	
		assertNotNull(count);
		Reporter.log("The search result is not empty. Number of found results: " + listImages.size(), true);
	}
	
	//Business method
	public void SearchAProduct(String arg) {
		SetSearchTextBox(arg);
		SwitchToImagesTab();
		VerifyTheSearchIsNotEmpty();
		Reporter.log("Able to Search for thr Product Successfully. Product: " + arg, true);
	}
	

}
