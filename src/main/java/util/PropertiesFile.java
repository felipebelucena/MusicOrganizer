package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

import Exception.PastaDeMusicaNaoExisteException;
import Exception.PastaDeMusicaVaziaException;
/**
 * 
 * @author FrankJunior
 *
 */
public class PropertiesFile {

	/**
	 * Pega um valor de uma propriedade do arquivo de properties
	 * 
	 * @return String do valor do campo
	 */
	public static String getProperties(String campo) {
		String diretorioDeMusica = null;
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES));
			diretorioDeMusica = prop.getProperty(campo);
		} catch (FileNotFoundException e) {
			diretorioDeMusica = null;
		} catch (IOException e) {
			diretorioDeMusica = null;
		}
		return diretorioDeMusica;
	}

	/**
	 * Seta uma propriedade no arquivo de properties
	 * @param campo - nome do campo do arquivo
	 * @param valor - valor que esse campo terá
	 */
	public static void setProperties(String campo, String valor) {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES));
			prop.setProperty(campo, valor);
			prop.store(new FileOutputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES),null);
		} catch (FileNotFoundException e) {
			Logger.error(e.getMessage());
		} catch (IOException e) {
			Logger.error(e.getMessage());
		}
	}

	/**
	 * Verifica se a pasta de musica é valida
	 * @param musicFolder
	 * @throws PastaDeMusicaNaoExisteException
	 * @throws PastaDeMusicaVaziaException
	 */
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
	
	/**
	 * Reseta o arquivo de properties com configurações default
	 */
	public static void resetProperties(){
		try {
			Properties prop = new Properties();
			prop.setProperty(ConstantesUI.DIRETORIO_DE_MUSICA, ConstantesUI.STRING_VAZIA);
			prop.setProperty(ConstantesUI.TIPO_DE_DISCO, ConstantesUI.DISC_TYPE_DEFAULT);
			prop.store(new FileOutputStream(ConstantesUI.ARQUIVO_DE_PROPERTIES),null);
		} catch (FileNotFoundException e) {
			Logger.error(e.getMessage());
		} catch (IOException e) {
			Logger.error(e.getMessage());
		}
	}
	
	/**
	 * Retorna o tipo de disco das propriedades
	 * 
	 * @return String com o tipo de disco
	 */
	public static String getTipoDeDisco() {
		String tipoDeDisco = getProperties(ConstantesUI.TIPO_DE_DISCO);
		if (tipoDeDisco == null) {
			PropertiesFile.resetProperties();
		}
		return tipoDeDisco;
	}

}
