package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelSelecaoImagem extends JPanel {

	private GridBagConstraints gbc;
	private JLabel labelSelecaoImagem;
	private JButton botaoSelecaoImagem;

	public PainelSelecaoImagem() {
		gbc = new GridBagConstraints();
		initComponents();
	}

	private void initComponents() {
		this.setBorder(BorderFactory.createTitledBorder("Selecione a imagem"));
		this.setLayout(new GridBagLayout());
		JTextField textFieldSelecaoImagem = new JTextField(15);
		labelSelecaoImagem = new JLabel("Informe a URL da imagem");
		botaoSelecaoImagem = new JButton("OK");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 10, 5);
		this.add(labelSelecaoImagem, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 50, 5);
		this.add(textFieldSelecaoImagem, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(botaoSelecaoImagem, gbc);
	}

	public JLabel getLabelSelecaoImagem() {
		return labelSelecaoImagem;
	}

	public JButton getBotaoSelecaoImagem() {
		return botaoSelecaoImagem;
	}

}
