package app;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import model.LookAndFeelTipos;
import view.TelaPrincipal;

/**
 * 
 * @author FrankJunior
 * 
 */

public class MusicOrganizer {

	/**
	 * Método Main da Aplicação
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setLookAndFeel(LookAndFeelTipos.NIMBUS);
				// linha mágica para retirar o log do JaudioTagger
				Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
				new TelaPrincipal();
			}
		});
	}

	/**
	 * Método para setar o LookAndFeel
	 * 
	 * @param lookAndFeel
	 */
	private static void setLookAndFeel(LookAndFeelTipos lookAndFeel) {
		try {
			switch (lookAndFeel) {
			case NIMBUS:
				installLookAndFeel(lookAndFeel);
				break;
			case METAL:
				installLookAndFeel(lookAndFeel);
				break;
			case MOTIF:
				installLookAndFeel(lookAndFeel);
				break;
			case MAC:
				installLookAndFeel(lookAndFeel);
				break;
			case GTK:
				installLookAndFeel(lookAndFeel);
				break;
			case SYSTEM:
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Seta o LookAndFeel, baseado nos LookAndFeel instalados no S.O
	 * 
	 * @param lookAndFeel
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 */
	private static void installLookAndFeel(LookAndFeelTipos lookAndFeel)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (lookAndFeel.getValor().equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
			// Caso queira setar uma fonte padrao
			// UIManager.getLookAndFeelDefaults().put("defaultFont", new
			// Font("Segoei UI", Font.TRUETYPE_FONT, 12));
		}
	}

}
