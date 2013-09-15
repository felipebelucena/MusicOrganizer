package Exception;

import util.ConstantesUI;

@SuppressWarnings("serial")
public class DiscoCarregadoException extends Exception {
	
	public DiscoCarregadoException() {
		super(ConstantesUI.ERRO_DISCO_SALVO);
	}

}
