package com.uniovi.InciDashboard_e4a.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView {

	public static void fillForm(WebDriver driver, String dnip, String passwordp) {
		WebElement dni = driver.findElement(By.id("email"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement password = driver.findElement(By.id("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		By boton = By.id("login");
		driver.findElement(boton).click();
	}

}
