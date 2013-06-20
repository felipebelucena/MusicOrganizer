package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Textos;

public class PainelFaixas extends JPanel {

	private GridBagConstraints gbc;

	public PainelFaixas() {
		gbc = new GridBagConstraints();
	}

	public void updateValues(ArrayList<JLabel> labels,
			ArrayList<JTextField> textFieldsNumero,
			ArrayList<JTextField> textFieldsFaixas) {

		this.removeAll();
		
		int quantidadeDeMusicas = labels.size();
		this.setBorder(BorderFactory.createTitledBorder(Textos.BORDA_MUSICAS));
		this.setLayout(new GridBagLayout());

		for (int i = 0; i < quantidadeDeMusicas; i++) {

			JLabel labelFaixa = labels.get(i);
			JTextField textFieldNumero = textFieldsNumero.get(i);
			JTextField textFieldFaixa = textFieldsFaixas.get(i);

			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(15, 10, 0, 5);
			this.add(labelFaixa, gbc);
			gbc.gridx++;
			gbc.insets = new Insets(10, 0, 10, 5);
			this.add(textFieldNumero, gbc);
			gbc.gridx++;
			gbc.weighty = 1;
			gbc.weightx = 1;
			this.add(textFieldFaixa, gbc);
			
			this.revalidate();
			this.repaint();

		}
	}

}
