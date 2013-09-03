package ui.dialog;

import java.awt.Cursor;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import ui.listener.Atualizador;
import util.ConstantesUI;
import util.Logger;
import Base.Tags;
import Base.TipoPopUp;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;
import Facade.Facade;

/**
 * 
 * @author FrankJunior
 *
 */

@SuppressWarnings("serial")
public class DialogOpenDisco extends JFileChooser {

	private Facade facade;
	private Atualizador atualizador = null;
	private File[] listFiles = null;
	ArrayList<Tags> listaTags = null;

	public DialogOpenDisco(Atualizador atualizador) {
		this.atualizador = atualizador;
		this.listFiles = null;
		this.listaTags = null;
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
			listaTags = facade.parserFileToTagsList(disco);
			
			try {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				listFiles = disco.listFiles();
				facade.carregaMusicas(listFiles, atualizador, listaTags);
				setCursor(null);
			} catch (ListaNulaException e) {
				listFiles = null;
				listaTags = null;
				atualizador = null;
				Logger.error(e.getMessage());
			} catch (ListaVaziaException e) {
				listFiles = null;
				listaTags = null;
				atualizador = null;
				Logger.error(e.getMessage());
				new PopUp(ConstantesUI.POPUP_DISCO_INVALIDO, TipoPopUp.ERROR);
			}
		}
	}

	public File[] getListFiles() {
		return listFiles;
	}

	public ArrayList<Tags> getListaTags() {
		return listaTags;
	}
	
	

}
