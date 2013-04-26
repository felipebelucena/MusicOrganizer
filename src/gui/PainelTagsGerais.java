package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelTagsGerais extends JPanel {

	private GridBagConstraints gbc;

	public PainelTagsGerais() {
		gbc = new GridBagConstraints();
		initComponents();
	}

	private void initComponents() {

		JLabel labelArtista = new JLabel("Artista");
		JLabel labelAlbum = new JLabel("Album");
		JLabel labelAno = new JLabel("Ano");
		JLabel labelGenero = new JLabel("Genero");

		JTextField TextFieldArtista = new JTextField(10);
		JTextField TextFieldAlbum = new JTextField(10);
		JTextField TextFieldAno = new JTextField(10);
		JTextField TextFieldGenero = new JTextField(10);

		labelArtista.setLabelFor(TextFieldArtista);
		labelAlbum.setLabelFor(TextFieldAlbum);
		labelAno.setLabelFor(TextFieldAno);
		labelGenero.setLabelFor(TextFieldGenero);

		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Tags Gerais"));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 0, 10);
		this.add(labelArtista, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(TextFieldArtista, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(labelAlbum, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(TextFieldAlbum, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(labelAno, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(TextFieldAno, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.NORTH;
		this.add(labelGenero, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		this.add(TextFieldGenero, gbc);
	}

}
