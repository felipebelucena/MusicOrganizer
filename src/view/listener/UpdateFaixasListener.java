package view.listener;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * 
 * @author FrankJunior
 *
 */
public interface UpdateFaixasListener {
	
	public void updateFaixas(ArrayList<JLabel> textFieldLabels,
			ArrayList<JTextField> textFieldsNumero,
			ArrayList<JTextField> textFieldsFaixas, ArrayList<JTextField> listTextFieldArtista);
}
