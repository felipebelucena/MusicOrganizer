package Exception;

import gui.ConstantesUI;

@SuppressWarnings("serial")
public class ImagemVaziaException extends Exception {
	
	public ImagemVaziaException() {
		super(ConstantesUI.POPUP_IMAGE_VAZIA);
	}

}
