package ui.paineis;

import java.awt.BorderLayout;
import java.awt.Font;
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
/**
 * 
 * @author FrankJunior
 *
 */
@SuppressWarnings("serial")
public class PainelFaixas extends JPanel implements UpdateFaixasListener {

	private GridBagConstraints gbc;
	private ArrayList<JLabel> listLabels = null;
	private ArrayList<JTextField> listTextFieldNumero = null;
	private ArrayList<JTextField> listTextFieldFaixas = null;
	
	private static PainelFaixas instace;

	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	private PainelFaixas() {
		gbc = new GridBagConstraints();
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Singleton
	 * ---------------------------------------------------------------------
	 */
	public static PainelFaixas getInstace(){
		if (instace == null) {
			instace = new PainelFaixas();
		}
		return instace;
	}

	/**
	 * Constrói a UI do <code>PainelFaixas</code>, com os 3 ArrayList: de
	 * JLabels, e os 2 de JTextFields
	 * 
	 * @param listLabels
	 * @param listTextFieldNumero
	 * @param listTextFieldFaixas
	 * @param listTextFieldArtistas - parametro não usado por essa classe
	 */
	@Override
	public void updateFaixas(ArrayList<JLabel> listLabels,
			ArrayList<JTextField> listTextFieldNumero,
			ArrayList<JTextField> listTextFieldFaixas,
			ArrayList<JTextField> listTextFieldArtistas) {

		/*
		 * ---------------------------------------------------------------------
		 * Setup do Painel
		 * ---------------------------------------------------------------------
		 */
		
		this.removeAll();
		String stringLegenda = String.format(ConstantesUI.LEGENDA_FORMATO, 
				ConstantesUI.LEGENDA, ConstantesUI.LEGENDA_ARQUIVO, 
				ConstantesUI.LEGENDA_NUMERO,ConstantesUI.LEGENDA_FAIXA);
		JLabel labelLegenda = new JLabel(stringLegenda);
		Font bold = new Font(labelLegenda.getFont().getName(), Font.BOLD, labelLegenda.getFont().getSize());
		labelLegenda.setFont(bold);
		
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());

		JPanel painelEsquerda = new JPanel();
		painelEsquerda.setLayout(new GridBagLayout());
		painelEsquerda.removeAll();

		JPanel painelDireita = new JPanel();
		painelDireita.setLayout(new GridBagLayout());
		painelDireita.removeAll();
		
		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		painelNorte.add(labelLegenda, gbc);

		this.listTextFieldNumero = listTextFieldNumero;
		this.listTextFieldFaixas = listTextFieldFaixas;
		this.listLabels = listLabels;
		int quantidadeDeMusicas = listLabels.size();
		this.setBorder(BorderFactory
				.createTitledBorder(ConstantesUI.BORDA_MUSICAS));
		this.setLayout(new BorderLayout());

		/*
		 * ---------------------------------------------------------------------
		 * Componentes da Esquerda
		 * ---------------------------------------------------------------------
		 */
		int len = quantidadeDeMusicas / 2;
		for (int i = 0; i < len; i++) {

			JLabel labelFaixa = listLabels.get(i);
			JTextField textFieldNumero = listTextFieldNumero.get(i);
			JTextField textFieldFaixa = listTextFieldFaixas.get(i);

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
		painelEsquerda.add(listTextFieldFaixas.get(len-1), gbc);
		painelEsquerda.revalidate();
		painelEsquerda.repaint();
		painelPrincipal.add(painelEsquerda, BorderLayout.WEST);

		/*
		 * ---------------------------------------------------------------------
		 * Componentes da Direita
		 * ---------------------------------------------------------------------
		 */
		for (int i = len; i < quantidadeDeMusicas; i++) {

			JLabel labelFaixa = listLabels.get(i);
			JTextField textFieldNumero = listTextFieldNumero.get(i);
			JTextField textFieldFaixa = listTextFieldFaixas.get(i);

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
		painelDireita.add(listTextFieldFaixas.get(quantidadeDeMusicas - 1), gbc);
		painelDireita.revalidate();
		painelDireita.repaint();
		painelPrincipal.add(painelDireita, BorderLayout.CENTER);
		painelPrincipal.add(painelNorte, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(painelPrincipal,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(null);
		this.add(scrollPane, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	public ArrayList<JLabel> getlistLabels() {
		return listLabels;
	}

	public ArrayList<JTextField> getlistTextFieldNumero() {
		return listTextFieldNumero;
	}

	public ArrayList<JTextField> getlistTextFieldFaixas() {
		return listTextFieldFaixas;
	}

}
