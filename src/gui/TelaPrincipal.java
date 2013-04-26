package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class TelaPrincipal extends JFrame {
	
	private GridBagConstraints gbc;
	private JPanel painelDireita;
	
	public TelaPrincipal() {
		gbc = new GridBagConstraints();
		initframe();
	}
	
	private void initframe(){
		//centralizando o Frame
		this.setSize(900, 600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) (((dimension.getWidth() / 2) - (this.getWidth() / 2)));
	    int y = (int) (((dimension.getHeight() / 2) - (this.getHeight() / 2)));
	    this.setLocation(x, y);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(new BorderLayout());
	    this.setTitle("Music Organizer");
	    
	    //Look and Feel
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	      } catch (ClassNotFoundException e) {
	    	  System.out.println(e.getMessage());
	      } catch (InstantiationException e) {
	    	  System.out.println(e.getMessage());
	      } catch (IllegalAccessException e) {
	    	  System.out.println(e.getMessage());
	      } catch (UnsupportedLookAndFeelException e) {
	    	  System.out.println(e.getMessage());
	      }SwingUtilities.updateComponentTreeUI(this);

		// Barra de Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
	      
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
	      
	      //Set up do layout
	      JPanel painelCentral = new JPanel();
	      painelCentral.setLayout(new BorderLayout());
	      
	      JPanel painelNorte = new JPanel();
	      painelNorte.setLayout(new GridLayout(0, 2));
	      painelCentral.add(painelNorte, BorderLayout.NORTH);
	      
	      JPanel painelEsquerda = new JPanel();
	      painelEsquerda.setLayout(new BorderLayout());
	      
	      JPanel painelTagsGerais = new JPanel();
	      painelTagsGerais.setLayout(new GridBagLayout());
	      gbc.gridx = 0;
	      gbc.gridy = 0;
	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(10, 10, 0, 10);
	      painelTagsGerais.add(labelArtista,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 0;
	      painelTagsGerais.add(TextFieldArtista,gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 1;
	      painelTagsGerais.add(labelAlbum,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 1;
	      painelTagsGerais.add(TextFieldAlbum,gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 2;
	      painelTagsGerais.add(labelAno,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 2;
	      painelTagsGerais.add(TextFieldAno,gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 3;
	      gbc.anchor = GridBagConstraints.NORTH;
	      painelTagsGerais.add(labelGenero,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 3;
	      gbc.weighty = 1;
	      gbc.weightx = 1;
	      gbc.anchor = GridBagConstraints.NORTHWEST;
	      painelTagsGerais.add(TextFieldGenero,gbc);
	      painelEsquerda.add(painelTagsGerais, BorderLayout.CENTER);
	      painelNorte.add(painelEsquerda);
	      
	      painelDireita = new JPanel();
	      painelDireita.setLayout(new BorderLayout());
	      painelNorte.add(painelDireita);
	      
	      JPanel painelSul = new JPanel();
	      painelSul.setLayout(new BorderLayout());
	      painelCentral.add(painelSul,BorderLayout.CENTER);
	      
	      JPanel painelFaixas = new JPanel();
	      painelFaixas.setBorder(BorderFactory.createTitledBorder("Musicas"));
	      painelFaixas.setLayout(new GridBagLayout());
	      painelSul.add(painelFaixas, BorderLayout.NORTH);
	      
	      JPanel painelBotoes = new JPanel();
//	      painelBotoes.setBackground(new Color(0,255,0));
	      painelBotoes.setLayout(new GridBagLayout());
	      JButton botaoSalvar = new JButton("Salvar");
	      JButton botaoNome2Tag = new JButton("Nome > Tag");
	      gbc.gridx = 0;
	      gbc.gridy = 0;
	      gbc.weightx = 0;
	      gbc.weighty = 0;
	      gbc.anchor = GridBagConstraints.NORTHEAST;
	      painelBotoes.add(botaoSalvar,gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 1;
	      gbc.weighty = 1;
	      painelBotoes.add(botaoNome2Tag,gbc);
	      painelDireita.add(painelBotoes,BorderLayout.EAST);
	      
	      Image imagemOriginal = null;
		try {
			imagemOriginal = ImageIO.read(new File("sample/outras-coisas.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	      Image imagem = getScaledImage(imagemOriginal, 150, 150);
	      
	      Box boxRadios = Box.createVerticalBox();
	      ButtonGroup grupoRadios = new ButtonGroup();
	      JRadioButton radioURL = new JRadioButton("pegar da URL",true);
	      JRadioButton radioArquivo = new JRadioButton("pegar de um arquivo",false);
	      grupoRadios.add(radioURL);
	      grupoRadios.add(radioArquivo);
	      boxRadios.add(radioURL);
	      boxRadios.add(radioArquivo);
	      
	      JPanel painelImagem = new JPanel();
	      painelImagem.setBorder(BorderFactory.createTitledBorder("Imagem"));
	      painelImagem.setLayout(new BorderLayout());
	      painelImagem.add(new JLabel(new ImageIcon(imagem)),BorderLayout.CENTER);
	      painelImagem.add(boxRadios,BorderLayout.SOUTH);
	      painelEsquerda.add(painelImagem,BorderLayout.WEST);
	      
	      final JPanel painelSelecaoImagemArquivo = new JPanel();
	      painelSelecaoImagemArquivo.setBorder(BorderFactory.createTitledBorder("Selecione a imagem"));
	      painelSelecaoImagemArquivo.setLayout(new GridBagLayout());
	      JTextField textFieldSelecaoImagem = new JTextField(15);
	      final JLabel labelSelecaoImagem = new JLabel("Informe a URL da imagem");
	      final JButton botaoSelecaoImagem = new JButton("OK");
	      gbc.gridx = 0;
	      gbc.gridy = 0;
	      gbc.weightx = 0;
	      gbc.weighty = 0;
	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(0, 0, 10, 5);
	      painelSelecaoImagemArquivo.add(labelSelecaoImagem, gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 1;
	      gbc.insets = new Insets(0, 0, 50, 5);
	      painelSelecaoImagemArquivo.add(textFieldSelecaoImagem, gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 1;
	      painelSelecaoImagemArquivo.add(botaoSelecaoImagem, gbc);
	      painelDireita.add(painelSelecaoImagemArquivo, BorderLayout.CENTER);
	      
	      radioURL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelSelecaoImagem.setText("Informe a URL da imagem");
				botaoSelecaoImagem.setText("Ok");
			}
		});
	      
	      radioArquivo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					labelSelecaoImagem.setText("Selecione o arquivo da imagem");
					botaoSelecaoImagem.setText("Procurar");
				}
			});
	    	  
	      ArrayList<JLabel> labels = new ArrayList<JLabel>();
	      ArrayList<JTextField> textFieldsNumero = new ArrayList<JTextField>();
	      ArrayList<JTextField> textFieldsFaixas = new ArrayList<JTextField>();
	      
		for (int i = 0; i < 20 ; i++) {

			labels.add(new JLabel());
			textFieldsNumero.add(new JTextField(3));
			textFieldsFaixas.add(new JTextField(15));

			JLabel labelFaixa1 = labels.get(i);
			labelFaixa1.setText("A Cor Do Som - Transe Total - 02 - Palco.mp3 --"+i);

			JTextField textFieldNumero = textFieldsNumero.get(i);
			textFieldNumero.setText("N"+i);
			JTextField textFieldFaixa = textFieldsFaixas.get(i);
			textFieldFaixa.setText("Tag"+i);

			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(15, 10, 0, 5);
			painelFaixas.add(labelFaixa1, gbc);
			gbc.gridx++;
			gbc.insets = new Insets(10, 0, 10, 5);
			painelFaixas.add(textFieldNumero, gbc);
			gbc.gridx++;
			gbc.weighty = 1;
			gbc.weightx = 1;
			painelFaixas.add(textFieldFaixa, gbc);

		}
	      
	    this.setJMenuBar(menuBar);
	    JScrollPane scrollPane = new JScrollPane(painelCentral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.add(scrollPane, BorderLayout.CENTER);
	    
	    this.setVisible(true);
	}

	private Image getScaledImage(Image imagemOriginal, int largura, int altura) {
		  BufferedImage resizedImg = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(imagemOriginal, 0, 0,largura, altura, null);
		    g2.dispose();
		    return resizedImg;
	}
	
	
}
