package Exception;

import gui.ConstantesUI;

@SuppressWarnings("serial")
public class ListaVaziaException extends Exception {
	
	public ListaVaziaException() {
		super(ConstantesUI.ERRO_LISTA_TAGS_VAZIA);
	}

}
