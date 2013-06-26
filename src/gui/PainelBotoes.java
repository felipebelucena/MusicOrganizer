package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Facade.Facade;


@SuppressWarnings("serial")
public class PainelBotoes extends JPanel {

	private GridBagConstraints gbc;
	private Facade facade;

	public PainelBotoes() {
		gbc = new GridBagConstraints();
		facade = Facade.getInstace();
		initComponents();
	}

	/**
	 * Inicializa os componentes do <code> PainelBotos </code>
	 */
	private void initComponents() {
		this.setLayout(new GridBagLayout());
		JButton botaoSalvar = new JButton(ConstantesUI.BOTAO_SALVAR);
		JButton botaoNome2Tag = new JButton(ConstantesUI.BOTAO_NOME2TAG);
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
		
		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				facade.salvar();
			}
		});
	}

}
