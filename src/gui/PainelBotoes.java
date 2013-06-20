package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.Textos;

public class PainelBotoes extends JPanel {

	private GridBagConstraints gbc;

	public PainelBotoes() {
		gbc = new GridBagConstraints();
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new GridBagLayout());
		JButton botaoSalvar = new JButton(Textos.BOTAO_SALVAR);
		JButton botaoNome2Tag = new JButton(Textos.BOTAO_NOME2TAG);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(15, 10, 0, 10);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		this.add(botaoSalvar, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1;
		this.add(botaoNome2Tag, gbc);
	}

}
