package gui;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class DialogOpenImage extends JFileChooser {

	private JTextField textFieldSelecaoImagem;

	public DialogOpenImage(JTextField textFieldSelecaoImagem) {
		this.textFieldSelecaoImagem = textFieldSelecaoImagem;
		init();
	}

	private void init() {
		this.setDialogTitle(ConstantesUI.DIALOG_ESCOLHA_UMA_IMAGEM);
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				ConstantesUI.DIALOG_FILTRO, ConstantesUI.FORMATO_JPG,
				ConstantesUI.FORMATO_PNG);
		this.setFileFilter(filter);
		int returnValue = this.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			textFieldSelecaoImagem.setText(this.getSelectedFile().getPath());
		}
	}

}
