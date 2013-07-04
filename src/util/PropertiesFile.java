package util;

import gui.ConstantesUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesFile {
	
	/**
	 * Pega o diretorio de musica de um arquivo de properties
	 * @return String do diretorio de musica do usuario
	 */
	public static String getProperties() {
		String diretorioDeMusica = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES));
			diretorioDeMusica = prop
					.getProperty(ConstantesUI.DIRETORIO_DE_MUSICA);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			diretorioDeMusica = null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			diretorioDeMusica = null;
		}
		return diretorioDeMusica;
	}
	
	public static void setProperties(String name) {
		Properties prop = new Properties();
		prop.setProperty(ConstantesUI.DIRETORIO_DE_MUSICA, name);
		try {
			prop.store(new FileOutputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES), null);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
