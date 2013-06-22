package gui;

import javax.swing.JOptionPane;


import Base.TipoPopUp;

public class PopUp {
	
	public PopUp(String msg, TipoPopUp tipo) {
		switch (tipo) {
		case ERROR:
			JOptionPane.showMessageDialog(null, msg, ConstantesUI.POPUP_ERRO, JOptionPane.ERROR_MESSAGE);
			break;
		case INFO:
			JOptionPane.showMessageDialog(null, msg, ConstantesUI.POPUP_INFO, JOptionPane.INFORMATION_MESSAGE);
			break;

		default:
			break;
		}
	}

}
