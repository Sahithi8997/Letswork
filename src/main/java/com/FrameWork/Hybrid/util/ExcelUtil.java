package com.FrameWork.Hybrid.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.FrameWork.Hybrid.reusablecomponent.BaseClass;

public class ExcelUtil {

	public static File myFile;
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet mySheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int progress;

	public static Object[] getData() throws Exception {
		myFile = new File(System.getProperty("user.dir")+BaseClass.p.getProperty("excelPath"));
		fis = new FileInputStream(myFile);
		workbook = new XSSFWorkbook(fis);
		mySheet = workbook.getSheet(BaseClass.p.getProperty("testCase"));
		int ci = 0, cj = 0;
		int totalRows = mySheet.getLastRowNum()+1;
		for (int i = 1; i < totalRows; i++) {
			mySheet = workbook.getSheet(BaseClass.p.getProperty("testCase"));
			mySheet = workbook.getSheet((String) getCellData(i, 0));
			cj = cj + mySheet.getLastRowNum();
		}
		progress = 2000/cj;
		Object[] tabArr = new Object[cj];
		for (int i = 1; i < totalRows; i++) {
			mySheet = workbook.getSheet(BaseClass.p.getProperty("testCase"));
			Object a = getCellData(0, 0);
			Object b = getCellData(i, 0);
			Object c = getCellData(0, 2);
			Object d = getCellData(i, 2);
			mySheet = workbook.getSheet((String) getCellData(i, 0));
			int totRow = mySheet.getLastRowNum() + 1;
			for (int m = 1; m < totRow; m++) {
				LinkedHashMap<Object, Object> map1 = new LinkedHashMap<Object, Object>();
				map1.put(BaseClass.p.getProperty("testCase"),m);
				map1.put(a, b);
				map1.put(c, d);
				for (int j = 0; j < mySheet.getRow(0).getLastCellNum(); j++) {
					Object s = getCellData(0, j);
					Object s1 = getCellData(m, j);
					map1.put(s, s1);
				}
				tabArr[ci] = map1;
				ci++;
			}
		}
		return tabArr;
	}

	public static Object getCellData(int RowNum, int ColNum) throws Exception {
		Object CellData = null;
		try {
			cell = mySheet.getRow(RowNum).getCell(ColNum);
			int type = mySheet.getRow(RowNum).getCell(ColNum).getCellType();
			if (type == 0) {
				CellData = (int)cell.getNumericCellValue();
			} else {
				CellData = cell.getStringCellValue();
			}
		} catch (Exception e) {
			CellData = null;
		}
		return CellData;
	}

	public static ArrayList<Object> dataFunction(Object testCase, Object name) throws Exception {
		ArrayList<Object> dataFn = new ArrayList<Object>();
		mySheet = workbook.getSheet(BaseClass.p.getProperty("testStep"));
		int tests = mySheet.getLastRowNum();
		for (int k = 1; k <= tests; k++) {
			if (getCellData(k, 0).equals(testCase)) {
				for (int l = 0; l < mySheet.getRow(0).getLastCellNum(); l++) {
					if (getCellData(0, l).equals(name)) {
						dataFn.add(getCellData(k, l));
						break;
					}
				}
			}
		}
		return dataFn;
	}

}
