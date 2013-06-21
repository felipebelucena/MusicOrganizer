package Aplicacao;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import gui.TelaPrincipal;

public class Main {
	
	private static final String LOOK_AND_FEEL = "Nimbus";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
						if(LOOK_AND_FEEL.equals(info.getName())){
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
						//Caso queira setar uma fonte padrao
//						UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Segoei UI", Font.TRUETYPE_FONT, 12));
					}
					new TelaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
