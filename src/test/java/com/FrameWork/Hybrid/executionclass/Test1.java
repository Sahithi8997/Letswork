package com.FrameWork.Hybrid.executionclass;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.FrameWork.Hybrid.reusablecomponent.BaseClass;
import com.FrameWork.Hybrid.util.ExcelUtil;
import com.FrameWork.Hybrid.util.ExtentReportMaker;
import com.FrameWork.Hybrid.util.ProgressBar;
import com.FrameWork.Hybrid.util.SendEmail;

public class Test1 {

	public static Method method[];
	public static ArrayList<Object> keyword;
	public static ArrayList<Object> userData;
	public static ArrayList<Object> locator;
	public static ArrayList<Object> locatorType;

	public static int value = 0;
	public static ProgressBar progressBar = new ProgressBar();;

	@DataProvider(name = "Data")
	public Object[] testData() throws Exception {
		Object[] testObjArray = ExcelUtil.getData();
		return testObjArray;
	}

	@Test(dataProvider = "Data")
	public void functionality(LinkedHashMap<Object, Object> data) throws Exception {
		new BaseClass();
		progressBar.setVisible(true);
		Object testCase = data.get(BaseClass.p.getProperty("testCaseID"));
		Object caseNumber = data.get(BaseClass.p.getProperty("testCase"));
		progressBar.setProgress((String) testCase + "_Data- " + caseNumber, value * ExcelUtil.progress);
		value++;
		ExtentReportMaker.create(testCase + "_Data- " + caseNumber);
		BaseClass.APP_LOGS.info("TEST -> "+testCase + " " + caseNumber);
		if (data.get("Run Mode").equals("Y")) {
			Class<?> c = Class.forName(BaseClass.p.getProperty("pageObjectPackage") + testCase);
			Object instance = c.newInstance();
			method = c.getMethods();
			keyword = ExcelUtil.dataFunction(testCase, BaseClass.p.getProperty("keyword"));
			userData = ExcelUtil.dataFunction(testCase, BaseClass.p.getProperty("data"));
			locator = ExcelUtil.dataFunction(testCase, BaseClass.p.getProperty("locator"));
			locatorType = ExcelUtil.dataFunction(testCase, BaseClass.p.getProperty("locatorType"));
			boolean finished = false;
			outer: for (int i = 0; i < keyword.size() && !finished; i++) {
				for (int j = 0; j < method.length; j++) {
					if (method[j].getName().equals(keyword.get(i))) {
						try {
							System.out.println(method[j].getName());
							method[j].invoke(instance, data, locatorType.get(i), locator.get(i), userData.get(i));
							break;
						} catch (Exception e) {
							finished = true;
							break outer;
						}
					}
				}
			}
			if (finished == true) {
				for (int l = 0; l < method.length; l++) {
					if ("closeBrowser".equals(method[l].getName())) {
						method[l].invoke(instance, null, null, null, null);
					}
				}
//				throw new Exception();
			}
		} else {
			ExtentReportMaker.skip("Run mode disabled");
			BaseClass.APP_LOGS.info("Run mode disabled");
		}
		ExtentReportMaker.flushReport();
		ExtentReportMaker.end();
	}

	@AfterTest
	public void end() throws IOException {
		ExtentReportMaker.flushReport();
		ExtentReportMaker.end();
		BaseClass.APP_LOGS.info("COMPLETE !!!");
		File file = new File(BaseClass.p.getProperty("reportPath"));
//		SendEmail.sendEmail(BaseClass.p.getProperty("reportPath"));
		Desktop.getDesktop().browse(file.toURI());
	}

}
