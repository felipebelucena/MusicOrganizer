package Exception;

import gui.ConstantesUI;

public class ListaNulaException extends Exception {
	
	public ListaNulaException() {
		super(ConstantesUI.ERRO_LISTA_NULA);
	}

}
