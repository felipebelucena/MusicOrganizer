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
		List<File> musicas = new ArrayList<File>();
		
		if (tipoDeDisco.equals(ConstantesUI.DISC_TYPE_VA)) {
			fillMusicArray(musicas, TipoDeDisco.VA);
		}else if(tipoDeDisco.equals(ConstantesUI.DISC_TYPE_DOUBLE)){
			fillMusicArray(musicas, TipoDeDisco.NORMAL);
		}else if(tipoDeDisco.equals(ConstantesUI.DISC_TYPE_TRIBUTES)){
			fillMusicArray(musicas, TipoDeDisco.NORMAL);
		}else if(tipoDeDisco.equals(ConstantesUI.DISC_TYPE_DEFAULT)){
			fillMusicArray(musicas, TipoDeDisco.NORMAL);
		}
		
		
//		PainelFaixas painelFaixas = controllerInput.getPainelFaixas();
//		PainelTagsGerais painelTagsGerais = controllerInput.getPainelTagsGerais();
//		ControllerImage controllerImage = ControllerImage.getInstace();
//
//
//		/*
//		 * Maneira meio louca de garantir a informação dentro do listaTags,e ainda ordenado
//		 * FIXME melhorar isso aqui
//		 */
//		for (int i = 0; i < musicas.size(); i++) {
//			listaTags.get(i).setArtista(
//					painelTagsGerais.getTextFieldArtista().getText());
//			listaTags.get(i).setAlbum(
//					painelTagsGerais.getTextFieldAlbum().getText());
//			listaTags.get(i).setAno(
//					painelTagsGerais.getTextFieldAno().getText());
//			listaTags.get(i).setGenero(
//					painelTagsGerais.getTextFieldGenero().getText());
//			listaTags.get(i).setNumero(
//					painelFaixas.getTextFieldsNumero().get(i).getText());
//			listaTags.get(i).setNomeDaMusica(
//					painelFaixas.getTextFieldsFaixas().get(i).getText());
//			listaTags.get(i).setImage(controllerImage.getImagem());
//		}
//
//		// Seta todas as tags
//		try {
//			setTags(listaTags, musicas);
//		} catch (NullPointerException e) {
//			new PopUp(ConstantesUI.POPUP_CAMPOS_OBRIGATORIOS, TipoPopUp.WARNING);
//		} catch (ReadOnlyFileException e) {
//			new PopUp(ConstantesUI.POPUP_ARQUIVO_APENAS_DE_LEITURA,
//					TipoPopUp.ERROR);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// Renomea os aquivos
//		controllerFile.renameFile(listaTags, musicas);
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
	 * Preenche uma lista de músicas com o path de todas as músicas
	 * @param musicas - lista a ser preenchida
	 * @param tipoDeDisco - Enum para identificar, de qual painel pegar as informações
	 */
	private void fillMusicArray(List<File> musicas, TipoDeDisco tipoDeDisco) {
		JPanel painelFaixas = null;;
		List<JLabel> labelsList = null;
		switch (tipoDeDisco) {
		case VA:
			painelFaixas = PainelFaixasVariousArtists.getInstace();
			labelsList = ((PainelFaixasVariousArtists) painelFaixas).getlistLabels();
			break;
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
	}

	/**
	 * Método auxiliar, para setar as tags de uma lista de .mp3
	 * 
	 * @param listaTags
	 * @param musicas
	 * @throws ReadOnlyFileException
	 * @throws NullPointerException
	 * @throws Exception
	 */
	private void setTags(ArrayList<Tags> listaTags, List<File> musicas)
			throws ReadOnlyFileException, NullPointerException, Exception {
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
				erro++; // Isso me garante, que o pop-up só vai ser exibido 1x
				if (erro <= 1) {
					new PopUp(ConstantesUI.POPUP_IMAGE_VAZIA, TipoPopUp.WARNING);
				}
			}
			f.commit();
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
