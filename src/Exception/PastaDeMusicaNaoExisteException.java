package Exception;

import gui.ConstantesUI;

@SuppressWarnings("serial")
public class PastaDeMusicaNaoExisteException extends Exception {
	
	public PastaDeMusicaNaoExisteException() {
		super(ConstantesUI.POPUP_DIRETORIO_DE_MUSICA_INVALIDO);
	}

}
