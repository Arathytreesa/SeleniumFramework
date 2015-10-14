package com.selenium.framework;

import java.io.IOException;

public class AutomationScripts extends ReusableMethods{
	
	public static void LoginToSalesforce() throws InterruptedException, IOException
	{
		

		driver.get("http://www.salesforce.com/");
		/*driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);*/
		/*
		 Enabling the click login operation using reusable method clickMethod
		 */
		clickMethod("//a[contains(text(),'Login')]","Login");
			
		Thread.sleep(3000);
		/*
		 Enabling the textbox operations using reusable method enterText
		 */
		enterText("//input[@id='username']","artijose5@gmail.com","UserName");
		enterText("//input[@id='password']","Password12345!","Password");
		
		Thread.sleep(3000);
		
		clickMethod("//button[@id='Login']","Login to Salesforce");
			
		Thread.sleep(3000);
		clickMethod("//*[@id='owner-choice']/div","Owner");
		clickMethod("//*[@id='walkthrough-callout-controls']/a","Walkthrough");
		clickMethod("//*[@id='walkthrough-callout-close']/img","Close");
		
		}


public static void LoginError() throws InterruptedException, IOException
{
	String expectedErrMsg="Please enter your password";
	
	driver.get("http://www.salesforce.com/");
	/*
	 Enabling the click login operation using reusable method clickMethod
	 */
	clickMethod("//a[contains(text(),'Login')]","Login");
		
	Thread.sleep(3000);
	/*
	 Enabling the textbox operations using reusable method enterText
	 */
	enterText("//input[@id='username']","arti161@gmail.com","UserName");
	enterText("//input[@id='password']","","Password");
	
	Thread.sleep(3000);
	
	clickMethod("//button[@id='Login']","Login to Salesforce");
	validateError("//div[@id='error']",expectedErrMsg,"Error");
		
}
public static void ForgotPassword() throws InterruptedException, IOException{

	driver.get("http://www.salesforce.com/");
	/*driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	/*
	 Enabling the click login operation using reusable method clickMethod
	 */
	clickMethod("//a[contains(text(),'Login')]","Login");
		
	Thread.sleep(3000);
			
	clickMethod("//a[contains(text(),'Forgot your password?')]","Forgot Password");
	//Enter the username on textbox when prompted
	enterText("//input[@id='un']","artijose5@gmail.com","UserName");
	//Click on continue
	clickMethod("//input[@id='continue']","Continue");
	/*
	 * - WRITE METHOD TO DISPLAY THE MESSAGE'1 USERNAME SAVED'
	 */
	
}
public static void RememberMe() throws InterruptedException, IOException{
	resultFlag=1;

	driver.get("http://www.salesforce.com/");
	/*driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	/*
	 Enabling the click login operation using reusable method clickMethod
	 */
	clickMethod("//a[contains(text(),'Login')]","Login");
	
	Thread.sleep(3000);
	/*
	 Enabling the textbox operations using reusable method enterText
	 */
	enterText("//input[@id='username']","artijose5@gmail.com","UserName");
	enterText("//input[@id='password']","Password12345!","Password");
	
		
	Thread.sleep(3000);
		
	clickMethod("//input[@id='rememberUn']","Remember Username");
	//click on login to salesforce button
	clickMethod("//button[@id='Login']","Login to Salesforce");
	//click on user account dropdown
	clickMethod("//span[@id='userNavLabel']","Arti Jose");
	//click on logout
	clickMethod("//a[contains(text(),'Logout')]","Log Out");
	
	/*
	- WRITE METHOD TO CHECK IF USERNAME IS DISPLAYED AFTER LOGGING OUT
	- WRITE METHOD TO DISPLAY THE MESSAGE'1 USERNAME SAVED'
	 */
	
	
}
public static void WrongCredentials() throws InterruptedException, IOException{

	String expectedErrMsg="Your login attempt has failed. The username or password may be incorrect,"
			+ " or your location or login time may be restricted. "
			+ "Please contact the administrator at your company for help.";
	
	driver.get("http://www.salesforce.com/");
	/*
	 Enabling the click login operation using reusable method clickMethod
	 */
	clickMethod("//a[contains(text(),'Login')]","Login");
		
	Thread.sleep(3000);
	/*
	 Enabling the textbox operations using reusable method enterText
	 */
	enterText("//input[@id='username']","123","UserName");
	enterText("//input[@id='password']","22131","Password");
	
	Thread.sleep(3000);
	
	clickMethod("//button[@id='Login']","Login to Salesforce");
	validateError("//div[@id='error']",expectedErrMsg,"ErrorMessage");
		
	
}
}
