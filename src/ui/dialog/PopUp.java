package ui.dialog;

import javax.swing.JOptionPane;

import util.ConstantesUI;


import Base.TipoPopUp;

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
			break;
		case INFO:
			JOptionPane.showMessageDialog(null, msg, ConstantesUI.POPUP_INFO, JOptionPane.INFORMATION_MESSAGE);
			break;
		case WARNING:
			JOptionPane.showMessageDialog(null, msg, ConstantesUI.POPUP_WARNING, JOptionPane.WARNING_MESSAGE);
			break;
		default:
			break;
		}
	}

}
