package util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

import Exception.PastaDeMusicaNaoExisteException;
import Exception.PastaDeMusicaVaziaException;

public class PropertiesFile {

	/**
	 * Pega o diretorio de musica de um arquivo de properties
	 * 
	 * @return String do diretorio de musica do usuario
	 */
	public static String getProperties(String campo) {
		String diretorioDeMusica = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES));
			diretorioDeMusica = prop.getProperty(campo);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			diretorioDeMusica = null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			diretorioDeMusica = null;
		}
		return diretorioDeMusica;
	}

	public static void setProperties(String campo, String valor) {
		Properties prop = new Properties();
		prop.setProperty(campo, valor);
		try {
			prop.store(new FileOutputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES),null);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void verifyMusicFolder(String musicFolder)
			throws PastaDeMusicaNaoExisteException, PastaDeMusicaVaziaException {
		if (musicFolder == null
				|| ConstantesUI.STRING_VAZIA.equals(musicFolder)) {
			throw new PastaDeMusicaVaziaException();
		}
		if (!new File(musicFolder).exists()) {
			throw new PastaDeMusicaNaoExisteException();
		}
	}

}
