package ui.paineis;

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
/**
 * 
 * @author FrankJunior
 *
 */
@SuppressWarnings("serial")
public class PainelTagsGerais extends JPanel implements HabilitarComponentesListener, UpdateTagsGeraisListener {

	private GridBagConstraints gbc;
	private JTextField textFieldArtista;
	private JTextField textFieldAlbum;
	private JTextField textFieldAno;
	private JTextField textFieldGenero;
	
	private JLabel labelArtista;
	private JLabel labelAlbum;
	private JLabel labelAno;
	private JLabel labelGenero;
	
	private boolean habilitarComponentes = false;
	private static PainelTagsGerais instace;

	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	private PainelTagsGerais() {
		gbc = new GridBagConstraints();
		initComponents();
	}
	
	private PainelTagsGerais(boolean habilitarComponentes) {
		gbc = new GridBagConstraints();
		this.habilitarComponentes = habilitarComponentes;
		initComponents();
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Singleton
	 * ---------------------------------------------------------------------
	 */
	public static PainelTagsGerais getInstace(){
		if (instace == null) {
			instace = new PainelTagsGerais();
		}
		return instace;
	}
	
	public static PainelTagsGerais getInstace(boolean habilitarComponentes){
		if (instace == null) {
			instace = new PainelTagsGerais(habilitarComponentes);
		}
		return instace;
	}

	/**
	 * Inicializa os componentes da UI do PainelTagsgerais
	 */
	private void initComponents() {

		labelArtista = new JLabel(ConstantesUI.LABEL_ARTISTA);
		labelAlbum = new JLabel(ConstantesUI.LABEL_ARLBUM);
		labelAno = new JLabel(ConstantesUI.LABEL_ANO);
		labelGenero = new JLabel(ConstantesUI.LABEL_GENERO);

		textFieldArtista = new JTextField(10);
		textFieldAlbum = new JTextField(10);
		textFieldAno = new JTextField(10);
		textFieldGenero = new JTextField(10);

		labelArtista.setLabelFor(textFieldArtista);
		labelAlbum.setLabelFor(textFieldAlbum);
		labelAno.setLabelFor(textFieldAno);
		labelGenero.setLabelFor(textFieldGenero);

		habilitarComponentes(habilitarComponentes);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(ConstantesUI.BORDA_TAGS_GERAIS));

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
	
	/**
	 * Atualiza os JTextFields do PainelTagsGerais, baseado nas Strings com nomes das Tags gerais
	 * @param artista
	 * @param album
	 * @param ano
	 * @param genero
	 */
	@Override
	public void updateTagsGerais(String artista, String album, String ano, String genero){
		textFieldArtista.setText(artista);
		textFieldAlbum.setText(album);
		textFieldAno.setText(ano);
		textFieldGenero.setText(genero);
	}
	
	@Override
	public void habilitarComponentes(boolean habilitar){
		labelArtista.setEnabled(habilitar);
		labelAlbum.setEnabled(habilitar);
		labelAno.setEnabled(habilitar);
		labelGenero.setEnabled(habilitar);
		textFieldArtista.setEnabled(habilitar);
		textFieldAlbum.setEnabled(habilitar);
		textFieldAno.setEnabled(habilitar);
		textFieldGenero.setEnabled(habilitar);
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
