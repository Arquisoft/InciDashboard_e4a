package com.uniovi.InciDashboard_e4a.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PostView {

	public static void fillForm(WebDriver driver, String titlep, String descriptionp) {
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		WebElement description = driver.findElement(By.name("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void fillFormPhoto(WebDriver driver, String titlep, String descriptionp, String photoUrl) {
		WebElement title = driver.findElement(By.name("title"));
		title.click();
		title.clear();
		title.sendKeys(titlep);
		WebElement description = driver.findElement(By.name("description"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);
		WebElement photo = driver.findElement(By.name("photo"));
		photoUrl=System.getProperty("user.dir")+photoUrl;
		photo.sendKeys(photoUrl);
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
	}
}
