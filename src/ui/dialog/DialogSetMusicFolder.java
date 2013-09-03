package ui.dialog;

import javax.swing.JFileChooser;

import Base.TipoPopUp;

import util.ConstantesUI;
import util.PropertiesFile;

/**
 * 
 * @author FrankJunior
 *
 */

@SuppressWarnings("serial")
public class DialogSetMusicFolder extends JFileChooser {
	
	public DialogSetMusicFolder() {
		init();
	}

	private void init() {
		this.setDialogTitle(ConstantesUI.DIALOG_SET_MUSIC_FOLDER);
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = this.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			String musicfolder = this.getSelectedFile().getPath();
			PropertiesFile.setProperties(ConstantesUI.DIRETORIO_DE_MUSICA, musicfolder);
			new PopUp(ConstantesUI.POPUP_DIRETORIO_DE_MUSICA_SALVO, TipoPopUp.INFO);
		}
	}

}
