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
	      painelDireita.setLayout(null);
	      painelNorte.add(painelDireita);
	      
	      JPanel painelSul = new JPanel();
	      painelSul.setLayout(new BorderLayout());
	      painelCentral.add(painelSul,BorderLayout.CENTER);
	      
	      JPanel painelFaixas = new JPanel();
	      painelFaixas.setLayout(new GridBagLayout());
	      painelSul.add(painelFaixas, BorderLayout.NORTH);
	      
	      
	      ArrayList<JLabel> labels = new ArrayList<JLabel>();
	      ArrayList<JTextField> textFieldsNumero = new ArrayList<JTextField>();
	      ArrayList<JTextField> textFieldsFaixas = new ArrayList<JTextField>();
	      
		for (int i = 0; i <1 ; i++) {

			labels.add(new JLabel());
			textFieldsNumero.add(new JTextField(3));
			textFieldsFaixas.add(new JTextField(10));

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
	
	
}
