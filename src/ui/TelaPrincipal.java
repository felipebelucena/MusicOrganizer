package ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.dialog.DialogOpenDisco;
import ui.dialog.DialogSetMusicFolder;
import ui.dialog.PopUp;
import ui.listener.Atualizador;
import ui.listener.HabilitarComponentesListener;
import util.ConstantesUI;
import util.PropertiesFile;
import Base.TipoPopUp;
import Exception.PastaDeMusicaNaoExisteException;
import Exception.PastaDeMusicaVaziaException;
import Facade.Facade;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame implements
		HabilitarComponentesListener {

	private Facade facade;
	private static JMenuItem menuItemSalvar;
	private static JMenuItem menuItemNome2Tag;
	private Atualizador atualizador;

	public TelaPrincipal() {
		facade = Facade.getInstace();
		atualizador = new Atualizador();
		initframe();
	}

	/**
	 * Inicializa componentes visuais
	 */
	private void initframe() {
		/*
		 * ---------------------------------------------------------------------
		 * Centraliza o Frame
		 * ---------------------------------------------------------------------
		 */
		this.setSize(ConstantesUI.LARGURA_TELA, ConstantesUI.ALTURA_TELA);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (((dimension.getWidth() / 2) - (this.getWidth() / 2)));
		int y = (int) (((dimension.getHeight() / 2) - (this.getHeight() / 2)));
		this.setLocation(x, y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle(ConstantesUI.TITULO);

		/*
		 * ---------------------------------------------------------------------
		 * Barra de Menu
		 * ---------------------------------------------------------------------
		 */
		JMenuBar menuBar = new JMenuBar();

		JMenu menuFile = new JMenu(ConstantesUI.MENU_FILE);
		JMenuItem menuItemAbrir = new JMenuItem(ConstantesUI.MENU_ITEM_OPEN);
		menuItemAbrir.setAccelerator(ConstantesUI.CTRL_A);
		menuItemSalvar = new JMenuItem(ConstantesUI.BOTAO_SALVAR);
		menuItemSalvar.setAccelerator(ConstantesUI.CTRL_S);
		menuItemNome2Tag = new JMenuItem(ConstantesUI.BOTAO_NOME2TAG);
		menuItemNome2Tag.setAccelerator(ConstantesUI.CTRL_N);

		JMenu menuInfo = new JMenu(ConstantesUI.MENU_INFO);
		JMenuItem menuItemAbout = new JMenuItem(ConstantesUI.MENU_ITEM_ABOUT);
		menuItemAbout.setAccelerator(ConstantesUI.CTRL_I);

		final JMenu menuSettings = new JMenu(ConstantesUI.MENU_SETTINGS);
		JMenuItem menuItemSetMusicFolder = new JMenuItem(
				ConstantesUI.MENU_ITEM_SET_MUSIC_FOLDER);
		menuItemSetMusicFolder.setAccelerator(ConstantesUI.CTRL_1);

		JMenuItem menuItemSetLanguage = new JMenuItem(
				ConstantesUI.MENU_ITEM_SET_LANGUAGE);
		menuItemSetLanguage.setAccelerator(ConstantesUI.CTRL_2);
		JLabel idioma = new JLabel(ConstantesUI.ESPACO
				+ ConstantesUI.AINDA_NAO_IMPLEMENTADO);
		idioma.setForeground(ConstantesUI.COR_DESABILITADO);

		JMenuItem menuItemChangeLookAndFeel = new JMenuItem(
				ConstantesUI.MENU_ITEM_CHANGE_LOOKANDFEEL);
		menuItemChangeLookAndFeel.setAccelerator(ConstantesUI.CTRL_3);
		String lookAndFeel = ConstantesUI.ESPACO
				+ ConstantesUI.AINDA_NAO_IMPLEMENTADO;
		JLabel labelLookAndFeel = new JLabel(lookAndFeel);
		labelLookAndFeel.setForeground(ConstantesUI.COR_DESABILITADO);

		JMenuItem menuItemSetDiscType = new JMenuItem(
				ConstantesUI.MENU_ITEM_SET_DISC_TYPE);
		final JLabel labelDiscType = new JLabel(ConstantesUI.ESPACO
				+ PropertiesFile.getProperties(ConstantesUI.TIPO_DE_DISCO));
		labelDiscType.setForeground(ConstantesUI.COR_DESABILITADO);

		String musicFolder = PropertiesFile
				.getProperties(ConstantesUI.DIRETORIO_DE_MUSICA);
		final JLabel labelMusicFolder = new JLabel(ConstantesUI.ESPACO
				+ musicFolder);
		labelMusicFolder.setEnabled(true);

		habilitarComponentes(false);

		try {
			PropertiesFile.verifyMusicFolder(musicFolder);
			labelMusicFolder.setForeground(ConstantesUI.COR_DESABILITADO);
			menuSettings.setForeground(ConstantesUI.COR_PRETO);
		} catch (PastaDeMusicaNaoExisteException e) {
			labelMusicFolder.setForeground(ConstantesUI.COR_VERMELHO);
			menuSettings.setForeground(ConstantesUI.COR_VERMELHO);
		} catch (PastaDeMusicaVaziaException e) {
			labelMusicFolder.setForeground(ConstantesUI.COR_VERMELHO);
			menuSettings.setForeground(ConstantesUI.COR_VERMELHO);
		}

		menuFile.add(menuItemAbrir);
		menuFile.add(menuItemSalvar);
		menuFile.add(menuItemNome2Tag);

		menuSettings.add(menuItemSetMusicFolder);
		menuSettings.add(labelMusicFolder);
		menuSettings.addSeparator();

		menuSettings.add(menuItemSetLanguage);
		menuSettings.add(idioma);
		menuSettings.addSeparator();

		menuSettings.add(menuItemChangeLookAndFeel);
		menuSettings.add(labelLookAndFeel);
		menuSettings.addSeparator();

		menuSettings.add(menuItemSetDiscType);
		menuSettings.add(labelDiscType);

		menuInfo.add(menuItemAbout);

		menuBar.add(menuFile);
		menuBar.add(menuInfo);
		menuBar.add(menuSettings);

		/*
		 * ---------------------------------------------------------------------
		 * Setup do Layout
		 * ---------------------------------------------------------------------
		 */
		JPanel painelCentral = new JPanel();
		painelCentral.setLayout(new BorderLayout());

		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new GridLayout(0, 2));
		painelCentral.add(painelNorte, BorderLayout.NORTH);

		JPanel painelEsquerda = new JPanel();
		painelEsquerda.setLayout(new BorderLayout());

		final PainelTagsGerais painelTagsGerais = new PainelTagsGerais();
		painelNorte.add(painelEsquerda);
		atualizador.addHabilitarComponentesListener(painelTagsGerais);

		JPanel painelDireita = new JPanel();
		painelDireita.setLayout(new BorderLayout());
		painelNorte.add(painelDireita);

		JPanel painelSul = new JPanel();
		painelSul.setLayout(new BorderLayout());
		painelCentral.add(painelSul, BorderLayout.CENTER);

		final PainelFaixasVariousArtists painelFaixas = new PainelFaixasVariousArtists();
		painelSul.add(painelFaixas, BorderLayout.CENTER);
		atualizador.addUpdateFaixasListener(painelFaixas);

		final PainelBotoes painelBotoes = new PainelBotoes();
		painelDireita.add(painelBotoes, BorderLayout.EAST);
		atualizador.addHabilitarComponentesListener(painelBotoes);

		final PainelImagem painelImagem = new PainelImagem();
		painelEsquerda.add(painelImagem, BorderLayout.WEST);
		atualizador.addUpdateImageListener(painelImagem);

		final PainelSelecaoImagem painelSelecaoImagem = new PainelSelecaoImagem(
				painelImagem);
		atualizador.addHabilitarComponentesListener(painelSelecaoImagem);

		painelEsquerda.add(painelSelecaoImagem, BorderLayout.CENTER);
		painelDireita.add(painelTagsGerais, BorderLayout.CENTER);
		atualizador.addUpdateTagsGeraisListener(painelTagsGerais);

		menuItemAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizador.addHabilitarComponentesListener(TelaPrincipal.this);
				new DialogOpenDisco(atualizador);
			}
		});

		/*
		 * ---------------------------------------------------------------------
		 * Eventos
		 * ---------------------------------------------------------------------
		 */
		menuItemSetDiscType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] tiposDeDisco = { ConstantesUI.DISC_TYPE_DEFAULT,
						ConstantesUI.DISC_TYPE_DOUBLE,
						ConstantesUI.DISC_TYPE_TRIBUTES,
						ConstantesUI.DISC_TYPE_VA };
				String tipoDeDiscoSelecionado = (String) JOptionPane
						.showInputDialog(null, "Choose a disc type",
								"Disc Type", JOptionPane.PLAIN_MESSAGE, null,
								tiposDeDisco, ConstantesUI.DISC_TYPE_DEFAULT);
				PropertiesFile.setProperties(ConstantesUI.TIPO_DE_DISCO,
						tipoDeDiscoSelecionado);
				labelDiscType.setText(ConstantesUI.ESPACO
						+ PropertiesFile
								.getProperties(ConstantesUI.TIPO_DE_DISCO));
			}
		});

		/**
		 * Evento do menuItem set de Settings: music folder
		 */
		menuItemSetMusicFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogSetMusicFolder();
				labelMusicFolder.setForeground(ConstantesUI.COR_DESABILITADO);
				labelMusicFolder.setText(ConstantesUI.ESPACO
						+ PropertiesFile
								.getProperties(ConstantesUI.DIRETORIO_DE_MUSICA));
				menuSettings.setForeground(ConstantesUI.COR_PRETO);
			}
		});

		/**
		 * Evento do item de menu de Settings: Set Language
		 */
		menuItemSetLanguage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PopUp(ConstantesUI.AINDA_NAO_IMPLEMENTADO, TipoPopUp.INFO);
			}
		});

		/**
		 * Evento do item de menu de Settings: Set LookAndFeel
		 */
		menuItemChangeLookAndFeel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PopUp(ConstantesUI.AINDA_NAO_IMPLEMENTADO, TipoPopUp.INFO);
			}
		});

		/**
		 * Evento do item de menu About
		 */
		menuItemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PopUp(ConstantesUI.AINDA_NAO_IMPLEMENTADO, TipoPopUp.INFO);
			}
		});

		/**
		 * Evento do item de menu File: Salvar
		 */
		menuItemSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				menuItemSalvar.setEnabled(false);
				facade.salvar();
				menuItemSalvar.setEnabled(true);
				setCursor(null);
			}
		});

		/**
		 * Evento do item de menu File: Nome2Tag
		 */
		menuItemNome2Tag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				facade.nome2Tags();
			}
		});

		this.setJMenuBar(menuBar);
		// JScrollPane scrollPane = new JScrollPane(painelCentral,
		// JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(painelCentral, BorderLayout.CENTER);

		this.setVisible(true);
	}

	/**
	 * Habilitar componentes dessa tela
	 */
	@Override
	public void habilitarComponentes(boolean habilitar) {
		menuItemSalvar.setEnabled(habilitar);
		menuItemNome2Tag.setEnabled(habilitar);
	}

}
