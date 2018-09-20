package com.FrameWork.Hybrid.reusablecomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.FrameWork.Hybrid.reusablecomponent.BaseClass;

public class UI extends BaseClass {

	public UI(WebDriver driver) {
		super(driver);
	}

	public static By locators(Object locatorType, Object locator) {
		By by;
		switch ((String)locatorType) {
		case "id":
			by = By.id((String) locator);
			break;
		case "name":
			by = By.name((String) locator);
			break;
		case "xpath":
			by = By.xpath((String) locator);
			break;
		case "css":
			by = By.cssSelector((String) locator);
			break;
		case "linktext":
			by = By.linkText((String) locator);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}

}
