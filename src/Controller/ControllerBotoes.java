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
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;

import Base.Tags;
import Base.TipoPopUp;
import Exception.ImagemVaziaException;

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
		
		// criando um Array de File e alimentando ele com o path de todas as músicas, já ordenadas
		List<File> musicas = new ArrayList<File>();
		for (int i = 0; i < painelFaixas.getTextFieldLabels().size(); i++) {
			String faixaPath = painelFaixas.getTextFieldLabels().get(i).getText();
			String discoPath = controller.getDisco().getAbsolutePath();
			String path = discoPath+File.separator+faixaPath;
			musicas.add(new File(path));
		}
		
		// Verifica se algum disco já foi carregado
		if(listaTags == null){
			new PopUp(ConstantesUI.POPUP_CARREGUE_UM_DISCO, TipoPopUp.INFO);
			return;
		}
		
		// Maneira meio louca de garantir a informação dentro do listaTags. E ainda ordenado
		for (int i = 0; i < musicas.size(); i++) {
			listaTags.get(i).setArtista(painelTagsGerais.getTextFieldArtista().getText());
			listaTags.get(i).setAlbum(painelTagsGerais.getTextFieldAlbum().getText());
			listaTags.get(i).setAno(painelTagsGerais.getTextFieldAno().getText());
			listaTags.get(i).setGenero(painelTagsGerais.getTextFieldGenero().getText());
			listaTags.get(i).setNumero(painelFaixas.getTextFieldsNumero().get(i).getText());
			listaTags.get(i).setNomeDaMusica(painelFaixas.getTextFieldsFaixas().get(i).getText());
			listaTags.get(i).setImage(controllerImage.getImagem());
		}
		
		//Seta todas as tags
		try {
			setTags(listaTags,musicas);
		} catch (NullPointerException e) {
			new PopUp(ConstantesUI.POPUP_CAMPOS_OBRIGATORIOS, TipoPopUp.INFO);
		} catch (ReadOnlyFileException e) {
			new PopUp(ConstantesUI.POPUP_ARQUIVO_APENAS_DE_LEITURA, TipoPopUp.ERROR);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
//		String artista = listaTags.get(0).getArtista();
//		String album = listaTags.get(0).getAlbum();
//		File tempDirectory = new File(artista+File.separator+album);
//		if(tempDirectory.mkdirs()){
//			System.out.println("criou com sucesso");
//		}else{
//			System.out.println("falha na criação");
//		}
	}

	private void setTags(ArrayList<Tags> listaTags,List<File> musicas) throws ReadOnlyFileException, NullPointerException, Exception {
		int erro = 0;
		for (int i = 0; i < musicas.size(); i++) {
				AudioFile f = AudioFileIO.read(musicas.get(i));
				Tag tag = f.getTag();
				tag.setField(FieldKey.ARTIST, listaTags.get(i).getArtista());
				tag.setField(FieldKey.ALBUM, listaTags.get(i).getAlbum());
				tag.setField(FieldKey.YEAR, listaTags.get(i).getAno());
				tag.setField(FieldKey.GENRE, listaTags.get(i).getGenero());
				tag.setField(FieldKey.TRACK, listaTags.get(i).getNumero());
				tag.setField(FieldKey.TITLE, listaTags.get(i).getNomeDaMusica());
				try {
					Artwork art = setArtwork(listaTags.get(i).getImage());
					tag.deleteArtworkField();
					tag.setField(art);
				} catch (ImagemVaziaException e) {
					erro++; //Isso me garante, que o pop-up só vai ser exibido 1x
					if(erro <= 1){
						new PopUp(ConstantesUI.POPUP_IMAGE_VAZIA, TipoPopUp.INFO);
					}
				}
				f.commit();
		}
	}
	
	private Artwork setArtwork(byte[] image) throws ImagemVaziaException{
		if(image != null){
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
		}else{
			throw new ImagemVaziaException();
		}
	}

}
