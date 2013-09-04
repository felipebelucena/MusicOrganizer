package ui.dialog;

import javax.swing.JOptionPane;

import util.ConstantesUI;
import util.Logger;


import Base.TipoPopUp;

/**
 * 
 * @author FrankJunior
 *
 */

public class PopUp {
	
	/**
	 * Classe e abstração para montagem de Pop-up
	 * @param msg
	 * @param tipo
	 */
	public PopUp(String msg, TipoPopUp tipo) {
		switch (tipo) {
		case ERROR:
			JOptionPane.showMessageDialog(null, msg, ConstantesUI.POPUP_ERRO, JOptionPane.ERROR_MESSAGE);
			Logger.error(msg);
			break;
		case INFO:
			JOptionPane.showMessageDialog(null, msg, ConstantesUI.POPUP_INFO, JOptionPane.INFORMATION_MESSAGE);
			Logger.info(msg);
			break;
		case WARNING:
			JOptionPane.showMessageDialog(null, msg, ConstantesUI.POPUP_WARNING, JOptionPane.WARNING_MESSAGE);
			Logger.warning(msg);
			break;
		default:
			break;
		}
	}

}
