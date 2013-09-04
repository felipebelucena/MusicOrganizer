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
import ui.listener.UpdateFaixasListener;
import ui.listener.UpdateTagsGeraisListener;
import ui.paineis.PainelBotoes;
import ui.paineis.PainelFaixas;
import ui.paineis.PainelFaixasVariousArtists;
import ui.paineis.PainelImagem;
import ui.paineis.PainelSelecaoImagem;
import ui.paineis.PainelTagsGerais;
import ui.paineis.PainelTagsGeraisDoubleDisc;
import ui.paineis.PainelTagsGeraisVariousArtists;
import util.ConstantesUI;
import util.Logger;
import util.PropertiesFile;
import Base.TipoPopUp;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;
import Exception.PastaDeMusicaNaoExisteException;
import Exception.PastaDeMusicaVaziaException;
import Facade.Facade;

/**
 * 
 * @author FrankJunior
 *
 */
@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame implements
		HabilitarComponentesListener {

	private Facade facade;
	private static JMenuItem menuItemSalvar;
	private static JMenuItem menuItemNome2Tag;
	private Atualizador atualizador;
	private String tipoDeDisco;
	private JPanel painelTagsGerais;
	private JPanel painelFaixas;
	private JPanel painelDireita;
	private JPanel painelSul;
	private DialogOpenDisco dialogOpenDisco;

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

		tipoDeDisco = PropertiesFile.getTipoDeDisco();

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
		painelNorte.add(painelEsquerda);

		painelDireita = new JPanel();
		painelDireita.setLayout(new BorderLayout());
		painelNorte.add(painelDireita);

		painelSul = new JPanel();
		painelSul.setLayout(new BorderLayout());
		painelCentral.add(painelSul, BorderLayout.CENTER);

		updatePaineisFromTipoDeDisco();

		final PainelBotoes painelBotoes = new PainelBotoes();
		atualizador.addHabilitarComponentesListener(painelBotoes);

		final PainelImagem painelImagem = new PainelImagem();
		atualizador.addUpdateImageListener(painelImagem);

		final PainelSelecaoImagem painelSelecaoImagem = new PainelSelecaoImagem(
				atualizador);
		atualizador.addHabilitarComponentesListener(painelSelecaoImagem);

		painelDireita.add(painelBotoes, BorderLayout.EAST);
		painelSul.add(painelFaixas, BorderLayout.CENTER);
		painelEsquerda.add(painelImagem, BorderLayout.WEST);
		painelEsquerda.add(painelSelecaoImagem, BorderLayout.CENTER);
		painelDireita.add(painelTagsGerais, BorderLayout.CENTER);

		/*
		 * ---------------------------------------------------------------------
		 * Eventos
		 * ---------------------------------------------------------------------
		 */

		/**
		 * Evento do menuItem de File: Abrir
		 */
		menuItemAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizador.addHabilitarComponentesListener(TelaPrincipal.this);
				dialogOpenDisco = new DialogOpenDisco(atualizador);
			}
		});

		/**
		 * Evento do menuItem de Settings: set disc type
		 */
		menuItemSetDiscType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] tiposDeDisco = { ConstantesUI.DISC_TYPE_DEFAULT,
						ConstantesUI.DISC_TYPE_DOUBLE,
						ConstantesUI.DISC_TYPE_TRIBUTES,
						ConstantesUI.DISC_TYPE_VA };
				String tipoDeDiscoSelecionado = (String) JOptionPane
						.showInputDialog(null,
								ConstantesUI.DIALOG_TIPO_DE_DISCO,
								ConstantesUI.DIALOG_TIPO_DE_DISCO_TITULO,
								JOptionPane.PLAIN_MESSAGE, null, tiposDeDisco,
								PropertiesFile.getTipoDeDisco());
				PropertiesFile.setProperties(ConstantesUI.TIPO_DE_DISCO,
						tipoDeDiscoSelecionado);
				labelDiscType.setText(ConstantesUI.ESPACO
						+ PropertiesFile
								.getProperties(ConstantesUI.TIPO_DE_DISCO));

				painelSul.remove(painelFaixas);
				painelDireita.remove(painelTagsGerais);

				updatePaineisFromTipoDeDisco();

				painelDireita.add(painelTagsGerais, BorderLayout.CENTER);
				painelDireita.revalidate();
				painelDireita.repaint();
				painelSul.add(painelFaixas, BorderLayout.CENTER);
				painelSul.revalidate();
				painelSul.repaint();
				TelaPrincipal.this.revalidate();
				TelaPrincipal.this.repaint();
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
		this.add(painelCentral, BorderLayout.CENTER);

		this.setVisible(true);
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Métodos Auxiliares
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * Habilitar componentes dessa tela
	 */
	@Override
	public void habilitarComponentes(boolean habilitar) {
		menuItemSalvar.setEnabled(habilitar);
		menuItemNome2Tag.setEnabled(habilitar);
	}

	/**
	 * Atualiza os paines de tags gerais e paineFaixas, de acordo com o tipo de
	 * disco setado nas propriedades
	 */
	private void updatePaineisFromTipoDeDisco() {
		painelTagsGerais = null;
		painelFaixas = null;
		tipoDeDisco = PropertiesFile.getTipoDeDisco();
		if (tipoDeDisco.equals(ConstantesUI.DISC_TYPE_VA)) {
			try {
				// se essa lista for nula, cai no catch. Serve para verificar se algum disco já foi carregado
				dialogOpenDisco.getListFiles();
				painelFaixas = PainelFaixasVariousArtists.getInstace();
				painelTagsGerais = PainelTagsGeraisVariousArtists.getInstace(true);
				updateAtualizador(painelTagsGerais.getClass().getName(), painelFaixas.getClass().getName());
				try {
					Facade.getInstace().carregaMusicas(dialogOpenDisco.getListFiles(), atualizador, dialogOpenDisco.getListaTags());
				} catch (ListaNulaException e) {
					Logger.error(e.getMessage());
				} catch (ListaVaziaException e) {
					Logger.error(e.getMessage());
				}
			} catch (NullPointerException e) {
				painelFaixas = PainelFaixasVariousArtists.getInstace();
				painelTagsGerais = PainelTagsGeraisVariousArtists.getInstace();
				updateAtualizador(painelTagsGerais.getClass().getName(), painelFaixas.getClass().getName());
			}
		} else if (tipoDeDisco.equals(ConstantesUI.DISC_TYPE_DOUBLE)) {
			try {
				// se essa lista for nula, cai no catch. Serve para verificar se algum disco já foi carregado
				dialogOpenDisco.getListFiles();
				painelTagsGerais = PainelTagsGeraisDoubleDisc.getInstace(true);
				painelFaixas = PainelFaixas.getInstace();
				updateAtualizador(painelTagsGerais.getClass().getName(), painelFaixas.getClass().getName());
				try {
					Facade.getInstace().carregaMusicas(dialogOpenDisco.getListFiles(), atualizador, dialogOpenDisco.getListaTags());
				} catch (ListaNulaException e) {
					Logger.error(e.getMessage());
				} catch (ListaVaziaException e) {
					Logger.error(e.getMessage());
				}
			} catch (NullPointerException e) {
				painelTagsGerais = PainelTagsGeraisDoubleDisc.getInstace();
				painelFaixas = PainelFaixas.getInstace();
				updateAtualizador(painelTagsGerais.getClass().getName(), painelFaixas.getClass().getName());
			}
		} else {
			try {
				// se essa lista for nula, cai no catch. Serve para verificar se algum disco já foi carregado
				dialogOpenDisco.getListFiles();
				painelTagsGerais = PainelTagsGerais.getInstace(true);
				painelFaixas = PainelFaixas.getInstace();
				updateAtualizador(painelTagsGerais.getClass().getName(), painelFaixas.getClass().getName());
				try {
					Facade.getInstace().carregaMusicas(dialogOpenDisco.getListFiles(), atualizador, dialogOpenDisco.getListaTags());
				} catch (ListaNulaException e) {
					Logger.error(e.getMessage());
				} catch (ListaVaziaException e) {
					Logger.error(e.getMessage());
				}
			} catch (NullPointerException e) {
				painelTagsGerais = PainelTagsGerais.getInstace();
				painelFaixas = PainelFaixas.getInstace();
				updateAtualizador(painelTagsGerais.getClass().getName(), painelFaixas.getClass().getName());
			}
		}
	}

	/**
	 * Método chamado várias vezes, para atualizar o atualizador, de acordo com
	 * o tipo de disco carregado
	 * FIXME Esse método funciona, mas está feio. Deixar ele melhor depois
	 */
	private void updateAtualizador(String nomePainelTagsGerais, String nomePainelFaixas) {
		Class<?> classnamePainelTagsGerais = null;
		Class<?> classnamePainelFaixas = null;
		try {
			classnamePainelTagsGerais = Class.forName(nomePainelTagsGerais);
			classnamePainelFaixas = Class.forName(nomePainelFaixas);
		} catch (ClassNotFoundException e) {
			Logger.error(e.getMessage());
		}
		atualizador
				.addHabilitarComponentesListener((HabilitarComponentesListener) classnamePainelTagsGerais.cast(painelTagsGerais));
		atualizador
				.addUpdateTagsGeraisListener((UpdateTagsGeraisListener) classnamePainelTagsGerais.cast(painelTagsGerais));

		atualizador
				.addUpdateFaixasListener((UpdateFaixasListener) classnamePainelFaixas.cast(painelFaixas));
	}

}
