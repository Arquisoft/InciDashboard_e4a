package com.uniovi.InciDashboard_e4a.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.uniovi.InciDashboard_e4a.selenium.pageobjects.PO_LoginView;
import com.uniovi.InciDashboard_e4a.selenium.pageobjects.PO_View;

public class SeleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new HtmlUnitDriver();
    baseUrl = "http://localhost:8090/";
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	driver.navigate().to(baseUrl);
  }

	@Test
	public void testLogInCorrecto() {
		PO_LoginView.fillForm(driver, "ivan@gmail.com", "123456");
		PO_View.checkElement(driver, "span", "ivan");
	}

	
	@Test
	public void testLogInNoCorrecto() {
		PO_LoginView.fillForm(driver, "maria@prueba.es", "12345678");
		PO_View.checkElement(driver, "label", "Username");
	}

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }


}
