package Controller;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import ui.PopUp;
import util.ConstantesUI;

import Base.TipoBotaoImagem;
import Base.TipoImagemFile;
import Base.TipoPopUp;

public class ControllerImage {
	
	private static ControllerImage controllerImage;
	private byte[] imagem = null;
	
	private ControllerImage() {
		
	}
	
	public static ControllerImage getInstace(){
		if(controllerImage == null){
			controllerImage = new ControllerImage();
			return controllerImage;
		}
		return controllerImage;
	}
	
	/**
	 * Carrega uma imagem, de uma URL ou de um arquivo
	 * @param url
	 * @param tipoBotaoImagem
	 * @return a imagem selecionada
	 */
	public byte[] loadImage(String url,TipoBotaoImagem tipoBotaoImagem) {
			switch (tipoBotaoImagem) {
			case URL:
				//TODO adicionar uma JProgressBar
				imagem = downloadImage(url);
				break;
			case ARQUIVO:
				if(url.endsWith(ConstantesUI.FORMATO_JPG)){
					imagem = loadImageFromFile(url, TipoImagemFile.JPG);
				}else if(url.endsWith(ConstantesUI.FORMATO_PNG)){
					imagem = loadImageFromFile(url, TipoImagemFile.PNG);
				}else{
					new PopUp(ConstantesUI.POPUP_SELECIONE_UMA_IMAGEM, TipoPopUp.WARNING);
					imagem = null;
				}
				break;
			}
		return imagem;
	}

	/**
	 * Carrega a imagem de um arquivo
	 * @param url
	 * @param tipoImagemFile
	 * @return
	 */
	private byte[] loadImageFromFile(String url, TipoImagemFile tipoImagemFile){
		BufferedImage img = null;
		byte[] imagem = null;
		try {
			img = ImageIO.read(new File(url));
			imagem = parserToByte(img,tipoImagemFile);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return imagem;
	}
	
	/**
	 * [parser] converte um BufferedImage em um byte[]
	 * @param img
	 * @param tipoImagemFile
	 * @return byte[] array da imagem
	 */
	private byte[] parserToByte(BufferedImage img, TipoImagemFile tipoImagemFile) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imagem = null;
		try {
			switch (tipoImagemFile) {
			case JPG:
				ImageIO.write(img, ConstantesUI.FORMATO_JPG, baos);
				break;
			case PNG:
				ImageIO.write(img, ConstantesUI.FORMATO_PNG, baos);
				break;
			}
			baos.flush();
			imagem = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return imagem;
	}

	/**
	 * Faz o donwload de uma imagem, a partir de uma URL
	 * @param url
	 * @return byte[] da imagem baixada
	 */
	private byte[] downloadImage(String url) {
		byte[] imagemBaixada = null;
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {

			URL endereco = new URL(url);
			in = new BufferedInputStream(endereco.openStream());
			out = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int numRead = 0;
			int current = 0;
			while ((numRead = in.read(buffer)) != -1) {
				current = 0;
				out.write(buffer, current, numRead);
				current = current + numRead;
			}
			out.close();
			in.close();
			imagemBaixada = out.toByteArray();
		} catch (UnknownHostException e) {
			new PopUp(ConstantesUI.ERRO_PROBLEMA_COM_A_INTERNET, TipoPopUp.ERROR);
		} catch (MalformedURLException e) {
			new PopUp(ConstantesUI.ERRO_URL_INVALIDA, TipoPopUp.ERROR);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return imagemBaixada;
	}

	public byte[] getImagem() {
		return imagem;
	}

}
