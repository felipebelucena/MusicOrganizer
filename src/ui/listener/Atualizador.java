package ui.listener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author FrankJunior
 *
 */

public class Atualizador {
	
	private List<HabilitarComponentesListener> listHabilitarListener;
	private List<UpdateFaixasListener> listUpdateFaixasListener;
	private List<UpdateImageListener> listUpdateImageListener;
	private List<UpdateTagsGeraisListener> listUpdateTagsGeraisListener;
	
	public Atualizador() {
		listHabilitarListener = new ArrayList<HabilitarComponentesListener>();
		listUpdateFaixasListener = new ArrayList<UpdateFaixasListener>();
		listUpdateImageListener = new ArrayList<UpdateImageListener>();
		listUpdateTagsGeraisListener = new ArrayList<UpdateTagsGeraisListener>();
	}
	
	public void addHabilitarComponentesListener(HabilitarComponentesListener listener){
		listHabilitarListener.add(listener);
	}
	
	public void addUpdateFaixasListener(UpdateFaixasListener updateFaixasListener){
		listUpdateFaixasListener.add(updateFaixasListener);
	}
	
	public void addUpdateImageListener(UpdateImageListener updateImageListener){
		listUpdateImageListener.add(updateImageListener);
	}
	
	public void addUpdateTagsGeraisListener(UpdateTagsGeraisListener updateTagsGeraisListener){
		listUpdateTagsGeraisListener.add(updateTagsGeraisListener);
	}
	
	public void updateImage(byte[] image){
		for (UpdateImageListener listener : listUpdateImageListener) {
			listener.updateImage(image);
		}
	}
	
	public void updateFaixas(ArrayList<JLabel> listLabels,
			ArrayList<JTextField> listTextFieldNumero,
			ArrayList<JTextField> listTextFieldFaixas, ArrayList<JTextField> listTextFieldArtista){
		
		for (UpdateFaixasListener listener : listUpdateFaixasListener) {
			listener.updateFaixas(listLabels, listTextFieldNumero, listTextFieldFaixas, listTextFieldArtista);
		}
	}
	
	public void updatetagsGerais(String artista, String album, String ano, String genero, int discoNumero, int discoTotal){
		
		for (UpdateTagsGeraisListener listener : listUpdateTagsGeraisListener) {
			listener.updateTagsGerais(artista, album, ano, genero, discoNumero, discoTotal);
		}
	}
	
	public void habilitarTodosOsComponentes(){
		for (HabilitarComponentesListener listener : listHabilitarListener) {
			listener.habilitarComponentes(true);
		}
	}
	
}
