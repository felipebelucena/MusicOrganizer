package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.InsetsUIResource;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

public class TelaPrincipal extends JFrame {
	
	private GridBagConstraints gbc;
	
	public TelaPrincipal() {
		gbc = new GridBagConstraints();
		initframe();
	}
	
	private void initframe(){
		//centralizando o Frame
		this.setSize(800, 600);
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
	      }
	      SwingUtilities.updateComponentTreeUI(this);
	      
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
	      painelCentral.setLayout(new GridLayout(2, 0));
	      
	      JPanel painelNorte = new JPanel();
	      painelNorte.setLayout(new GridLayout(0, 2));
	      painelCentral.add(painelNorte);
	      
	      JPanel painelEsquerda = new JPanel();
	      painelEsquerda.setLayout(new GridBagLayout());
	      gbc.gridx = 0;
	      gbc.gridy = 0;
	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(10, 10, 0, 10);
	      painelEsquerda.add(labelArtista,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 0;
	      painelEsquerda.add(TextFieldArtista,gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 1;
	      painelEsquerda.add(labelAlbum,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 1;
	      painelEsquerda.add(TextFieldAlbum,gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 2;
	      painelEsquerda.add(labelAno,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 2;
	      painelEsquerda.add(TextFieldAno,gbc);
	      gbc.gridx = 0;
	      gbc.gridy = 3;
	      gbc.anchor = GridBagConstraints.NORTH;
	      painelEsquerda.add(labelGenero,gbc);
	      gbc.gridx = 1;
	      gbc.gridy = 3;
	      gbc.weighty = 1;
	      gbc.weightx = 1;
	      gbc.anchor = GridBagConstraints.NORTHWEST;
	      painelEsquerda.add(TextFieldGenero,gbc);
	      painelNorte.add(painelEsquerda);
	      
	      JPanel painelDireita = new JPanel();
//	      painelDireita.setBackground(new Color(255, 255, 0));
	      painelDireita.setLayout(null);
	      painelNorte.add(painelDireita);
	      
	      JPanel painelSul = new JPanel();
	      painelSul.setBackground(new Color(0, 255, 0));
	      painelSul.setLayout(new GridBagLayout());
	      painelCentral.add(painelSul);
	      
	      ArrayList<JLabel> labels = new ArrayList<JLabel>();
	      ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	      
		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < 2; j++) {
				labels.add(new JLabel());
				textFields.add(new JTextField(3));
				textFields.add(new JTextField(10));
			}

			JLabel labelFaixa1 = labels.get(0);
			labelFaixa1.setText("nome01");
			JLabel labelFaixa2 = labels.get(1);
			labelFaixa2.setText("Faixa02");

			JTextField textFieldNumero1 = textFields.get(0);
			JTextField textFieldFaixa1 = textFields.get(1);
			JTextField textFieldNumero2 = textFields.get(2);
			JTextField textFieldFaixa2 = textFields.get(3);

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.insets = new Insets(10, 10, 0, 5);
			painelSul.add(labelFaixa1, gbc);
			gbc.gridy = 0;
			gbc.gridx = 1;
			gbc.insets = new Insets(10, 0, 0, 5);
			painelSul.add(textFieldNumero1, gbc);
			gbc.gridy = 0;
			gbc.gridx = 2;
			painelSul.add(textFieldFaixa1, gbc);
			gbc.gridy = 0;
			gbc.gridx = 3;
			gbc.insets = new Insets(10, 20, 0, 5);
			painelSul.add(labelFaixa2, gbc);
			gbc.gridy = 0;
			gbc.gridx = 4;
			gbc.insets = new Insets(10, 0, 0, 5);
			painelSul.add(textFieldNumero2, gbc);
			gbc.gridy = 0;
			gbc.gridx = 5;
			gbc.weighty = 1;
			gbc.weightx = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(10, 0, 0, 20);
			painelSul.add(textFieldFaixa2, gbc);

		}
	      
	    //Barra de Menu
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menuFile = new JMenu("File");
	    menuBar.add(menuFile);
	    
	    this.setJMenuBar(menuBar);
//	    this.add(painelCentral, BorderLayout.NORTH);
	    
	    JScrollPane scrollPane = new JScrollPane(painelCentral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    this.add(scrollPane, BorderLayout.CENTER);
	    
	    this.setVisible(true);
	}
	
	
}
