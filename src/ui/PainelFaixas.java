package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ui.listener.UpdateFaixasListener;
import util.ConstantesUI;

@SuppressWarnings("serial")
public class PainelFaixas extends JPanel implements UpdateFaixasListener {

	private GridBagConstraints gbc;
	private ArrayList<JLabel> textFieldLabels = null;
	private ArrayList<JTextField> textFieldsNumero = null;
	private ArrayList<JTextField> textFieldsFaixas = null;

	public PainelFaixas() {
		gbc = new GridBagConstraints();
	}

	/**
	 * Constrói a UI do <code>PainelFaixas</code>, com os 3 ArrayList: de
	 * JLabels, e os 2 de JTextFields
	 * 
	 * @param textFieldLabels
	 * @param textFieldsNumero
	 * @param textFieldsFaixas
	 */
	@Override
	public void updateFaixas(ArrayList<JLabel> textFieldLabels,
			ArrayList<JTextField> textFieldsNumero,
			ArrayList<JTextField> textFieldsFaixas) {
		System.out.println("entrei aqui");
		this.removeAll();

		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());

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
		this.setBorder(BorderFactory
				.createTitledBorder(ConstantesUI.BORDA_MUSICAS));
		this.setLayout(new BorderLayout());

		int len = quantidadeDeMusicas / 2;
		for (int i = 0; i < len; i++) {

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
			gbc.insets = new Insets(0, 0, 0, 0);
			painelEsquerda.add(labelFaixa, gbc);
			gbc.gridx++;
			gbc.insets = new Insets(0, 0, 0, 0);
			painelEsquerda.add(textFieldNumero, gbc);
			if (i < len - 1) {
				gbc.gridx++;
				gbc.weighty = 0;
				gbc.weightx = 0;
				painelEsquerda.add(textFieldFaixa, gbc);
			}
		}
		gbc.gridx++;
		gbc.weighty = 1;
		gbc.weightx = 0;
		painelEsquerda.add(textFieldsFaixas.get(len-1), gbc);
		painelEsquerda.revalidate();
		painelEsquerda.repaint();
		painelPrincipal.add(painelEsquerda, BorderLayout.WEST);

		for (int i = len; i < quantidadeDeMusicas; i++) {

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
			gbc.insets = new Insets(0, 0, 0, 0);
			painelDireita.add(labelFaixa, gbc);
			gbc.gridx++;
			gbc.insets = new Insets(0, 0, 0, 0);
			painelDireita.add(textFieldNumero, gbc);
			if (i < quantidadeDeMusicas - 1) {
				gbc.gridx++;
				gbc.weighty = 0;
				gbc.weightx = 0;
				painelDireita.add(textFieldFaixa, gbc);
			}
		}
		gbc.gridx++;
		gbc.weighty = 1;
		gbc.weightx = 0;
		painelDireita.add(textFieldsFaixas.get(quantidadeDeMusicas - 1), gbc);
		painelDireita.revalidate();
		painelDireita.repaint();
		painelPrincipal.add(painelDireita, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(painelPrincipal,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(null);
		this.add(scrollPane, BorderLayout.CENTER);
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
