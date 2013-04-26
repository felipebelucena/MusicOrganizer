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
	      
	      //Set up do layout
	      JPanel painelCentral = new JPanel();
	      painelCentral.setLayout(new BorderLayout());
	      
	      JPanel painelNorte = new JPanel();
	      painelNorte.setLayout(new GridLayout(0, 2));
	      painelCentral.add(painelNorte, BorderLayout.NORTH);
	      
	      JPanel painelEsquerda = new JPanel();
	      painelEsquerda.setLayout(new BorderLayout());
	      
	      PainelTagsGerais painelTagsGerais = new PainelTagsGerais();
	      painelEsquerda.add(painelTagsGerais, BorderLayout.CENTER);
	      painelNorte.add(painelEsquerda);
	      
	      JPanel painelDireita = new JPanel();
	      painelDireita.setLayout(new BorderLayout());
	      painelNorte.add(painelDireita);
	      
	      JPanel painelSul = new JPanel();
	      painelSul.setLayout(new BorderLayout());
	      painelCentral.add(painelSul,BorderLayout.CENTER);
	      
	      PainelFaixas painelFaixas = new PainelFaixas();
	      painelSul.add(painelFaixas, BorderLayout.NORTH);
	      
	      PainelBotoes painelBotoes = new PainelBotoes();
	      painelDireita.add(painelBotoes,BorderLayout.EAST);
	      
	      PainelImagem painelImagem = new PainelImagem();
	      painelEsquerda.add(painelImagem,BorderLayout.WEST);
	      
	      final PainelSelecaoImagem painelSelecaoImagem = new PainelSelecaoImagem();
	      painelDireita.add(painelSelecaoImagem, BorderLayout.CENTER);
	      
	      painelImagem.getRadioURL().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				painelSelecaoImagem.getLabelSelecaoImagem().setText("Informe a URL da imagem");
				painelSelecaoImagem.getBotaoSelecaoImagem().setText("Ok");
			}
		});
	      
	      painelImagem.getRadioArquivo().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					painelSelecaoImagem.getLabelSelecaoImagem().setText("Selecione o arquivo da imagem");
					painelSelecaoImagem.getBotaoSelecaoImagem().setText("Procurar");
				}
			});
	    	  
	      
	    this.setJMenuBar(menuBar);
	    JScrollPane scrollPane = new JScrollPane(painelCentral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.add(scrollPane, BorderLayout.CENTER);
	    
	    this.setVisible(true);
	}

}
