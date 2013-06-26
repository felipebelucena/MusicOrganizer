package Controller;

import gui.ConstantesUI;
import gui.PainelFaixas;
import gui.PainelTagsGerais;
import gui.PopUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		PainelTagsGerais painelTagsGerais = controller.getPainelTagsGerais();
		ControllerImage controllerImage = ControllerImage.getInstace();
		
		//criando um Array de File e alimentando ele com o path de todas as músicas, já ordenadas
		List<File> musicas = new ArrayList<File>();
		for (int i = 0; i < painelFaixas.getTextFieldLabels().size(); i++) {
			String faixaPath = painelFaixas.getTextFieldLabels().get(i).getText();
			String discoPath = controller.getDisco().getAbsolutePath();
			String path = discoPath+File.separator+faixaPath;
			musicas.add(new File(path));
		}
		
		//Verifica se algum disco já foi carregado
		if(listaTags == null){
			new PopUp(ConstantesUI.POPUP_CARREGUE_UM_DISCO, TipoPopUp.INFO);
			return;
		}
		
		//Seta todas as tags
		setTags(controller, painelFaixas, painelTagsGerais, controllerImage,
				musicas);
		
//		String artista = listaTags.get(0).getArtista();
//		String album = listaTags.get(0).getAlbum();
//		File tempDirectory = new File(artista+File.separator+album);
//		if(tempDirectory.mkdirs()){
//			System.out.println("criou com sucesso");
//		}else{
//			System.out.println("falha na criação");
//		}
	}

	private void setTags(Controller controller, PainelFaixas painelFaixas,
			PainelTagsGerais painelTagsGerais, ControllerImage controllerImage,
			List<File> musicas) {
		for (int i = 0; i < musicas.size(); i++) {
			try {
				if(controller.validator(musicas.get(i))){
				AudioFile f = AudioFileIO.read(musicas.get(i));
				Tag tag = f.getTag();
				tag.setField(FieldKey.ARTIST, painelTagsGerais.getTextFieldArtista().getText());
				tag.setField(FieldKey.ALBUM, painelTagsGerais.getTextFieldAlbum().getText());
				tag.setField(FieldKey.YEAR, painelTagsGerais.getTextFieldAno().getText());
				tag.setField(FieldKey.GENRE, painelTagsGerais.getTextFieldGenero().getText());
				tag.deleteArtworkField();
				Artwork art = setArtwork(controllerImage.getImagem());
				tag.setField(art);
				tag.setField(FieldKey.YEAR, painelFaixas.getTextFieldsNumero().get(i).getText());
				tag.setField(FieldKey.TITLE, painelFaixas.getTextFieldsFaixas().get(i).getText());
				f.commit();
				}
			} catch (NullPointerException e) {
				new PopUp(ConstantesUI.POPUP_CAMPOS_OBRIGATORIOS, TipoPopUp.INFO);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
				cover.delete();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return art;
	}

}
