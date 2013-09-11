package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;

import ui.dialog.PopUp;
import ui.paineis.PainelFaixas;
import ui.paineis.PainelFaixasVariousArtists;
import ui.paineis.PainelTagsGerais;
import ui.paineis.PainelTagsGeraisDoubleDisc;
import ui.paineis.PainelTagsGeraisVariousArtists;
import util.ConstantesUI;
import util.ControllerFile;
import util.Logger;
import util.PropertiesFile;
import Base.Tags;
import Base.TipoDeDisco;
import Base.TipoPopUp;
import Exception.ImagemVaziaException;
import Exception.PastaDeMusicaNaoExisteException;
import Exception.PastaDeMusicaVaziaException;

/**
 * 
 * @author FrankJunior
 *
 */

public class ControllerOutput {

	private ControllerInput controllerInput;
	private ControllerFile controllerFile;

	public ControllerOutput() {
		controllerInput = ControllerInput.getInstace();
		controllerFile = ControllerFile.getInstance();
	}

	/**
	 * Método chamado pelo botão "salvar". Faz basicamente 3 coisas: 
	 * 1 - Seta as tags 
	 * 2 - Cria a arvore de diretorio de acordo com a necessidade 
	 * 3 - move os .mp3 corrigidos para a arvore
	 */
	public void salvar() {
		
		/*
		 * Verifica se o diretorio de musica é válido
		 */
		String diretorioDeMusica = PropertiesFile.getProperties(ConstantesUI.DIRETORIO_DE_MUSICA);
		try {
			PropertiesFile.verifyMusicFolder(diretorioDeMusica);
		} catch (PastaDeMusicaNaoExisteException e1) {
			new PopUp(ConstantesUI.POPUP_DIRETORIO_DE_MUSICA_INVALIDO, TipoPopUp.ERROR);
			return;
		} catch (PastaDeMusicaVaziaException e1) {
			new PopUp(ConstantesUI.POPUP_INFORME_DIRETORIO_DE_MUSICA, TipoPopUp.WARNING);
			return;
		}

		/*
		 * Verifica se algum disco já foi carregado
		 */
		ArrayList<Tags> listaTags = controllerInput.getListaTags();
		if (listaTags == null) {
			new PopUp(ConstantesUI.POPUP_CARREGUE_UM_DISCO, TipoPopUp.WARNING);
			return;
		}
		
		/*
		 * carrega o tipo de disco, do arquivo de properties
		 */
		String tipoDeDisco = PropertiesFile.getTipoDeDisco();
		List<File> musicas = null;
		
		/*
		 * 1. pega todas as URL dos .mp3, preenchendo uma lista de File
		 * 2. preenche uma lista de tags, baseado em cada .mp3
		 * 3. seta todas as tags
		 * 4. renomeia os arquivos
		 */
		if (tipoDeDisco.equals(ConstantesUI.DISC_TYPE_VA)) {
			
			musicas = fillMusicArray(TipoDeDisco.VA);
			listaTags = fillListaTags(musicas, TipoDeDisco.VA);
			setTags(listaTags, musicas, TipoDeDisco.VA);
			controllerFile.renameFile(listaTags, musicas, TipoDeDisco.VA);
			
		} else if(tipoDeDisco.equals(ConstantesUI.DISC_TYPE_DOUBLE)){
			
			musicas = fillMusicArray(TipoDeDisco.DOUBLE);
			listaTags = fillListaTags(musicas, TipoDeDisco.DOUBLE);
			setTags(listaTags, musicas, TipoDeDisco.DOUBLE);
			controllerFile.renameFile(listaTags, musicas, TipoDeDisco.DOUBLE);
		
		} else if(tipoDeDisco.equals(ConstantesUI.DISC_TYPE_TRIBUTES)){
			
			musicas = fillMusicArray(TipoDeDisco.TRIBUTES);
			listaTags = fillListaTags(musicas, TipoDeDisco.TRIBUTES);
			setTags(listaTags, musicas, TipoDeDisco.TRIBUTES);
			controllerFile.renameFile(listaTags, musicas, TipoDeDisco.TRIBUTES);
		
		} else if(tipoDeDisco.equals(ConstantesUI.DISC_TYPE_DEFAULT)){
			
			musicas = fillMusicArray(TipoDeDisco.NORMAL);
			listaTags = fillListaTags(musicas, TipoDeDisco.NORMAL);
			setTags(listaTags, musicas, TipoDeDisco.NORMAL);
			controllerFile.renameFile(listaTags, musicas, TipoDeDisco.NORMAL);
		}
		
		
//		PainelFaixas painelFaixas = controllerInput.getPainelFaixas();
//		PainelTagsGerais painelTagsGerais = controllerInput.getPainelTagsGerais();
//
//
//		// Criação do diretorio
//		File tempDir = controllerFile.tempDirCreate(diretorioDeMusica, listaTags);
//		if (tempDir != null) {
//			if (!tempDir.mkdirs()) {
//				new PopUp(ConstantesUI.POPUP_FALHA_CRIACAO_DIRETORIO,
//						TipoPopUp.ERROR);
//				return;
//			}
//		}
//
//		// Movendo os arquivos para a pasta de musica
//		if (tempDir != null) {
//			controllerFile.moveFile(musicas, tempDir);
//			File diretorioDeOrigem = musicas.get(0).getParentFile();
//			Logger.debug("deletando: " + diretorioDeOrigem);
//			controllerFile.delete(diretorioDeOrigem);
//		}
//
//		new PopUp(ConstantesUI.POPUP_SALVO_COM_SUCESSO, TipoPopUp.INFO);
	}

	/**
	 *  Preenche uma lista de Tags, pegando as informações da UI
	 * @param musicas - lista de músicas carregadas
	 * @param tipoDeDisco - enum com o tipo de disco, que vai salvar
	 * @return lista de tags preenchida
	 */
	private ArrayList<Tags> fillListaTags(List<File> musicas, TipoDeDisco tipoDeDisco) {
		ArrayList<Tags> listaTags = new ArrayList<Tags>();
		ControllerImage controllerImage = ControllerImage.getInstace();
		switch (tipoDeDisco) {
		case VA: {
			listaTags.clear();
			PainelFaixasVariousArtists painelFaixas = PainelFaixasVariousArtists.getInstace();
			PainelTagsGeraisVariousArtists painelTagsGerais = PainelTagsGeraisVariousArtists.getInstace();
			for (int i = 0; i < musicas.size(); i++) {
				Tags tags = new Tags();
				tags.setAlbum(painelTagsGerais.getTextFieldAlbum().getText());
				tags.setAno(painelTagsGerais.getTextFieldAno().getText());
				tags.setGenero(painelTagsGerais.getTextFieldGenero().getText());
				tags.setNumero(painelFaixas.getlistTextFieldNumero().get(i).getText());
				tags.setNomeDaMusica(painelFaixas.getlistTextFieldFaixas().get(i).getText());
//				TODO implementar esse JTextField
//				tags.setArtista(artista);
				tags.setAlbumArtista(ConstantesUI.VARIOUS_ARTISTS);
				tags.setImage(controllerImage.getImagem());
				tags.setDiscoNumero(ConstantesUI.STRING_VAZIA);
				tags.setDiscoTotal(ConstantesUI.STRING_VAZIA);
				listaTags.add(tags);
			}
			break;
		}
		case DOUBLE: {
			listaTags.clear();
			PainelFaixas painelFaixas = PainelFaixas.getInstace();
			PainelTagsGeraisDoubleDisc paineltagsGerais = PainelTagsGeraisDoubleDisc.getInstace();
			for (int i = 0; i < musicas.size(); i++) {
				Tags tags = new Tags();
				tags.setAlbum(paineltagsGerais.getTextFieldAlbum().getText());
				tags.setAno(paineltagsGerais.getTextFieldAno().getText());
				tags.setGenero(paineltagsGerais.getTextFieldGenero().getText());
				tags.setNumero(painelFaixas.getlistTextFieldNumero().get(i).getText());
				tags.setNomeDaMusica(painelFaixas.getlistTextFieldFaixas().get(i).getText());
				tags.setArtista(paineltagsGerais.getTextFieldArtista().getText());
				tags.setAlbumArtista(ConstantesUI.STRING_VAZIA);
				tags.setImage(controllerImage.getImagem());
//				TODO implementar esses JTextField
//				tags.setDiscoNumero(discoNumero);
//				tags.setDiscoTotal(discoTotal)
				listaTags.add(tags);
			}
			break;
		}
		case NORMAL:
		case TRIBUTES:{
			listaTags.clear();
			PainelFaixas painelFaixas = PainelFaixas.getInstace();
			PainelTagsGerais painelTagsGerais = PainelTagsGerais.getInstace();
			for (int i = 0; i < musicas.size(); i++) {
				Tags tags = new Tags();
				tags.setAlbum(painelTagsGerais.getTextFieldAlbum().getText());
				tags.setAno(painelTagsGerais.getTextFieldAno().getText());
				tags.setGenero(painelTagsGerais.getTextFieldGenero().getText());
				tags.setNumero(painelFaixas.getlistTextFieldNumero().get(i).getText());
				tags.setNomeDaMusica(painelFaixas.getlistTextFieldFaixas().get(i).getText());
				tags.setArtista(painelTagsGerais.getTextFieldArtista().getText());
				tags.setAlbumArtista(ConstantesUI.STRING_VAZIA);
				tags.setImage(controllerImage.getImagem());
				tags.setDiscoNumero(ConstantesUI.STRING_VAZIA);
				tags.setDiscoTotal(ConstantesUI.STRING_VAZIA);
				listaTags.add(tags);
			}
			break;
		}
		default:
			new PopUp(ConstantesUI.POPUP_PAINEL_INVALIDO, TipoPopUp.ERROR);
			break;
		}
		return listaTags;
	}

	/**
	 * Preenche uma lista de músicas com o path de todas as músicas
	 * @param tipoDeDisco - Enum para identificar, de qual painel pegar as informações
	 * @return lista de musicas preenchida
	 */
	private List<File> fillMusicArray(TipoDeDisco tipoDeDisco) {
		List<File> musicas = new ArrayList<File>();
		JPanel painelFaixas = null;
		List<JLabel> labelsList = null;
		switch (tipoDeDisco) {
		case VA:
			painelFaixas = PainelFaixasVariousArtists.getInstace();
			labelsList = ((PainelFaixasVariousArtists) painelFaixas).getlistLabels();
			break;
		case DOUBLE:
		case TRIBUTES:
		case NORMAL:
			painelFaixas = PainelFaixas.getInstace();
			labelsList = ((PainelFaixas) painelFaixas).getlistLabels();
			break;
		default:
			new PopUp(ConstantesUI.POPUP_PAINEL_INVALIDO, TipoPopUp.ERROR);
			break;
		}
		
		String discoPath = controllerInput.getDisco().getAbsolutePath();
		//se a lista de músicas não estiver fazia, limpa
		if (!musicas.isEmpty()) {
			musicas.clear();
		}
		// criando um Array de File e alimentando ele com o path de todas as
		// músicas, já ordenadas
		for (int i = 0; i < labelsList.size(); i++) {
			String faixaPath = labelsList.get(i)
					.getText();
			String path = discoPath + File.separator + faixaPath;
			musicas.add(new File(path));
		}
		return musicas;
	}

	/**
	 * Método auxiliar, para setar as tags de uma lista de .mp3
	 * 
	 * @param listaTags
	 * @param musicas
	 * @param tipoDeDisco
	 */
	private void setTags(ArrayList<Tags> listaTags, List<File> musicas,
			TipoDeDisco tipoDeDisco) {

		switch (tipoDeDisco) {
		case DOUBLE:{
			try {
				int erro = 0;
				for (int i = 0; i < musicas.size(); i++) {
					AudioFile f = AudioFileIO.read(musicas.get(i));
					Tag tag = f.getTag();
					tag.setField(FieldKey.ALBUM, listaTags.get(i).getAlbum());
					tag.setField(FieldKey.YEAR, listaTags.get(i).getAno());
					tag.setField(FieldKey.GENRE, listaTags.get(i).getGenero());
					tag.setField(FieldKey.TRACK, listaTags.get(i).getNumero());
					tag.setField(FieldKey.TITLE, listaTags.get(i).getNomeDaMusica());
					tag.setField(FieldKey.ARTIST, listaTags.get(i).getArtista());
					tag.setField(FieldKey.ALBUM_ARTIST, ConstantesUI.STRING_VAZIA);
					//TODO trocar essas 2 linhas pelas linhas de baixo
					// quando o TODO do método fillListaTags() for resolvido
//					tag.setField(FieldKey.DISC_NO, listaTags.get(i).getDiscoNumero());
//					tag.setField(FieldKey.DISC_TOTAL, listaTags.get(i).getDiscoTotal());
					try {
						Artwork art = setArtwork(listaTags.get(i).getImage());
						tag.deleteArtworkField();
						tag.setField(art);
					} catch (ImagemVaziaException e) {
						// Isso me garante, que o pop-up só vai ser exibido 1x
						erro++; 
						if (erro <= 1) {
							new PopUp(ConstantesUI.POPUP_IMAGE_VAZIA, TipoPopUp.WARNING);
						}
					}
					f.commit();
				}
			} catch (ReadOnlyFileException e) {
				new PopUp(ConstantesUI.POPUP_ARQUIVO_APENAS_DE_LEITURA,TipoPopUp.ERROR);
				return;
			} catch (NullPointerException e) {
				new PopUp(ConstantesUI.POPUP_CAMPOS_OBRIGATORIOS, TipoPopUp.WARNING);
				return;
				// Esse Exception generico serve pra substituir os 4 Exception
				// que o "AudioFile f = AudioFileIO.read(musicas.get(i));" gera
			} catch (Exception e) {
				Logger.error(ConstantesUI.ERRO_INESPERADO+e.getMessage());
				return;
			}
			break;
		}
		case VA:{
			try {
				int erro = 0;
				for (int i = 0; i < musicas.size(); i++) {
					AudioFile f = AudioFileIO.read(musicas.get(i));
					Tag tag = f.getTag();
					tag.setField(FieldKey.ALBUM, listaTags.get(i).getAlbum());
					tag.setField(FieldKey.YEAR, listaTags.get(i).getAno());
					tag.setField(FieldKey.GENRE, listaTags.get(i).getGenero());
					tag.setField(FieldKey.TRACK, listaTags.get(i).getNumero());
					tag.setField(FieldKey.TITLE, listaTags.get(i).getNomeDaMusica());
					//TODO trocar essa linha pela linha de baixo
					// quando o TODO do método fillListaTags() for resolvido
					tag.setField(FieldKey.ARTIST, ConstantesUI.STRING_VAZIA);
//					tag.setField(FieldKey.ARTIST, listaTags.get(i).getArtista());
					tag.setField(FieldKey.ALBUM_ARTIST, ConstantesUI.VARIOUS_ARTISTS);
					try {
						Artwork art = setArtwork(listaTags.get(i).getImage());
						tag.deleteArtworkField();
						tag.setField(art);
					} catch (ImagemVaziaException e) {
						// Isso me garante, que o pop-up só vai ser exibido 1x
						erro++; 
						if (erro <= 1) {
							new PopUp(ConstantesUI.POPUP_IMAGE_VAZIA, TipoPopUp.WARNING);
						}
					}
					f.commit();
				}
			} catch (ReadOnlyFileException e) {
				new PopUp(ConstantesUI.POPUP_ARQUIVO_APENAS_DE_LEITURA,TipoPopUp.ERROR);
				return;
			} catch (NullPointerException e) {
				new PopUp(ConstantesUI.POPUP_CAMPOS_OBRIGATORIOS, TipoPopUp.WARNING);
				return;
				// Esse Exception generico serve pra substituir os 4 Exception
				// que o "AudioFile f = AudioFileIO.read(musicas.get(i));" gera
			} catch (Exception e) {
				Logger.error(ConstantesUI.ERRO_INESPERADO+e.getMessage());
				return;
			}
			break;
		}
		case NORMAL:
		case TRIBUTES:{
			try {
				int erro = 0;
				for (int i = 0; i < musicas.size(); i++) {
					AudioFile f = AudioFileIO.read(musicas.get(i));
					Tag tag = f.getTag();
					tag.setField(FieldKey.ALBUM, listaTags.get(i).getAlbum());
					tag.setField(FieldKey.YEAR, listaTags.get(i).getAno());
					tag.setField(FieldKey.GENRE, listaTags.get(i).getGenero());
					tag.setField(FieldKey.TRACK, listaTags.get(i).getNumero());
					tag.setField(FieldKey.TITLE, listaTags.get(i).getNomeDaMusica());
					tag.setField(FieldKey.ARTIST, listaTags.get(i).getArtista());
					tag.setField(FieldKey.ALBUM_ARTIST, ConstantesUI.STRING_VAZIA);
					try {
						Artwork art = setArtwork(listaTags.get(i).getImage());
						tag.deleteArtworkField();
						tag.setField(art);
					} catch (ImagemVaziaException e) {
						// Isso me garante, que o pop-up só vai ser exibido 1x
						erro++; 
						if (erro <= 1) {
							new PopUp(ConstantesUI.POPUP_IMAGE_VAZIA, TipoPopUp.WARNING);
						}
					}
					f.commit();
				}
			} catch (ReadOnlyFileException e) {
				new PopUp(ConstantesUI.POPUP_ARQUIVO_APENAS_DE_LEITURA,TipoPopUp.ERROR);
				return;
			} catch (NullPointerException e) {
				new PopUp(ConstantesUI.POPUP_CAMPOS_OBRIGATORIOS, TipoPopUp.WARNING);
				return;
				// Esse Exception generico serve pra substituir os 4 Exception
				// que o "AudioFile f = AudioFileIO.read(musicas.get(i));" gera
			} catch (Exception e) {
				Logger.error(ConstantesUI.ERRO_INESPERADO+e.getMessage());
				return;
			}
			break;
		}
		default:
			break;
		}
	}

	/**
	 * Método para setar a imagem na tag
	 * 
	 * @param image
	 * @return
	 * @throws ImagemVaziaException
	 */
	private Artwork setArtwork(byte[] image) throws ImagemVaziaException {
		if (image != null) {
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
			} finally {
				try {
					cover.delete();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return art;
		} else {
			throw new ImagemVaziaException();
		}
	}

	/**
	 * Escreve os nomes dos arquivos, nos JTextfield do PainelFaixas
	 */
	public void nome2Tags() {
		PainelFaixas painelFaixas = controllerInput.getPainelFaixas();
		ArrayList<JLabel> nomes = painelFaixas.getlistLabels();

		for (int i = 0; i < nomes.size(); i++) {
			String faixa = nomes.get(i).getText();
			painelFaixas.getlistTextFieldFaixas().get(i).setText(faixa);
		}
	}

}
