package com.FrameWork.Hybrid.reusablecomponent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

public class BaseClass {

	public static WebDriver driver;
	public static String today;
	public static String time;
	public static Logger APP_LOGS = Logger.getLogger(BaseClass.class);
	public static Properties p = new Properties();

	static {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		today = formatter.format(date);
		formatter = new SimpleDateFormat("HH-mm-ss a");
		time = formatter.format(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy/HH-mm-ss a");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		BufferedReader reader = null;
		try {
			String propertyFilePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\FrameWork\\Hybrid\\constants\\constant.properties";
			reader = new BufferedReader(new FileReader(propertyFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BaseClass() {
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\log4j.properties");
	}

	public BaseClass(WebDriver driver) {
		BaseClass.driver = driver;
		BaseClass.p.setProperty("reportPath", System.getProperty("user.dir") + "\\Reports\\" + BaseClass.today
				+ "\\" + BaseClass.time + "\\Extent Report\\Report.html");
	}

}
