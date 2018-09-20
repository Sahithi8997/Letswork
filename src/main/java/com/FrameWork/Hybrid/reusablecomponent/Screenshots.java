package com.FrameWork.Hybrid.reusablecomponent;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

	static String outputPath;
	static String finalPath;

	public static void screenShot(WebDriver driver, Object number, Object caseName, String name) throws IOException {
		outputPath = System.getProperty("user.dir")+"\\Reports\\" + BaseClass.today + "\\" + BaseClass.time + "\\Screenshots"
				+ "\\Test Case-" + caseName + "\\Data-" + number;
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		finalPath = outputPath + "\\" + name + ".png";
		File DestFile = new File(finalPath);
		FileUtils.copyFile(SrcFile, DestFile);

	}

	public static String outputPath() {
		return finalPath;
	}

}
