package com.uniovi.InciDashboard_e4a.selenium.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.InciDashboard_e4a.selenium.util.SeleniumUtils;

public class PO_HomeView extends PO_View {

	static public void checkWelcome(WebDriver driver, int language) {
		//Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String
			textIdiom2, int locale1, int locale2 ) {
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
		//Cambiamos a segundo idioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		//COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_HomeView.checkWelcome(driver, locale2);
		//Volvemos a Espa�ol.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		//Esperamos a que se cargue el saludo de bienvenida en Español
		PO_HomeView.checkWelcome(driver, locale1);
	}

	private static void changeIdiom(WebDriver driver, String textLanguage) {
		PO_NavView.changeIdiom(driver, textLanguage);
	}

	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		PO_NavView.clickOption(driver, textOption, criterio, textoDestino);
	}

}
