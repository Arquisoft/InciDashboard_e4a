package com.uniovi.InciDashboard_e4a.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.InciDashboard_e4a.InciDashboardE4aApplication;
import com.uniovi.InciDashboard_e4a.selenium.pageobjects.PO_HomeView;
import com.uniovi.InciDashboard_e4a.selenium.pageobjects.PO_LoginView;
import com.uniovi.InciDashboard_e4a.selenium.pageobjects.PO_NavView;
import com.uniovi.InciDashboard_e4a.selenium.pageobjects.PO_View;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciDashboardE4aApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
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
	
	@Test
	public void testCambiarEstado() {
		PO_LoginView.fillForm(driver, "ivan@gmail.com", "123456");
		WebElement element = driver.findElement(By.id("inci6"));
		element.click();
//		element = driver.findElement(By.id("success"));
//		element.click();
		driver.navigate().to("http://localhost:8090/incidences/6/3");
		PO_View.checkElement(driver, "h3", "Incidencias");
		String status = driver.findElement(By.id("status6")).getText();
		assertEquals("CLOSED", status);
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
