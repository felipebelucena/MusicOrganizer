package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.ConstantesUI;


@SuppressWarnings("serial")
public class PainelFaixas extends JPanel {

	private GridBagConstraints gbc;
	private ArrayList<JLabel> textFieldLabels = null;
	private ArrayList<JTextField> textFieldsNumero = null;
	private ArrayList<JTextField> textFieldsFaixas = null;

	public PainelFaixas() {
		gbc = new GridBagConstraints();
	}

	/**
	 * Constrói a UI do <code>PainelFaixas</code>, com os 3 ArrayList: de JLabels, e os 2 de JTextFields
	 * @param textFieldLabels
	 * @param textFieldsNumero
	 * @param textFieldsFaixas
	 */
	public void updateValues(ArrayList<JLabel> textFieldLabels,
			ArrayList<JTextField> textFieldsNumero,
			ArrayList<JTextField> textFieldsFaixas) {

		this.removeAll();
		
		JPanel painelEsquerda = new JPanel();
		painelEsquerda.setLayout(new GridBagLayout());
		painelEsquerda.removeAll();
		
		JPanel painelDireita = new JPanel();
		painelDireita.setLayout(new GridBagLayout());
		painelDireita.removeAll();
		
		this.textFieldsNumero = textFieldsNumero;
		this.textFieldsFaixas = textFieldsFaixas;
		this.textFieldLabels = textFieldLabels;
		int quantidadeDeMusicas = textFieldLabels.size();
		this.setBorder(BorderFactory.createTitledBorder(ConstantesUI.BORDA_MUSICAS));
		this.setLayout(new BorderLayout());

		for (int i = 0; i < quantidadeDeMusicas/2; i++) {

			JLabel labelFaixa = textFieldLabels.get(i);
			JTextField textFieldNumero = textFieldsNumero.get(i);
			JTextField textFieldFaixa = textFieldsFaixas.get(i);

			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(15, 0, 0, 0);
			painelEsquerda.add(labelFaixa, gbc);
			gbc.gridx++;
			gbc.insets = new Insets(10, 0, 0, 0);
			painelEsquerda.add(textFieldNumero, gbc);
			gbc.gridx++;
			gbc.weighty = 1;
			gbc.weightx = 0;
			painelEsquerda.add(textFieldFaixa, gbc);
			
			painelEsquerda.revalidate();
			painelEsquerda.repaint();
			this.add(painelEsquerda, BorderLayout.WEST);
		}
		
		for (int i = quantidadeDeMusicas/2; i < quantidadeDeMusicas; i++) {

			JLabel labelFaixa = textFieldLabels.get(i);
			JTextField textFieldNumero = textFieldsNumero.get(i);
			JTextField textFieldFaixa = textFieldsFaixas.get(i);

			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(15, 0, 0, 0);
			painelDireita.add(labelFaixa, gbc);
			gbc.gridx++;
			gbc.insets = new Insets(10, 0, 0, 0);
			painelDireita.add(textFieldNumero, gbc);
			gbc.gridx++;
			gbc.weighty = 1;
			gbc.weightx = 0;
			painelDireita.add(textFieldFaixa, gbc);
			
			painelDireita.revalidate();
			painelDireita.repaint();
			this.add(painelDireita, BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	}

	public ArrayList<JLabel> getTextFieldLabels() {
		return textFieldLabels;
	}

	public ArrayList<JTextField> getTextFieldsNumero() {
		return textFieldsNumero;
	}

	public ArrayList<JTextField> getTextFieldsFaixas() {
		return textFieldsFaixas;
	}
	

}
