package Facade;

import gui.PainelFaixas;
import gui.PainelImagem;
import gui.PainelSelecaoImagem;
import gui.PainelTagsGerais;

import java.io.File;
import java.util.ArrayList;

import Base.Tags;
import Base.TipoBotaoImagem;
import Controller.Controller;
import Controller.ControllerImage;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;

public class Facade {

	private static Facade facade;
	private Controller controller;
	private ControllerImage controllerImage;

	private Facade() {
		controller = new Controller();
		controllerImage = new ControllerImage();
	}

	public static Facade getInstace() {
		if (facade == null) {
			facade = new Facade();
			return facade;
		}
		return facade;
	}

	public ArrayList<Tags> parserFileToTagsList(File disco) {
		return controller.parserFileToTagsList(disco);
	}
	
	public void carregaMusicas(File[] arquivos, PainelTagsGerais painelTagsGerais,
			PainelFaixas painelFaixas, PainelImagem painelImagem, PainelSelecaoImagem painelSelecaoImagem, ArrayList<Tags> listaTags)
			throws ListaNulaException, ListaVaziaException {
		controller.carregaMusicas(arquivos, painelTagsGerais, painelFaixas,painelImagem, painelSelecaoImagem, listaTags);
	}

	public byte[] loadImage(String url,TipoBotaoImagem tipoBotaoImagem) {
		return controllerImage.loadImage(url,tipoBotaoImagem);
	}
	
	public byte[] loadDefaultImage() {
		return controller.loadDefaultImage();
	}	
}
