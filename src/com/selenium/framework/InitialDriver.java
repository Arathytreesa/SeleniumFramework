package com.selenium.framework;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitialDriver {
	static WebDriver driver;
	static int resultFlag;

	public static void main(String[] args)throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{

		String dataTablePath= "C:/Users/Arathy/Desktop/Workbook1.xls";
		String[][] recData=ReusableMethods.readXLFile(dataTablePath,"Sheet1");
		String testScript;
		String Run_noRun;
		
		for(int i=1;i<=recData.length-1;i++){
			resultFlag=0;
			Run_noRun=recData[i][1];
			testScript=recData[i][2];
			
			if(Run_noRun.equalsIgnoreCase("Y")){
				
				ReusableMethods.startReport(testScript, "C:/Users/Arathy/Desktop/ReportHTML");
			
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			Method testcase=AutomationScripts.class.getMethod(testScript);
			testcase.invoke(testcase);

			driver.close();
			ReusableMethods.bw.close();
if(resultFlag==0){
				ReusableMethods.writeXLFile(dataTablePath, "Sheet1", i, 3, "Pass");
			}
else{
	ReusableMethods.writeXLFile(dataTablePath, "Sheet1", i, 3, "Fail");
}
		}
			else {
				System.out.println("Test Script "+testScript+" is not required to be executed");
				ReusableMethods.writeXLFile(dataTablePath, "Sheet1", i, 3, "Not Executed");
			}
			
		}
		
		
		/*
		 WHILE USING JAVA REFLECTION METHOD AND SIMPLE WAY TO CALL AUTOMATION TEST CASES FROM AUTOMATION SCRIPTS
		 
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		*/
			/*
			 USING JAVA REFLECTION
			 -calling methods from another class by using name of the method.
			 -reference to a method using method'sname
			 
				 
				String testScript="loginToSalesForce";
				Method testcase=AutomationScripts.class.getMethod(testScript);
				testcase.invoke(testcase);
				  */
				 
		/*
		 * SIMPLE WAY OF CALLING SCRIPTS
		AutomationScripts.LoginToSalesforce();
		AutomationScripts.LoginError();
		AutomationScripts.RememberMe();
		AutomationScripts.ForgotPassword();
		AutomationScripts.WrongCredentials();
		
		driver.close();
		*/
		}
}
		
		
		
		
	