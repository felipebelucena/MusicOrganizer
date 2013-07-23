package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ui.dialog.DialogOpenImage;
import ui.listener.HabilitarComponentesListener;
import util.ConstantesUI;

import Base.TipoBotaoImagem;
import Facade.Facade;

@SuppressWarnings("serial")
public class PainelSelecaoImagem extends JPanel implements HabilitarComponentesListener {

	private GridBagConstraints gbc;
	private JLabel labelSelecaoImagem;
	private JButton botaoSelecaoImagem;
	private JTextField textFieldSelecaoImagem;
	private JRadioButton radioURL;
	private JRadioButton radioArquivo;
	private PainelImagem painelImagem;
	private Facade facade;

	public PainelSelecaoImagem(PainelImagem painelImagem) {
		this.painelImagem = painelImagem;
		gbc = new GridBagConstraints();
		facade = Facade.getInstace();
		initComponents();
	}

	/**
	 * inicializa componentes visuais
	 */
	private void initComponents() {
		this.setBorder(BorderFactory.createTitledBorder(ConstantesUI.BORDA_SELECIONE_UM_IMAGEM));
		this.setLayout(new GridBagLayout());
		textFieldSelecaoImagem = new JTextField(15);
		labelSelecaoImagem = new JLabel(ConstantesUI.INFORME_URL_IMAGEM);
		botaoSelecaoImagem = new JButton(ConstantesUI.OK);
		Box boxRadios = Box.createVerticalBox();
		ButtonGroup grupoRadios = new ButtonGroup();
		radioURL = new JRadioButton(ConstantesUI.RADIOBUTTON_PEGAR_DA_URL,true);
		radioArquivo = new JRadioButton(ConstantesUI.RADIOBUTTON_PEGAR_DE_ARQUIVO,false);
		habilitarComponentes(false);
		grupoRadios.add(radioURL);
		grupoRadios.add(radioArquivo);
		boxRadios.add(radioURL);
		boxRadios.add(radioArquivo);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 5, 5);
		this.add(labelSelecaoImagem, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(textFieldSelecaoImagem, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(botaoSelecaoImagem, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(boxRadios, gbc);
		
		botaoSelecaoImagem.addActionListener(new ActionListener() {
			byte[] imagem = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(radioURL.isSelected()){
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					String url = textFieldSelecaoImagem.getText();
					imagem = facade.loadImage(url, TipoBotaoImagem.URL);
					setCursor(null);
					if(imagem != null){
						painelImagem.updateImage(imagem);
					}
				}else if(radioArquivo.isSelected()){
					if(ConstantesUI.STRING_VAZIA.equals(textFieldSelecaoImagem.getText())){
						new DialogOpenImage(textFieldSelecaoImagem);
					}
					String url = textFieldSelecaoImagem.getText();
					imagem = facade.loadImage(url, TipoBotaoImagem.ARQUIVO);
					if(imagem != null){
						painelImagem.updateImage(imagem);
					}else{
						textFieldSelecaoImagem.setText(ConstantesUI.STRING_VAZIA);
					}
				}
			}
		});
		
		radioURL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelSelecaoImagem.setText(ConstantesUI.INFORME_URL_IMAGEM);
				botaoSelecaoImagem.setText(ConstantesUI.OK);
				textFieldSelecaoImagem.setText(ConstantesUI.STRING_VAZIA);
				textFieldSelecaoImagem.setEnabled(true);
			}
		});
		
		radioArquivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelSelecaoImagem.setText(ConstantesUI.SELECIONE_ARQUIVO_IMAGEM);
				botaoSelecaoImagem.setText(ConstantesUI.PROCURAR);
				textFieldSelecaoImagem.setText(ConstantesUI.STRING_VAZIA);
				textFieldSelecaoImagem.setEnabled(false);
			}
		});
	}
	/**
	 * Método para habilitar e desabilitar todos os componentes do <code>PainelSelececaoImagem</code>. 
	 * Habilita se já tiver carregado um disco, e desabilita se nao tiver nenhum disco carregado
	 * @param boolean
	 */
	@Override
	public void habilitarComponentes(boolean habilitar){
		textFieldSelecaoImagem.setEnabled(habilitar);
		labelSelecaoImagem.setEnabled(habilitar);
		botaoSelecaoImagem.setEnabled(habilitar);
		botaoSelecaoImagem.setEnabled(habilitar);
		radioURL.setEnabled(habilitar);
		radioArquivo.setEnabled(habilitar);
	}

}
