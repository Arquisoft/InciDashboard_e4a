package com.uniovi.InciDashboard_e4a.selenium.util;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	
	/**
	 * Aborta si el "texto" no está presente en la página actual
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param texto: texto a buscar
	 */
	static public void textoPresentePagina(WebDriver driver, String texto)
	{
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));		
		assertTrue("Texto " + texto + " no localizado!", list.size() > 0);			
	}

	/**
	 * Aborta si el "texto" está presente en la página actual
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param texto: texto a buscar
	 */
	static public void textoNoPresentePagina(WebDriver driver, String texto)
	{
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));		
		assertTrue("Texto " + texto + " aun presente !", list.size() == 0);			
	}

	/**
	 * Aborta si el "texto" está presente en la página actual tras timeout segundos.
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param texto: texto a buscar
	 * @param timeout: el tiempo máximo que se esperará por la aparición del texto a buscar
	 */
	static public void EsperaCargaPaginaNoTexto(WebDriver driver, String texto, int timeout)
	{
		Boolean resultado = 
				(new WebDriverWait(driver, timeout)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + texto + "')]")));

		assertTrue(resultado);	
	}


	/**
	 * Espera por la visibilidad de un elemento/s en la vista actualmente cargandose en driver. Para ello se empleará una consulta xpath.
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param xpath: consulta xpath.
	 * @param timeout: el tiempo máximo que se esperará por la aparición del elemento a buscar con xpath
	 * @return  Se retornará la lista de elementos resultantes de la búsqueda con xpath.
	 */
	static public List<WebElement> EsperaCargaPaginaxpath(WebDriver driver, String xpath, int timeout)
	{
		WebElement resultado = 
				(new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		assertTrue(resultado != null);
		List<WebElement> elementos = driver.findElements(By.xpath(xpath));

		return elementos;					
	}

	/**
	 * Espera por la visibilidad de un elemento/s en la vista actualmente cargandose en driver. Para ello se empleará una consulta xpath 
	 * según varios criterios..
	 * 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param criterio: "id" or "class" or "text" or "@attribute" or "free". Si el valor de criterio es free es una expresion xpath completa. 
	 * @param text: texto correspondiente al criterio.
	 * @param timeout: el tiempo máximo que se esperará por la apareción del elemento a buscar con criterio/text.
	 * @return Se retornará la lista de elementos resultantes de la búsqueda.
	 */
	static public List<WebElement> EsperaCargaPagina(WebDriver driver, String criterio, String text, int timeout)
	{
		String busqueda;
		if (criterio.equals("id")) busqueda = "//*[contains(@id,'" + text + "')]";
		else if (criterio.equals("class")) busqueda = "//*[contains(@class,'" + text + "')]";
		else if (criterio.equals("text")) busqueda = "//*[contains(text(),'" + text + "')]";
		else if (criterio.equals("free")) busqueda = text;
		else busqueda = "//*[contains("+criterio+",'" + text + "')]";

		return EsperaCargaPaginaxpath(driver, busqueda, timeout);
	}


	/**
	 * PROHIBIDO USARLO PARA VERSIÓN FINAL.
	 * Esperar "segundos" durante la ejecucion del navegador 
	 * @param driver: apuntando al navegador abierto actualmente.
	 * @param segundos: Segundos de bloqueo de la ejecución en el navegador.
	 */
	static public void esperarSegundos(WebDriver driver, int segundos){

		synchronized(driver){
			try {
				driver.wait(segundos * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
