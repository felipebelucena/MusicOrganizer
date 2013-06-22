package Exception;

import gui.ConstantesUI;

public class ListaVaziaException extends Exception {
	
	public ListaVaziaException() {
		super(ConstantesUI.ERRO_LISTA_TAGS_VAZIA);
	}

}
