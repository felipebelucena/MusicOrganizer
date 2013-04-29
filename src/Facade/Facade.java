package Facade;

import gui.PainelFaixas;
import gui.PainelTagsGerais;

import java.io.File;
import java.util.ArrayList;

import Base.Tags;
import Controller.Controller;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;

public class Facade {

	private static Facade facade;
	private Controller controller;

	private Facade() {
		controller = new Controller();
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
	
	public void updateValues(File[] arquivos, PainelTagsGerais painelTagsGerais,
			PainelFaixas painelFaixas, ArrayList<Tags> listaTags)
			throws ListaNulaException, ListaVaziaException {
		controller.updateValues(arquivos, painelTagsGerais, painelFaixas, listaTags);
	}

}
