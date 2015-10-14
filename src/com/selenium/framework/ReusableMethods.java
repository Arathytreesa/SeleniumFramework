package com.selenium.framework;

import org.openqa.selenium.By;





import java.io.BufferedWriter;
/* WJP to compare two similar XL sheet , for match make green and for no match make red in second xl sheet
 * */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

public class ReusableMethods extends InitialDriver {
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	
/*
 -Created a reusable method 'enterText' for textbox entry.
 -Arguments: objXpath-->Xpath locater for the object
 			: TextVal-->Value entered in the object.
 			:objName-->Name of the object.
 */
public static void enterText(String objXpath, String TextVal, String objName) throws IOException
{
	if(driver.findElement(By.xpath(objXpath)).isDisplayed())
	{
		driver.findElement(By.xpath(objXpath)).sendKeys(TextVal);
	}
	else
	{
		resultFlag=1;
		Update_Report("Fail","enterText",objName+"textbox could not be found");
		System.out.println(objName+"textbox could not be found");
	}
	
}
/*
 -Created a reusable method 'clickMethod'for enabling click operations.
 -Arguments: Objxpath,ObjName
 */
public static void clickMethod(String Objxpath,String ObjName) throws IOException{
	if (driver.findElement(By.xpath(Objxpath)).isDisplayed())
	{
		driver.findElement(By.xpath(Objxpath)).click();
		
	}
	else
	{
		resultFlag=1;
		Update_Report("Fail","clickMethod",ObjName+" could not be found");
		System.out.println(ObjName+" could not be found");
	}

}
public static void validateError(String objXpath,String expectedErrMsg, String ObjName ) throws IOException{
	if(driver.findElement(By.xpath(objXpath)).isDisplayed())
			{
		String actualMessage=driver.findElement(By.xpath(objXpath)).getText();
		if(expectedErrMsg.equals(actualMessage))
		{
		Update_Report("Pass","ValidateError",actualMessage+" is same as "+expectedErrMsg);
		System.out.println(actualMessage+" is same as"+expectedErrMsg);
		}
		else{
			Update_Report("Fail","ValidateError",actualMessage+" is not same as "+expectedErrMsg);
			System.out.println(actualMessage+" is not same as "+expectedErrMsg);
		}
			}
	else{
		resultFlag=1;

		Update_Report("Fail","ValidateError",ObjName+" could not be found");
		System.out.println(ObjName+" could not be found");
	}
	}

	
public static void writeXLFile(String dataTablePath, String sheetName, int iRowCount, int colCount, String XlData) throws IOException{
		/*Step 1: Get the XL Path*/
		File  xlFile = new File(dataTablePath);

		/*step2: Access the Xl File*/
		FileInputStream  xlDoc  = new FileInputStream(xlFile);

		/*Step3: Access the work book (POI jar file) */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);

		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		/*Access Row*/
		HSSFRow   row = sheet.getRow(iRowCount);
		
		/*Access cell*/
		HSSFCell  cell = row.getCell(colCount);
		
		cell.setCellValue(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(XlData);
		
		/* Set Color */
		if(XlData.equalsIgnoreCase("Pass")){
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.BRIGHT_GREEN().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}else if(XlData.equalsIgnoreCase("Fail")){
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.RED().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}
		else {
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}
		
		
		
		FileOutputStream fout = new FileOutputStream(dataTablePath);
		wb.write(fout);
		fout.flush();
		fout.close();
	}
	
	
	
	/* Name Of the Method: readXLFile
	 * Arguments: dataTablePath --> xl sheet path from your computer, sheetName --> name of the sheet
	 * Description: read the xl data and return it to main system
	 * Creation Date: Aug 24 2015
	 * Last Modified: Aug 24 2015
	 * Created by: ProgrammingNinjas
	 * */

	
	
	public static String[][] readXLFile(String dataTablePath, String sheetName) throws IOException{


		/*Step 1: Get the XL Path*/
		File  xlFile = new File(dataTablePath);

		/*step2: Access the Xl File*/
		FileInputStream  xlDoc  = new FileInputStream(xlFile);

		/*Step3: Access the work book (POI jar file) */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);

		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		/*Get row and col*/
		int iRowCount = sheet.getLastRowNum() + 1;


		int iColCount = sheet.getRow(0).getLastCellNum();

		String[][] xlData = new String[iRowCount][iColCount];

		for(int j = 0; j <iRowCount; j++){
			for(int i = 0; i<iColCount; i++){
				xlData[j][i] = sheet.getRow(j).getCell(i).getStringCellValue() ;
				System.out.print(sheet.getRow(j).getCell(i).getStringCellValue() + " ");
			}
			System.out.println();
		}

		return xlData;
	}
	//Generating HTML Report
	public static void startReport(String scriptName, String ReportsPath) throws IOException{
		
		String strResultPath = null;
		
		
		String testScriptName =scriptName;
		
		
		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 
			
			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";
		
		

		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Selenium Report</B></FONT></TD></TR>");


	}

	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ result + "</FONT></TD></TR>");

		} 
	}

}

