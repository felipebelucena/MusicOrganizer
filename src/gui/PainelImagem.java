package gui;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PainelImagem extends JPanel {

	private Image imagemOriginal = null;
	
	public PainelImagem() {
		initComponents();
	}

	/**
	 * Inicializa componentes visuais
	 */
	private void initComponents(){
		
		Image imagem = getScaledImage(imagemOriginal, 150, 150);
		this.setBorder(BorderFactory.createTitledBorder(ConstantesUI.BORDA_IMAGEM));
		this.setLayout(new BorderLayout());
		this.add(new JLabel(new ImageIcon(imagem)),BorderLayout.CENTER);
		this.repaint();
		this.validate();
	}
	
	/**
	 * Método para atualizar a imagem
	 * @param image
	 */
	//Esse método está aqui na UI, e nao no controller, pq esse método chama o initComponent dentro dele
	public void updateValues(byte[] image) {
		
		this.removeAll();
		try {
			if (image != null) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(byteArrayInputStream);
			imagemOriginal = ImageIO.read(imageInputStream);
			initComponents();
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	      
	}
	
	/**
	 * Método para redimensionar a imagem, indepenente do tamanho que ela venha
	 * @param imagemOriginal
	 * @param largura
	 * @param altura
	 * @return Imagem redimensionada
	 */
	private Image getScaledImage(Image imagemOriginal, int largura, int altura) {
		  BufferedImage resizedImg = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		    Graphics2D g2 = resizedImg.createGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2.drawImage(imagemOriginal, 0, 0,largura, altura, null);
		    g2.dispose();
		    return resizedImg;
	}
	
}
