package view.paineis;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.TipoBotaoImagem;

import util.ConstantesUI;
import view.listener.Atualizador;
import view.listener.HabilitarComponentesListener;
import view.progress.ProgressLoadImage;

/**
 * 
 * @author FrankJunior
 * 
 */
@SuppressWarnings("serial")
public class PainelSelecaoImagem extends JPanel implements
		HabilitarComponentesListener {

	private GridBagConstraints gbc;
	private JLabel labelSelecaoImagem;
	private JButton botaoSelecaoImagem;
	private JTextField textFieldSelecaoImagem;
	private JRadioButton radioURL;
	private JRadioButton radioArquivo;
	private Atualizador atualizador;

	public PainelSelecaoImagem(Atualizador atualizador) {
		this.atualizador = atualizador;
		gbc = new GridBagConstraints();
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
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(radioURL.isSelected()){
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					String url = textFieldSelecaoImagem.getText();
					ProgressLoadImage progressLoadImage = new ProgressLoadImage(url, TipoBotaoImagem.URL, atualizador);
					progressLoadImage.start();
					setCursor(null);
				}else if(radioArquivo.isSelected()){
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					ProgressLoadImage progressLoadImage = new ProgressLoadImage(TipoBotaoImagem.ARQUIVO, atualizador, textFieldSelecaoImagem);
					progressLoadImage.start();
					setCursor(null);
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
	 * Método para habilitar e desabilitar todos os componentes do
	 * <code>PainelSelececaoImagem</code>. Habilita se já tiver carregado um
	 * disco, e desabilita se nao tiver nenhum disco carregado
	 * 
	 * @param boolean
	 */
	@Override
	public void habilitarComponentes(boolean habilitar) {
		textFieldSelecaoImagem.setEnabled(habilitar);
		labelSelecaoImagem.setEnabled(habilitar);
		botaoSelecaoImagem.setEnabled(habilitar);
		botaoSelecaoImagem.setEnabled(habilitar);
		radioURL.setEnabled(habilitar);
		radioArquivo.setEnabled(habilitar);
	}

}
