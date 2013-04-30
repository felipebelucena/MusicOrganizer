package gui;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Facade.Facade;

public class PainelImagem extends JPanel {

	private GridBagConstraints gbc;
	private JRadioButton radioURL;
	private JRadioButton radioArquivo;
	private Image imagemOriginal = null;
	private Facade facade;
	
	public PainelImagem() {
		gbc = new GridBagConstraints();
		facade = Facade.getInstace();
		initComponents();
	}

	private void initComponents(){
		
		Image imagem = getScaledImage(imagemOriginal, 150, 150);
		Box boxRadios = Box.createVerticalBox();
		ButtonGroup grupoRadios = new ButtonGroup();
		radioURL = new JRadioButton("pegar da URL",true);
		radioArquivo = new JRadioButton("pegar de um arquivo",false);
		grupoRadios.add(radioURL);
		grupoRadios.add(radioArquivo);
		boxRadios.add(radioURL);
		boxRadios.add(radioArquivo);
		
		this.setBorder(BorderFactory.createTitledBorder("Imagem"));
		this.setLayout(new BorderLayout());
		this.add(new JLabel(new ImageIcon(imagem)),BorderLayout.CENTER);
		this.add(boxRadios,BorderLayout.SOUTH);
	}
	
	public void updateValues(byte[] image) {
		
		this.removeAll();
		try {
			if (image != null) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(byteArrayInputStream);
			imagemOriginal = ImageIO.read(imageInputStream);
			initComponents();
			}else{
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
	}
	
	private Image getScaledImage(Image imagemOriginal, int largura, int altura) {
		  BufferedImage resizedImg = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(imagemOriginal, 0, 0,largura, altura, null);
		    g2.dispose();
		    return resizedImg;
	}
	
	public JRadioButton getRadioURL() {
		return radioURL;
	}

	public JRadioButton getRadioArquivo() {
		return radioArquivo;
	}
	
}
