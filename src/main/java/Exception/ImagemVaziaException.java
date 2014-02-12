package Exception;

import util.ConstantesUI;
/**
 * 
 * @author FrankJunior
 *
 */

@SuppressWarnings("serial")
public class ImagemVaziaException extends Exception {
	
	public ImagemVaziaException() {
		super(ConstantesUI.POPUP_IMAGE_VAZIA);
	}

}
