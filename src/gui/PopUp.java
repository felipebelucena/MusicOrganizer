package gui;

import javax.swing.JOptionPane;

import Base.TipoPopUp;

public class PopUp {
	
	public PopUp(String msg, TipoPopUp tipo) {
		switch (tipo) {
		case ERROR:
			JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
			break;
		case INFO:
			JOptionPane.showMessageDialog(null, msg, "info", JOptionPane.INFORMATION_MESSAGE);
			break;

		default:
			break;
		}
	}

}
