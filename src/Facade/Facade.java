package Facade;


import java.io.File;
import java.util.ArrayList;

import ui.PainelFaixas;
import ui.PainelImagem;
import ui.PainelSelecaoImagem;
import ui.PainelTagsGerais;

import Base.Tags;
import Base.TipoBotaoImagem;
import Controller.Controller;
import Controller.ControllerBotoes;
import Controller.ControllerImage;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;

public class Facade {

	private static Facade facade;
	private Controller controller;
	private ControllerImage controllerImage;
	private ControllerBotoes controllerBotoes;

	private Facade() {
		controller = Controller.getInstace();
		controllerImage = ControllerImage.getInstace();
		controllerBotoes = new ControllerBotoes();
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
	 * @param disco
	 * @return ArrayList de tags
	 */
	public ArrayList<Tags> parserFileToTagsList(File disco) {
		return controller.parserFileToTagsList(disco);
	}
	
	/**
	 * Atualiza o <code>PainelFaixas</code> com as tags carregadas do disco,e monta a UI
	 * @param arquivos
	 * @param painelTagsGerais
	 * @param painelFaixas
	 * @param painelImagem
	 * @param painelSelecaoImagem 
	 * @param listaTags
	 * @throws ListaNulaException
	 * @throws ListaVaziaException
	 */
	public void carregaMusicas(File[] arquivos, PainelTagsGerais painelTagsGerais,
			PainelFaixas painelFaixas, PainelImagem painelImagem, PainelSelecaoImagem painelSelecaoImagem, ArrayList<Tags> listaTags)
			throws ListaNulaException, ListaVaziaException {
		controller.carregaMusicas(arquivos, painelTagsGerais, painelFaixas,painelImagem, painelSelecaoImagem, listaTags);
	}

	/**
	 * Carrega uma imagem, de uma URL ou de um arquivo, e atualiza a UI
	 * @param url
	 * @param tipoBotaoImagem
	 */
	public byte[] loadImage(String url,TipoBotaoImagem tipoBotaoImagem) {
		return controllerImage.loadImage(url,tipoBotaoImagem);
	}
	
	/**
	 * Método para carregar a imagem padrão
	 * @return imagem padrão
	 */
	public byte[] loadDefaultImage() {
		return controller.loadDefaultImage();
	}

	public void salvar() {
		controllerBotoes.salvar();
	}

	public void nome2Tags() {
		controllerBotoes.nome2Tags();
	}	
}
