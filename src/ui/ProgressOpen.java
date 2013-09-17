package ui;
/**
 * 
 * @author frank
 *
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import ui.dialog.PopUp;
import ui.listener.Atualizador;
import util.ConstantesUI;
import util.Logger;
import Base.Tags;
import Base.TipoPopUp;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;
import Facade.Facade;

public class ProgressOpen implements PropertyChangeListener {

	private ProgressMonitor pbar;
	private JFileChooser fileChooser;
	private Facade facade;
	private Atualizador atualizador = null;
	private File[] listFiles = null;
	ArrayList<Tags> listaTags = null;
	
	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	public ProgressOpen(JFileChooser fileChooser, Atualizador atualizador) {
		this.fileChooser = fileChooser;
		this.atualizador = atualizador;
		this.listFiles = null;
		this.listaTags = null;
		facade = Facade.getInstace();
	}

	/**
	 * Método para startar a thread do ProgressMonitor
	 */
	public void start() {
		Open open = new Open();
		open.addPropertyChangeListener(this);
		open.execute();
	}

	/**
	 * Classe interna, que executa uma Thread separada (SwingWorker)
	 *  para alimentar o ProgressMonitor, e carregar o disco na UI
	 * @author frank
	 *
	 */
	class Open extends SwingWorker<Void, Void> {

		protected Void doInBackground() throws Exception {
			pbar = new ProgressMonitor(null, ConstantesUI.PROGRESS_CARREGANDO,
					ConstantesUI.PROGRESS_INICIANDO, 0, 100);
			int returnFile = fileChooser.showOpenDialog(null);
			if (returnFile == JFileChooser.APPROVE_OPTION) {
				pbar.setProgress(ConstantesUI.PROGRESS_5);
				File disco = fileChooser.getSelectedFile();
				pbar.setProgress(ConstantesUI.PROGRESS_10);
				pbar.setNote(ConstantesUI.PROGRESS_NOTE_EXTRAINDO_TAGS);
				listaTags = facade.parserFileToTagsList(disco);
				pbar.setProgress(ConstantesUI.PROGRESS_20);
				
				try {
					listFiles = disco.listFiles();
					pbar.setProgress(ConstantesUI.PROGRESS_50);
					pbar.setNote(ConstantesUI.PROGRESS_NOTE_EXIBINDO_VALORES);
					facade.carregaMusicas(listFiles, atualizador, listaTags);
					pbar.setProgress(ConstantesUI.PROGRESS_100);
				} catch (ListaNulaException e) {
					listFiles = null;
					listaTags = null;
					atualizador = null;
					Logger.error(e.getMessage());
					done();
				} catch (ListaVaziaException e) {
					listFiles = null;
					listaTags = null;
					atualizador = null;
					Logger.error(e.getMessage());
					done();
					new PopUp(ConstantesUI.POPUP_DISCO_INVALIDO, TipoPopUp.ERROR);
				}
			}
			return null;
		}
		
		@Override
		protected void done() {
			pbar.close();
		}
		
	}

	/*
	 * Evento que captura as mudanças no ProgressMonitor.
	 * Caso o setProgress(), nao seja mais alimentado no
	 * doInBackground(). COlocar aqui
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
	}
	
	public File[] getListFiles() {
		return listFiles;
	}

	public ArrayList<Tags> getListaTags() {
		return listaTags;
	}
}
