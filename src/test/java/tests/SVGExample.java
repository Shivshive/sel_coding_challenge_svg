package tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SVGExample {
	
		
	@Test(dataProvider = "getState")
	public void mapTest(String state) throws InterruptedException {
			
		final By state_path = By.xpath("//*[name()='svg' and @id='map-svg']//*[name()='path' and ancestor::*[name()='g' and @id='"+state+"']]");
//		final By state_subregion_path = By.xpath("//*[name()='svg' and @id='map-svg']//*[name()='path' and ancestor::*[name()='g' and @id='"+state+"']]");
		final By svgIframe = By.xpath("//iframe[contains(@id,'map-instance')]");
		final By svgElement= By.xpath("//*[name()='svg' and @id='map-svg']");
		
		
		final String URL = "https://petdiseasealerts.org/forecast-map/#/";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		
		
		driver.switchTo().frame(driver.findElement(svgIframe));
		
		Thread.sleep(3000);
		
		WebElement state_ele = driver.findElement(state_path);
		
		WebElement svg = driver.findElement(svgElement);
		
		scrollToElement(svg,driver);

		((JavascriptExecutor)driver).executeScript("arguments[0].dispatchEvent(new Event('click'));", state_ele);
		
		Thread.sleep(5000);
		
		driver.quit();
		
	}
	
	public void scrollToElement(WebElement element, WebDriver driver) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	@DataProvider(parallel = true)
	public Object[][] getState(){
		return new Object[][] {
			{"california"},
			{"florida"},
			{"new-york"},
			{"maryland"}
		};
	}


}
