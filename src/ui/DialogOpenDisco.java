package ui;

import java.awt.Cursor;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import util.ConstantesUI;

import Base.Tags;
import Base.TipoPopUp;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;
import Facade.Facade;

@SuppressWarnings("serial")
public class DialogOpenDisco extends JFileChooser {

	private Facade facade;
	private PainelTagsGerais painelTagsGerais;
	private PainelFaixas painelFaixas;
	private PainelImagem painelImagem;
	private PainelSelecaoImagem painelSelecaoImagem;

	public DialogOpenDisco(PainelTagsGerais painelTagsGerais, PainelFaixas painelFaixas, PainelImagem painelImagem, PainelSelecaoImagem painelSelecaoImagem) {
		this.painelTagsGerais = painelTagsGerais;
		this.painelFaixas = painelFaixas;
		this.painelImagem = painelImagem;
		this.painelSelecaoImagem = painelSelecaoImagem;
		facade = Facade.getInstace();
		init();
	}

	/**
	 * Inicializa o JFileChooser, para carregar o disco
	 */
	private void init() {
		this.setDialogTitle(ConstantesUI.DIALOG_ESCOLHA_UM_CD);
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = this.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			File disco = this.getSelectedFile();
			ArrayList<Tags> listaTags = null;
			listaTags = facade.parserFileToTagsList(disco);
			
			try {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				facade.carregaMusicas(disco.listFiles(), painelTagsGerais, painelFaixas,painelImagem, painelSelecaoImagem, listaTags);
				setCursor(null);
			} catch (ListaNulaException e) {
				System.out.println(e.getMessage());
			} catch (ListaVaziaException e) {
				System.out.println(e.getMessage());
				new PopUp(ConstantesUI.POPUP_DISCO_INVALIDO, TipoPopUp.ERROR);
			}
		}
	}

}
