package view.paineis;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.ConstantesUI;
import view.listener.HabilitarComponentesListener;
import view.progress.ProgressSave;
import Facade.Facade;
/**
 * 
 * @author FrankJunior
 *
 */

@SuppressWarnings("serial")
public class PainelBotoes extends JPanel implements HabilitarComponentesListener {

	private GridBagConstraints gbc;
	private Facade facade;
	private static JButton botaoSalvar;
	private static JButton botaoNome2Tag;

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
		botaoSalvar = new JButton(ConstantesUI.BOTAO_SALVAR);
		botaoNome2Tag = new JButton(ConstantesUI.BOTAO_NOME2TAG);
		habilitarComponentes(false);
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
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				botaoSalvar.setEnabled(false);
				new ProgressSave().start();
				botaoSalvar.setEnabled(true);
				setCursor(null);
			}
		});
		
		botaoNome2Tag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				facade.nome2Tags();
			}
		});
	}
	
	@Override
	public void habilitarComponentes(boolean habilitar){
		botaoSalvar.setEnabled(habilitar);
		botaoNome2Tag.setEnabled(habilitar);
	}

}
