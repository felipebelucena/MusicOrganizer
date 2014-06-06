package model;
/**
 * 
 * @author FrankJunior
 *
 */

public enum LookAndFeelTipos {
    NIMBUS("Nimbus"),
    METAL("Metal"),
    MOTIF("CDE/Motif"),
    MAC("Mac OS X"),
    SYSTEM("System"),
    GTK("GTK");
 
    private String valor;
    
    LookAndFeelTipos(String valor) {
        this.valor = valor;
    }
	public String getValor() {
		return valor;
	}
    
}