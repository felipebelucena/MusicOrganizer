package view.progress;

/**
 * 
 * @author frank
 *
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import util.ConstantesUI;
import Facade.Facade;

public class ProgressSave implements PropertyChangeListener {

	private ProgressMonitor pbar;
	private Save save;
	private boolean isOk = false;

	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	public ProgressSave() {

	}

	/**
	 * Método para startar a thread do ProgressMonitor
	 */
	public void start() {
		save = new Save();
		save.addPropertyChangeListener(this);
		save.execute();
	}

	/**
	 * Classe interna, que executa uma Thread separada (SwingWorker) para
	 * alimentar o ProgressMonitor, e salva o disco
	 * 
	 * @author frank
	 * 
	 */
	public class Save extends SwingWorker<Void, Void> {

		protected Void doInBackground() throws Exception {
			pbar = new ProgressMonitor(null,
					ConstantesUI.PROGRESS_LOADING_IMAGE,
					ConstantesUI.PROGRESS_SALVANDO, ConstantesUI.PROGRESS_0,
					ConstantesUI.PROGRESS_100);
			pbar.setMillisToDecideToPopup(1);
			pbar.setMillisToPopup(1);

			Facade.getInstace().salvar(save);

			isOk = true;
			return null;
		}

		public void atualizaProgresso(int percentual) {
			setProgress(percentual);
		}

		@Override
		protected void done() {
			pbar.close();
		}

	}

	/*
	 * Evento que captura as mudanças no ProgressMonitor. Caso o setProgress(),
	 * nao seja mais alimentado no doInBackground(). COlocar aqui
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!isOk) {
			int progress = save.getProgress();
			pbar.setProgress(progress);
		}

	}

}
