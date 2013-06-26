package Controller;

import gui.ConstantesUI;
import gui.PainelFaixas;
import gui.PainelImagem;
import gui.PainelTagsGerais;
import gui.PopUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;

import Base.Tags;
import Base.TipoPopUp;

public class ControllerBotoes {
	
	private Controller controller;
	
	public ControllerBotoes() {
		
	}
	
	public void salvar(){
		//TODO pegar de um arquivo de properties
		String diretorioDeMusica = "";
		
		String diretorioTemp = "";
		
		Controller controller = Controller.getInstace();
		ArrayList<Tags> listaTags = controller.getListaTags();
		PainelFaixas painelFaixas = controller.getPainelFaixas();
		PainelImagem painelImagem = controller.getPainelImagem();
		//Deprecated
		ControllerImage controllerImage = ControllerImage.getInstace();
		PainelTagsGerais painelTagsGerais = controller.getPainelTagsGerais();
		
		if(listaTags == null){
			new PopUp(ConstantesUI.POPUP_CARREGUE_UM_DISCO, TipoPopUp.INFO);
			return;
		}
		
		
		File[] musicas = controller.getDisco().listFiles();
		for (int i = 0; i < musicas.length; i++) {
			try {
				if(controller.validator(musicas[i])){
				AudioFile f = AudioFileIO.read(musicas[i]);
				Tag tag = f.getTag();
				tag.setField(FieldKey.ARTIST, painelTagsGerais.getTextFieldArtista().getText());
				tag.setField(FieldKey.ALBUM, painelTagsGerais.getTextFieldAlbum().getText());
				tag.setField(FieldKey.YEAR, painelTagsGerais.getTextFieldAno().getText());
				tag.setField(FieldKey.GENRE, painelTagsGerais.getTextFieldGenero().getText());
				tag.deleteArtworkField();
				Artwork art = setArtwork(controllerImage.getImagem());
				tag.setField(art);
				f.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
//		String artista = listaTags.get(0).getArtista();
//		String album = listaTags.get(0).getAlbum();
//		File tempDirectory = new File(artista+File.separator+album);
//		if(tempDirectory.mkdirs()){
//			System.out.println("criou com sucesso");
//		}else{
//			System.out.println("falha na criação");
//		}
		/**
		 * TODO
		 * 1 - criar a arvore de diretorio no path temporario, usando a listaTags
		 * 2 - A principio, copiar os .mp3 para o temp, e só depois setar as tags
		 * OBS: eu disse "A principio" pq quando essa feature tiver rendondinha, setar direto no diretorio onde elas estão =D
		 * 3 - uma vez as .mp3 dentro do temp path, e setadas as tags, move para o path de msuicas
		 */
	}
	
	private Artwork setArtwork(byte[] image){
		File cover = new File("cover.jpg");
		FileOutputStream fos = null;
		Artwork art = null;
		try {
			fos = new FileOutputStream(cover);
			fos.write(image);
			art = new Artwork();
			art.setFromFile(cover);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return art;
	}

}
