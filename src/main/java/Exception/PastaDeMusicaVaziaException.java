package Exception;

import util.ConstantesUI;

/**
 * 
 * @author FrankJunior
 *
 */

@SuppressWarnings("serial")
public class PastaDeMusicaVaziaException extends Exception {
	
	public PastaDeMusicaVaziaException() {
		super(ConstantesUI.POPUP_DIRETORIO_DE_MUSICA_VAZIO);
	}

}
