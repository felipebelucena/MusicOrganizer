package gui;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Base.Tags;
import Controller.Controller;
import Exception.ListaVaziaException;
import Facade.Facade;

public class DialogOpenDisco extends JFileChooser {

	private Facade facade;
	private PainelTagsGerais painelTagsGerais;

	public DialogOpenDisco(PainelTagsGerais painelTagsGerais) {
		this.painelTagsGerais = painelTagsGerais;
		facade = Facade.getInstace();
		init();
	}

	private void init() {
		this.setDialogTitle("Escolha um CD");
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = this.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File disco = this.getSelectedFile();
			ArrayList<Tags> listaTags = new ArrayList<Tags>();
			listaTags = facade.parserFileToTagsList(disco);
			try {
				painelTagsGerais.updateValues(listaTags);
			} catch (ListaNulaException e) {
				System.out.println(e.getMessage());
			} catch (ListaVaziaException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
