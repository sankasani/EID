package reusableFunctions;

import org.openqa.selenium.By;

import common.GenericLibrary;

public class MoodysApplicationSpecific {

	public static void popupHandler(String objRepo) throws InterruptedException {
		Thread.sleep(2000);
		if(GenericLibrary.getDriver().findElement(By.xpath(objRepo)).isDisplayed()==true) {
			GenericLibrary.getDriver().findElement(By.xpath(objRepo)).click();
		}
	}
	
	public static void Logout() throws InterruptedException {
		Thread.sleep(1000);
		GenericLibrary.getDriver().switchTo().defaultContent();
		GenericLibrary.getDriver().switchTo().frame("menu");
		GenericLibrary.getDriver().findElement(By.xpath("//*[@id=\"trMenu\"]/td [1]/a")).click();
		
		}
	
}
