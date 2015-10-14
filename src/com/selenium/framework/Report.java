package com.selenium.framework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;




public class Report{
//	static WebDriver driver;
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

	@Test
	public void test() throws Exception {

		startReport("Decending order sort","C:/Users//Desktop/Report/");
		
		Update_Report( "Pass", "Ascending order sort",  "Ascending order sort executing is complete.");
		Update_Report( "Fail", "Ascending order sort",  "Ascending order sort executing is complete.");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Pass", "EnterUserName",  "Username user@gmail.com is entered");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Pass", "EnterUserName",  "Username user@gmail.com is entered");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Pass", "EnterUserName",  "Username user@gmail.com is entered");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Pass", "EnterUserName",  "Username user@gmail.com is entered");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Pass", "EnterUserName",  "Username user@gmail.com is entered");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Pass", "EnterUserName",  "Username user@gmail.com is entered");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Pass", "EnterUserName",  "Username user@gmail.com is entered");
		Update_Report( "Fail", "Thank you",  " Please leave us early today");
		Update_Report( "Fail", "Enter Password",  "Password 1234 is entered in password field");
		
		Update_Report("Fail", "EnterPassword", "Password Password123 is entered.");
		Update_Report("Fail", "EnterPassword", "Password Password123 is entered.");
		Update_Report("Fail", "EnterPassword", "Password Password123 is entered.");
		Update_Report("Fail", "EnterPassword", "Password Password123 is entered.");
		Update_Report("Fail", "EnterPassword", "Password Password123 is entered.");
		Update_Report("Fail", "EnterPassword", "Password Password123 is entered.");
		
		
		bw.close();


	}

	
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

