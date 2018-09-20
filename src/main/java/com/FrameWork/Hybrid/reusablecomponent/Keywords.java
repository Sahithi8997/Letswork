package com.FrameWork.Hybrid.reusablecomponent;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.FrameWork.Hybrid.util.ExtentReportMaker;

public class Keywords extends UI {

	static int i = 0;

	public Keywords(WebDriver driver) {
		super(driver);
	}

	public void openBrowser(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + BaseClass.p.getProperty("driverPath"));
		WebDriver driver = new ChromeDriver();
		new BaseClass(driver);
		BaseClass.APP_LOGS.info("Browser Opened");
	}

	public void closeBrowser(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData) {
		BaseClass.driver.close();
		BaseClass.APP_LOGS.info("Browser Closed");
	}

	public void navigate(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws IOException {
		driver.get((String) userData);
		Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
				data.get(BaseClass.p.getProperty("testCaseID")), "Navigated");
		ExtentReportMaker.pass("Navigated", Screenshots.outputPath());
		BaseClass.APP_LOGS.info("Navigated to URL");
	}

	public void writeInInput(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws Exception {
		try {
			By loc = UI.locators(locatorType, locator);
			//driver.findElement(loc).clear();
			driver.findElement(loc).sendKeys(String.valueOf(data.get(userData)));
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.pass((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Passed");
		} catch (Exception e) {
			System.out.println(e);
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.fail((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Failed");
			throw new Exception();
		}
	}

	public void clickButton(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws Exception {
		try {
			By loc = UI.locators(locatorType, locator);
			driver.findElement(loc).click();
			Thread.sleep(300);
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), "Button clicked" + i);
			i++;
			ExtentReportMaker.pass("Button click", Screenshots.outputPath());
			BaseClass.APP_LOGS.info("Button click passed");
		} catch (NoSuchElementException e) {
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), "Button click Failed");
			ExtentReportMaker.fail("Button click failed", Screenshots.outputPath());
			BaseClass.APP_LOGS.info("Button click failed");
			throw new Exception();
		}
	}

	public void dropDown(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws Exception {
		try {
			By loc = UI.locators(locatorType, locator);
			List<WebElement> startFrom = driver.findElements(loc);
			for (WebElement a : startFrom) {
				if (a.getText().contains((String) data.get(userData)))
					a.click();
			}
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.pass((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Passed");
		} catch (NoSuchElementException e) {
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.fail((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Failed");
			throw new Exception();
		}
	}

	public void checkBox(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws Exception {
		try {
			String locator1 = (String) locator;
			locator1 = locator1.replace("Variable", (String) data.get(userData));
			By loc = UI.locators(locatorType, locator1);
			driver.findElement(loc).click();
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.pass((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Passed");
		} catch (NoSuchElementException e) {
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.fail((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Failed");
			throw new Exception();
		}
	}

	public void upload(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws Exception {
		StringSelection stringSelection = new StringSelection((String) data.get(userData));
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(90);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
				data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
		ExtentReportMaker.pass((String) userData, Screenshots.outputPath());
		BaseClass.APP_LOGS.info(userData + " Passed");
	}

	public void webTable(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws Exception {
		try {
			By loc = UI.locators(locatorType, locator);
			WebElement mytable = driver.findElement(loc);
			List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
			int rows_count = rows_table.size();
			for (int row = 0; row < rows_count; row++) {
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
				int columns_count = Columns_row.size();
				for (int column = 0; column < columns_count; column++) {
					String celtext = Columns_row.get(column).getText();
					if (celtext.equals((String) data.get(userData))) {
						driver.findElement(By.xpath("//table//tr[" + row + "]//td[" + columns_count + "]")).click();
					}
				}
			}
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.pass((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Passed");
		} catch (StaleElementReferenceException e) {

		} catch (NoSuchElementException e) {
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), (String) userData);
			ExtentReportMaker.fail((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info(userData + " Failed");
			throw new Exception();
		}
	}

	public void verification(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws Exception {
		try {
			Assert.assertTrue(driver.getPageSource().contains((String) data.get(userData)));
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), "Verification");
			ExtentReportMaker.pass((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info("Verification Successful");
		} catch (Error e) {
			Screenshots.screenShot(driver, data.get(BaseClass.p.getProperty("testCase")),
					data.get(BaseClass.p.getProperty("testCaseID")), "Verification Failed");
			ExtentReportMaker.fail((String) userData, Screenshots.outputPath());
			BaseClass.APP_LOGS.info("Verification Failed");
			throw new Exception();
		}
	}

	public void waitForTime(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData)
			throws InterruptedException {
		int waitTime = (int) userData * 1000;
		Thread.sleep(waitTime);
	}

	public void waitForElement(LinkedHashMap<Object, Object> data, Object locatorType, Object locator,
			Object userData) {
		int waitTime = (int) userData * 1000;
		By loc = UI.locators(locatorType, locator);
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}

	public void closePopUp(LinkedHashMap<Object, Object> data, Object locatorType, Object locator, Object userData) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}
}
