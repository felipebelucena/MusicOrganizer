package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.listener.HabilitarComponentesListener;
import ui.listener.UpdateTagsGeraisListener;
import util.ConstantesUI;

@SuppressWarnings("serial")
public class PainelTagsGeraisVariousArtists extends JPanel implements HabilitarComponentesListener, UpdateTagsGeraisListener {

	private GridBagConstraints gbc;
	private JTextField textFieldAlbum;
	private JTextField textFieldAno;
	private JTextField textFieldGenero;
	
	private JLabel labelAlbum;
	private JLabel labelAno;
	private JLabel labelGenero;

	public PainelTagsGeraisVariousArtists() {
		gbc = new GridBagConstraints();
		initComponents();
	}

	/**
	 * Inicializa os componentes da UI do PainelTagsgerais
	 */
	private void initComponents() {

		labelAlbum = new JLabel(ConstantesUI.LABEL_ARLBUM);
		labelAno = new JLabel(ConstantesUI.LABEL_ANO);
		labelGenero = new JLabel(ConstantesUI.LABEL_GENERO);

		textFieldAlbum = new JTextField(10);
		textFieldAno = new JTextField(10);
		textFieldGenero = new JTextField(10);

		labelAlbum.setLabelFor(textFieldAlbum);
		labelAno.setLabelFor(textFieldAno);
		labelGenero.setLabelFor(textFieldGenero);

		habilitarComponentes(false);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(ConstantesUI.BORDA_TAGS_GERAIS));

		gbc.insets = new Insets(10, 10, 0, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(labelAlbum, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(textFieldAlbum, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(labelAno, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(textFieldAno, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		this.add(labelGenero, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		this.add(textFieldGenero, gbc);
	}
	
	/**
	 * Atualiza os JTextFields do PainelTagsGerais, baseado nas Strings com nomes das Tags gerais
	 * @param artista
	 * @param album
	 * @param ano
	 * @param genero
	 */
	@Override
	public void updateTagsGerais(String artista, String album, String ano, String genero){
		textFieldAlbum.setText(album);
		textFieldAno.setText(ano);
		textFieldGenero.setText(genero);
	}
	
	@Override
	public void habilitarComponentes(boolean habilitar){
		labelAlbum.setEnabled(habilitar);
		labelAno.setEnabled(habilitar);
		labelGenero.setEnabled(habilitar);
		textFieldAlbum.setEnabled(habilitar);
		textFieldAno.setEnabled(habilitar);
		textFieldGenero.setEnabled(habilitar);
	}

	public JTextField getTextFieldAlbum() {
		return textFieldAlbum;
	}

	public JTextField getTextFieldAno() {
		return textFieldAno;
	}

	public JTextField getTextFieldGenero() {
		return textFieldGenero;
	}
	
}
