package ui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFileChooser;

import util.ConstantesUI;
import util.PropertiesFile;

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
			PropertiesFile.setProperties(musicfolder);
		}
	}

}
