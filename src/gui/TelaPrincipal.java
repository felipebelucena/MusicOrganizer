package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	public TelaPrincipal() {
		initframe();
	}

	/**
	 * Inicializa componentes visuais
	 */
	private void initframe() {
		// centralizando o Frame
		this.setSize(ConstantesUI.LARGURA_TELA, ConstantesUI.ALTURA_TELA);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (((dimension.getWidth() / 2) - (this.getWidth() / 2)));
		int y = (int) (((dimension.getHeight() / 2) - (this.getHeight() / 2)));
		this.setLocation(x, y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle(ConstantesUI.TITULO);

		// Barra de Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu(ConstantesUI.MENU_FILE);
		JMenuItem menuItemAbrir = new JMenuItem(ConstantesUI.MENU_ITEM_OPEN);
		menuFile.add(menuItemAbrir);
		menuBar.add(menuFile);

		// Set up do layout
		JPanel painelCentral = new JPanel();
		painelCentral.setLayout(new BorderLayout());

		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new GridLayout(0, 2));
		painelCentral.add(painelNorte, BorderLayout.NORTH);

		JPanel painelEsquerda = new JPanel();
		painelEsquerda.setLayout(new BorderLayout());

		final PainelTagsGerais painelTagsGerais = new PainelTagsGerais();
		painelEsquerda.add(painelTagsGerais, BorderLayout.CENTER);
		painelNorte.add(painelEsquerda);

		JPanel painelDireita = new JPanel();
		painelDireita.setLayout(new BorderLayout());
		painelNorte.add(painelDireita);

		JPanel painelSul = new JPanel();
		painelSul.setLayout(new BorderLayout());
		painelCentral.add(painelSul, BorderLayout.CENTER);

		final PainelFaixas painelFaixas = new PainelFaixas();
		painelSul.add(painelFaixas, BorderLayout.NORTH);

		PainelBotoes painelBotoes = new PainelBotoes();
		painelDireita.add(painelBotoes, BorderLayout.EAST);

		final PainelImagem painelImagem = new PainelImagem();
		painelEsquerda.add(painelImagem, BorderLayout.WEST);

		final PainelSelecaoImagem painelSelecaoImagem = new PainelSelecaoImagem(painelImagem);
		painelDireita.add(painelSelecaoImagem, BorderLayout.CENTER);

		menuItemAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogOpenDisco(painelTagsGerais, painelFaixas, painelImagem, painelSelecaoImagem);
			}
		});
		
		this.setJMenuBar(menuBar);
		JScrollPane scrollPane = new JScrollPane(painelCentral,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane, BorderLayout.CENTER);

		this.setVisible(true);
	}

}
