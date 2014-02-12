package Exception;

import util.ConstantesUI;

/**
 * 
 * @author FrankJunior
 *
 */

@SuppressWarnings("serial")
public class PastaDeMusicaNaoExisteException extends Exception {
	
	public PastaDeMusicaNaoExisteException() {
		super(ConstantesUI.POPUP_DIRETORIO_DE_MUSICA_INVALIDO);
	}

}
