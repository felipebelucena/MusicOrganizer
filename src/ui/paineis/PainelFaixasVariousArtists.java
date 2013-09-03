package ui.paineis;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

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
public class PainelFaixasVariousArtists extends JPanel implements UpdateFaixasListener {

	private GridBagConstraints gbc;
	private ArrayList<JLabel> textFieldLabels = null;
	private ArrayList<JTextField> textFieldsNumero = null;
	private ArrayList<JTextField> textFieldsFaixas = null;

	private static PainelFaixasVariousArtists instace;
	
	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	private PainelFaixasVariousArtists() {
		gbc = new GridBagConstraints();
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Singleton
	 * ---------------------------------------------------------------------
	 */
	public static PainelFaixasVariousArtists getInstace(){
		if (instace == null) {
			instace = new PainelFaixasVariousArtists();
		}
		return instace;
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
		this.removeAll();

		/*
		 * ---------------------------------------------------------------------
		 * Setup do Painel
		 * ---------------------------------------------------------------------
		 */
		List<JTextField> textFieldsArtistas = new ArrayList<JTextField>();
		for (int i = 0; i < textFieldsFaixas.size(); i++) {
			textFieldsArtistas.add(i, new JTextField(15));
		}
		
		String stringLegenda = String.format(ConstantesUI.LEGENDA_FORMATO_VA, 
				ConstantesUI.LEGENDA, ConstantesUI.LEGENDA_ARQUIVO, 
				ConstantesUI.LEGENDA_NUMERO,ConstantesUI.LEGENDA_FAIXA, 
				ConstantesUI.LEGENDA_ARTISTA);
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

		this.textFieldsNumero = textFieldsNumero;
		this.textFieldsFaixas = textFieldsFaixas;
		this.textFieldLabels = textFieldLabels;
		int quantidadeDeMusicas = textFieldLabels.size();
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

			JLabel labelFaixa = textFieldLabels.get(i);
			JTextField textFieldNumero = textFieldsNumero.get(i);
			JTextField textFieldFaixa = textFieldsFaixas.get(i);
			JTextField textFieldArtista = textFieldsArtistas.get(i);

			gbc.gridx = 0;
			gbc.gridy = i+1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 0, 0);
			painelEsquerda.add(labelFaixa, gbc);
			gbc.gridx++;
			painelEsquerda.add(textFieldNumero, gbc);
			gbc.gridx++;
			painelEsquerda.add(textFieldFaixa, gbc);
			if (i < len - 1) {
				gbc.gridx++;
				gbc.weighty = 0;
				gbc.weightx = 0;
				painelEsquerda.add(textFieldArtista, gbc);
			}
		}
		gbc.gridx++;
		gbc.weighty = 1;
		gbc.weightx = 0;
		painelEsquerda.add(textFieldsArtistas.get(len-1), gbc);
		
		painelEsquerda.revalidate();
		painelEsquerda.repaint();
		painelPrincipal.add(painelEsquerda, BorderLayout.WEST);

		/*
		 * ---------------------------------------------------------------------
		 * Componentes da Direita
		 * ---------------------------------------------------------------------
		 */
		for (int i = len; i < quantidadeDeMusicas; i++) {
			JLabel labelFaixa = textFieldLabels.get(i);
			JTextField textFieldNumero = textFieldsNumero.get(i);
			JTextField textFieldFaixa = textFieldsFaixas.get(i);
			JTextField textFieldArtista = textFieldsArtistas.get(i);

			gbc.gridx = 0;
			gbc.gridy = i+1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 0, 0, 0);
			painelDireita.add(labelFaixa, gbc);
			gbc.gridx++;
			painelDireita.add(textFieldNumero, gbc);
			gbc.gridx++;
			painelDireita.add(textFieldFaixa, gbc);
			if (i < quantidadeDeMusicas - 1) {
				gbc.gridx++;
				gbc.weighty = 0;
				gbc.weightx = 0;
				painelDireita.add(textFieldArtista, gbc);
			}
		}
		gbc.gridx++;
		gbc.weighty = 1;
		gbc.weightx = 0;
		painelDireita.add(textFieldsArtistas.get(quantidadeDeMusicas - 1), gbc);
		
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
