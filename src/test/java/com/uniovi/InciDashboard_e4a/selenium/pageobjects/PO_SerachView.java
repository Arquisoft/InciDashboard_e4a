package com.uniovi.InciDashboard_e4a.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_SerachView {

	public static void fillForm(WebDriver driver, String stringSearch) {
		WebElement search = driver.findElement(By.name("searchText"));
		search.click();
		search.clear();
		search.sendKeys(stringSearch);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
