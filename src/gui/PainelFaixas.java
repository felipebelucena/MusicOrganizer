package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelFaixas extends JPanel {

	private GridBagConstraints gbc;
	
	public PainelFaixas() {
		gbc = new GridBagConstraints();
		initComponents();
	}

	private void initComponents() {
		this.setBorder(BorderFactory.createTitledBorder("Musicas"));
		this.setLayout(new GridBagLayout());
		
	      ArrayList<JLabel> labels = new ArrayList<JLabel>();
	      ArrayList<JTextField> textFieldsNumero = new ArrayList<JTextField>();
	      ArrayList<JTextField> textFieldsFaixas = new ArrayList<JTextField>();
	      
		for (int i = 0; i < 20 ; i++) {

			labels.add(new JLabel());
			textFieldsNumero.add(new JTextField(3));
			textFieldsFaixas.add(new JTextField(15));

			JLabel labelFaixa1 = labels.get(i);
			labelFaixa1.setText("A Cor Do Som - Transe Total - 02 - Palco.mp3 --"+i);

			JTextField textFieldNumero = textFieldsNumero.get(i);
			textFieldNumero.setText("N"+i);
			JTextField textFieldFaixa = textFieldsFaixas.get(i);
			textFieldFaixa.setText("Tag"+i);

			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(15, 10, 0, 5);
			this.add(labelFaixa1, gbc);
			gbc.gridx++;
			gbc.insets = new Insets(10, 0, 10, 5);
			this.add(textFieldNumero, gbc);
			gbc.gridx++;
			gbc.weighty = 1;
			gbc.weightx = 1;
			this.add(textFieldFaixa, gbc);

		}
	}

}
