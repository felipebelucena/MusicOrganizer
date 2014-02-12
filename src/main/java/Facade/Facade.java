package Facade;

import java.io.File;
import java.util.ArrayList;

import model.Tags;
import model.TipoBotaoImagem;

import view.listener.Atualizador;
import view.progress.ProgressSave.Save;
import Controller.ControllerImage;
import Controller.ControllerInput;
import Controller.ControllerOutput;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;

/**
 * 
 * @author FrankJunior
 * 
 */

public class Facade {

	private static Facade facade;
	private ControllerInput controllerInput;
	private ControllerImage controllerImage;
	private ControllerOutput controllerOutput;

	private Facade() {
		controllerInput = ControllerInput.getInstace();
		controllerImage = ControllerImage.getInstace();
		controllerOutput = new ControllerOutput();
	}

	public static Facade getInstace() {
		if (facade == null) {
			facade = new Facade();
			return facade;
		}
		return facade;
	}

	/**
	 * [Parser] Extrai todas as tags de um disco, em um ArrayList
	 * 
	 * @param disco
	 * @return ArrayList de tags
	 */
	public ArrayList<Tags> parserFileToTagsList(File disco) {
		return controllerInput.parserFileToTagsList(disco);
	}

	/**
	 * Atualiza o <code>PainelFaixas</code> com as tags carregadas do disco,e
	 * monta a UI
	 * 
	 * @param arquivos
	 * @param painelTagsGerais
	 * @param painelFaixas
	 * @param painelImagem
	 * @param painelSelecaoImagem
	 * @param listaTags
	 * @throws ListaNulaException
	 * @throws ListaVaziaException
	 */
	public void carregaMusicas(File[] arquivos, Atualizador atualizador,
			ArrayList<Tags> listaTags) throws ListaNulaException,
			ListaVaziaException {
		controllerInput.carregaMusicas(arquivos, atualizador, listaTags);
	}

	/**
	 * Carrega uma imagem, de uma URL ou de um arquivo, e atualiza a UI
	 * 
	 * @param url
	 * @param tipoBotaoImagem
	 */
	public byte[] loadImage(String url, TipoBotaoImagem tipoBotaoImagem) {
		return controllerImage.loadImage(url, tipoBotaoImagem);
	}

	/**
	 * Método para carregar a imagem padrão
	 * 
	 * @return imagem padrão
	 */
	public byte[] loadDefaultImage() {
		return controllerInput.loadDefaultImage();
	}

	public void salvar(Save save) {
		controllerOutput.salvar(save);
	}

	public void nome2Tags() {
		controllerOutput.nome2Tags();
	}
}
