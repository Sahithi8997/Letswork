package com.FrameWork.Hybrid.util;

import com.FrameWork.Hybrid.reusablecomponent.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportMaker {

	public static ExtentReports extent = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\" + BaseClass.today + "\\"
			+ BaseClass.time + "\\Extent Report" + "\\Report.html");
	public static ExtentTest logg;

	public static void create(String testName) {
		logg = extent.startTest(testName);
	}

	public static void info(String message, String path) {
		logg.log(LogStatus.INFO, message, logg.addScreenCapture(path));
	}

	public static void pass(String message, String path) {
		logg.log(LogStatus.PASS, message, logg.addScreenCapture(path));
	}

	public static void fail(String message, String path) {
		logg.log(LogStatus.FAIL, message, logg.addScreenCapture(path));
	}

	public static void skip(String message) {
		logg.log(LogStatus.SKIP, message);
	}
	
	public static void end() {
		extent.endTest(logg);
	}

	public static void flushReport() {
		extent.flush();
	}
}
