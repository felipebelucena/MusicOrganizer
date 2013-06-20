package Exception;

import util.Textos;

public class ListaNulaException extends Exception {
	
	public ListaNulaException() {
		super(Textos.ERRO_LISTA_NULA);
	}

}
