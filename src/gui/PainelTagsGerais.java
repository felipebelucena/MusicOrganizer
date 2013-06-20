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

import Base.Tags;
import Exception.ListaVaziaException;
import Facade.Facade;

public class PainelTagsGerais extends JPanel {

	private GridBagConstraints gbc;
	private Facade facade;
	private JTextField textFieldArtista;
	private JTextField textFieldAlbum;
	private JTextField textFieldAno;
	private JTextField textFieldGenero;

	public PainelTagsGerais() {
		gbc = new GridBagConstraints();
		facade = Facade.getInstace();
		initComponents();
	}

	private void initComponents() {

		JLabel labelArtista = new JLabel(Textos.LABEL_ARTISTA);
		JLabel labelAlbum = new JLabel(Textos.LABEL_ARLBUM);
		JLabel labelAno = new JLabel(Textos.LABEL_ANO);
		JLabel labelGenero = new JLabel(Textos.LABEL_GENERO);

		textFieldArtista = new JTextField(10);
		textFieldAlbum = new JTextField(10);
		textFieldAno = new JTextField(10);
		textFieldGenero = new JTextField(10);

		labelArtista.setLabelFor(textFieldArtista);
		labelAlbum.setLabelFor(textFieldAlbum);
		labelAno.setLabelFor(textFieldAno);
		labelGenero.setLabelFor(textFieldGenero);

		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(Textos.BORDA_TAGS_GERAIS));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 0, 10);
		this.add(labelArtista, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(textFieldArtista, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(labelAlbum, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(textFieldAlbum, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(labelAno, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(textFieldAno, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.NORTH;
		this.add(labelGenero, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		this.add(textFieldGenero, gbc);
	}
	
	public void updateValues(String artista, String album, String ano, String genero){
		textFieldArtista.setText(artista);
		textFieldAlbum.setText(album);
		textFieldAno.setText(ano);
		textFieldGenero.setText(genero);
	}
	
	public JTextField getTextFieldArtista() {
		return textFieldArtista;
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
