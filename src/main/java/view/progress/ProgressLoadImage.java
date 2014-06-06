package view.progress;

/**
 * 
 * @author frank
 *
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import model.TipoBotaoImagem;

import util.ConstantesUI;
import view.dialog.DialogOpenImage;
import view.listener.Atualizador;
import Facade.Facade;

public class ProgressLoadImage implements PropertyChangeListener {

	private ProgressMonitor pbar;
	private String url;
	private TipoBotaoImagem tipoBotaoImagem;
	private Atualizador atualizador;
	private JTextField textFieldSelecaoImagem;

	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	public ProgressLoadImage(String url, TipoBotaoImagem tipoBotaoImagem,
			Atualizador atualizador) {
		this.url = url;
		this.tipoBotaoImagem = tipoBotaoImagem;
		this.atualizador = atualizador;
	}

	public ProgressLoadImage(TipoBotaoImagem tipoBotaoImagem,
			Atualizador atualizador, JTextField textFieldSelecaoImagem) {
		this.textFieldSelecaoImagem = textFieldSelecaoImagem;
		this.tipoBotaoImagem = tipoBotaoImagem;
		this.atualizador = atualizador;
	}

	/**
	 * Método para startar a thread do ProgressMonitor
	 */
	public void start() {
		LoadImage loadImage = new LoadImage();
		loadImage.addPropertyChangeListener(this);
		loadImage.execute();
	}

	/**
	 * Classe interna, que executa uma Thread separada (SwingWorker) para
	 * alimentar o ProgressMonitor, e faz o loading da imagem
	 * 
	 * @author frank
	 * 
	 */
	class LoadImage extends SwingWorker<Void, Void> {

		protected Void doInBackground() throws Exception {
			pbar = new ProgressMonitor(null,
					ConstantesUI.PROGRESS_LOADING_IMAGE,
					ConstantesUI.PROGRESS_INICIANDO, ConstantesUI.PROGRESS_0,
					ConstantesUI.PROGRESS_100);

			switch (tipoBotaoImagem) {
			case URL:
				pbar.setProgress(ConstantesUI.PROGRESS_5);
				byte[] imagem = Facade.getInstace().loadImage(url,
						TipoBotaoImagem.URL);
				pbar.setProgress(ConstantesUI.PROGRESS_50);
				pbar.setNote(ConstantesUI.PROGRESS_UPDATE_IMAGE);
				if (imagem != null) {
					atualizador.updateImage(imagem);
				}
				pbar.setProgress(ConstantesUI.PROGRESS_100);
				break;
			case ARQUIVO:
				if (ConstantesUI.STRING_VAZIA.equals(textFieldSelecaoImagem
						.getText())) {
					new DialogOpenImage(textFieldSelecaoImagem);
				}
				pbar.setProgress(ConstantesUI.PROGRESS_50);
				String url = textFieldSelecaoImagem.getText();
				imagem = Facade.getInstace().loadImage(url,
						TipoBotaoImagem.ARQUIVO);
				pbar.setNote(ConstantesUI.PROGRESS_UPDATE_IMAGE);
				if (imagem != null) {
					pbar.setProgress(ConstantesUI.PROGRESS_80);
					pbar.setNote(ConstantesUI.PROGRESS_UPDATE_IMAGE);
					atualizador.updateImage(imagem);
				} else {
					textFieldSelecaoImagem.setText(ConstantesUI.STRING_VAZIA);
				}
				pbar.setProgress(ConstantesUI.PROGRESS_100);

				break;

			default:
				break;
			}

			return null;
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

	}

}
