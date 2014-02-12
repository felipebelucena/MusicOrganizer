package view.paineis;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.ConstantesUI;
import view.listener.HabilitarComponentesListener;
import view.listener.KeyAdapterNumbersOnly;
import view.listener.UpdateTagsGeraisListener;
/**
 * 
 * @author FrankJunior
 *
 */
@SuppressWarnings("serial")
public class PainelTagsGeraisDoubleDisc extends JPanel implements HabilitarComponentesListener, UpdateTagsGeraisListener {

	private GridBagConstraints gbc;
	private JTextField textFieldArtista;
	private JTextField textFieldAlbum;
	private JTextField textFieldAno;
	private JTextField textFieldGenero;
	private JTextField textFieldDiscoNumero;
	private JTextField textFieldDiscoTotal;
	
	private JLabel labelArtista;
	private JLabel labelAlbum;
	private JLabel labelAno;
	private JLabel labelGenero;
	private JLabel labelDisco1;
	private JLabel labelDisco2;
	
	private boolean habilitarComponentes = false;
	private static PainelTagsGeraisDoubleDisc instace;

	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	private PainelTagsGeraisDoubleDisc() {
		gbc = new GridBagConstraints();
		initComponents();
	}
	
	private PainelTagsGeraisDoubleDisc(boolean habilitarComponentes) {
		gbc = new GridBagConstraints();
		this.habilitarComponentes = habilitarComponentes;
		initComponents();
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Singleton
	 * ---------------------------------------------------------------------
	 */
	public static PainelTagsGeraisDoubleDisc getInstace(){
		if (instace == null) {
			instace = new PainelTagsGeraisDoubleDisc();
		}
		return instace;
	}
	
	public static PainelTagsGeraisDoubleDisc getInstace(boolean habilitarComponentes){
		if (instace == null) {
			instace = new PainelTagsGeraisDoubleDisc(habilitarComponentes);
		}
		return instace;
	}

	/**
	 * Inicializa os componentes da UI do PainelTagsgerais
	 */
	private void initComponents() {

		labelArtista = new JLabel(ConstantesUI.LABEL_ARTISTA);
		labelAlbum = new JLabel(ConstantesUI.LABEL_ALBUM);
		labelAno = new JLabel(ConstantesUI.LABEL_ANO);
		labelGenero = new JLabel(ConstantesUI.LABEL_GENERO);
		labelDisco1 = new JLabel("Disco:");
		labelDisco2 = new JLabel("De:");
		

		textFieldArtista = new JTextField(10);
		textFieldAlbum = new JTextField(10);
		textFieldAno = new JTextField(10);
		textFieldGenero = new JTextField(10);
		textFieldDiscoNumero = new JTextField(2);
		textFieldDiscoNumero.addKeyListener(new KeyAdapterNumbersOnly());
		textFieldDiscoTotal = new JTextField(2);
		textFieldDiscoTotal.addKeyListener(new KeyAdapterNumbersOnly());

		labelArtista.setLabelFor(textFieldArtista);
		labelAlbum.setLabelFor(textFieldAlbum);
		labelAno.setLabelFor(textFieldAno);
		labelGenero.setLabelFor(textFieldGenero);
		labelDisco1.setLabelFor(textFieldDiscoNumero);
		labelDisco2.setLabelFor(textFieldDiscoTotal);

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
		gbc.gridwidth = 3;
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
		this.add(labelGenero, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(textFieldGenero, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(10, 10, 0, 0);
		gbc.anchor = GridBagConstraints.WEST;
		this.add(labelDisco1, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(10, 10, 0, 12);
		this.add(textFieldDiscoNumero, gbc);
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.insets = new Insets(10, 10, 0, 0);
		this.add(labelDisco2, gbc);
		gbc.gridx = 3;
		gbc.gridy = 4;
		this.add(textFieldDiscoTotal, gbc);
	}
	
	/**
	 * Atualiza os JTextFields do PainelTagsGerais, baseado nas Strings com nomes das Tags gerais
	 * @param artista
	 * @param album
	 * @param ano
	 * @param genero
	 * @param discoNumero
	 * @param discoTotal
	 */
	@Override
	public void updateTagsGerais(String artista, String album, String ano, String genero, int discoNumero, int discoTotal){
		textFieldArtista.setText(artista);
		textFieldAlbum.setText(album);
		textFieldAno.setText(ano);
		textFieldGenero.setText(genero);
		textFieldDiscoNumero.setText(Integer.toString(discoNumero));
		textFieldDiscoTotal.setText(Integer.toString(discoTotal));
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
		labelDisco1.setEnabled(habilitar);
		textFieldDiscoNumero.setEnabled(habilitar);
		labelDisco2.setEnabled(habilitar);
		textFieldDiscoTotal.setEnabled(habilitar);
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

	public JTextField getTextFieldDiscoNumero() {
		return textFieldDiscoNumero;
	}

	public JTextField getTextFieldDiscoTotal() {
		return textFieldDiscoTotal;
	}
	
}
