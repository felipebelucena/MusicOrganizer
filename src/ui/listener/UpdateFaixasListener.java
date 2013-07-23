package ui.listener;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

public interface UpdateFaixasListener {
	
	public void updateFaixas(ArrayList<JLabel> textFieldLabels,
			ArrayList<JTextField> textFieldsNumero,
			ArrayList<JTextField> textFieldsFaixas);
}
