package Exception;

import util.Textos;

public class ListaVaziaException extends Exception {
	
	public ListaVaziaException() {
		super(Textos.ERRO_LISTA_TAGS_VAZIA);
	}

}
