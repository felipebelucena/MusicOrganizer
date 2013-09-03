package Exception;

import util.ConstantesUI;

/**
 * 
 * @author FrankJunior
 *
 */

@SuppressWarnings("serial")
public class ListaNulaException extends Exception {
	
	public ListaNulaException() {
		super(ConstantesUI.ERRO_LISTA_NULA);
	}

}
