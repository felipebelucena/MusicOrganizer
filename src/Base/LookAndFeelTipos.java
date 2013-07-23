package Base;

public enum LookAndFeelTipos {
    NIMBUS("Nimbus"),
    METAL("Metal"),
    MOTIF("CDE/Motif"),
    MAC("Mac OS X"),
    SYSTEM("System"),
    GTK("GTK"),
    BLACK_EYES("Black Eyes");
 
    private String valor;
    
    LookAndFeelTipos(String valor) {
        this.valor = valor;
    }
	public String getValor() {
		return valor;
	}
    
}