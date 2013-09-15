package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;

import ui.listener.Atualizador;
import ui.paineis.PainelFaixas;
import ui.paineis.PainelTagsGerais;
import util.ConstantesUI;
import util.Logger;
import Base.Tags;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;

/**
 * 
 * @author FrankJunior
 *
 */

public class ControllerInput {

	private static ControllerInput controller;
	private Tags tag;
	private ArrayList<Tags> listaTags = null;
	private File disco = null;
	
	private PainelTagsGerais painelTagsGerais = null;
	private PainelFaixas painelFaixas = null;

	/*
	 * ---------------------------------------------------------------------
	 * Construtor
	 * ---------------------------------------------------------------------
	 */
	private ControllerInput() {
		tag = new Tags();
	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Singleton
	 * ---------------------------------------------------------------------
	 */
	public static ControllerInput getInstace(){
		if(controller == null){
			controller = new ControllerInput();
			return controller;
		}
		return controller;
	}

	/**
	 * [Parser] Extrai todas as tags de um disco, em um ArrayList
	 * @param disco
	 * @return ArrayList de tags
	 */
	public ArrayList<Tags> parserFileToTagsList(File disco) {
		this.disco = disco;
		File[] musicas = disco.listFiles();
		AudioFile audioFile = null;
		listaTags = new ArrayList<Tags>();

		try {
			int j = 0;
			for (int i = 0; i < musicas.length; i++) {

				Tags tags = new Tags();
				byte[] image = null;
				if (validator(musicas[i])) {
					audioFile = AudioFileIO.read(musicas[i]);
					Tag tag = audioFile.getTag();

					String album = tag.getFirst(FieldKey.ALBUM);
					String artista = tag.getFirst(FieldKey.ARTIST);
					String ano = tag.getFirst(FieldKey.YEAR);
					String genero = tag.getFirst(FieldKey.GENRE);
					String nomeDaMusica = tag.getFirst(FieldKey.TITLE);
					String numero = tag.getFirst(FieldKey.TRACK);
					String albumArtist = tag.getFirst(FieldKey.ALBUM_ARTIST);
					String disco_numero = tag.getFirst(FieldKey.DISC_NO);
					String disco_total = tag.getFirst(FieldKey.DISC_TOTAL);
					String nomeDoArquivo = musicas[i].getName();

					try {
						Artwork artwork = tag.getFirstArtwork();
						image = artwork.getBinaryData();
					} catch (NullPointerException e) {
						image = loadDefaultImage();
					}

					tags.setArtista(artista);
					tags.setAlbum(album);
					tags.setAno(ano);
					tags.setGenero(genero);
					tags.setNomeDaMusica(nomeDaMusica);
					tags.setNumero(numero);
					tags.setAlbumArtista(albumArtist);
					tags.setNomeDoArquivo(nomeDoArquivo);
					tags.setImage(image);
					try {
						tags.setDiscoNumero(Integer.parseInt(disco_numero));
						tags.setDiscoTotal(Integer.parseInt(disco_total));
					} catch (NumberFormatException e) {
						tags.setDiscoNumero(ConstantesUI.NUMERO_1);
						tags.setDiscoTotal(ConstantesUI.NUMERO_1);
					}

					listaTags.add(j, tags);
					j++;
					
					/**
					 * se quiser ver todas as tags por arquivo de musica, descomentar esse for
					 */
//					for(FieldKey c : FieldKey.values()){
//						System.out.println(c+" - "+tag.getFirst(c));
//						System.out.println("-----------");
//					}
				}
			}

		} catch (CannotReadException e) {
			Logger.error(e.getMessage());
		} catch (IOException e) {
			Logger.error(e.getMessage());
		} catch (TagException e) {
			Logger.error(e.getMessage());
		} catch (ReadOnlyFileException e) {
			Logger.error(e.getMessage());
		} catch (InvalidAudioFrameException e) {
			Logger.error(e.getMessage());
		}

		return listaTags;
	}

	/**
	 * Método para carregar a imagem padrão
	 * @return imagem padrão
	 */
	public byte[] loadDefaultImage() {
		byte[] image = null;
		Path path = Paths.get(ConstantesUI.IMAGEM_PADRAO);
		try {
			image = Files.readAllBytes(path);
		} catch (IOException e) {
			Logger.error(e.getMessage());
		}
		return image;
	}

	/**
	 * Valida o disco de acordo com os formatos aceitos, nesse caso, só o .mp3
	 * @param file
	 * @return true se for válido e false se nao for
	 */
	protected boolean validator(File file) {
		String[] extensoes = new String[] { ConstantesUI.FORMATO_MP3 };
		boolean result = false;

		for (String extensao : extensoes) {
			if (file.getName().toLowerCase().trim().endsWith(extensao)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Atualiza o <code>PainelFaixas</code> com as tags carregadas do disco,e monta a UI
	 * @param arquivos
	 * @param painelTagsGerais
	 * @param painelFaixas
	 * @param painelImagem
	 * @param painelSelecaoImagem 
	 * @param listaTags
	 * @throws ListaNulaException
	 * @throws ListaVaziaException
	 */
	public void carregaMusicas(File[] arquivos,Atualizador atualizador, ArrayList<Tags> listaTags)
			throws ListaNulaException, ListaVaziaException {

		// declara variaveis
		String artista = ConstantesUI.STRING_VAZIA;
		String album = ConstantesUI.STRING_VAZIA;
		String ano = ConstantesUI.STRING_VAZIA;
		String genero = ConstantesUI.STRING_VAZIA;
		int discoNumero = ConstantesUI.NUMERO_1;
		int discoTotal = ConstantesUI.NUMERO_1;
		try {

			// preenche valores gerais
			if (listaTags.get(0) != null) {
				artista = listaTags.get(0).getArtista();
				album = listaTags.get(0).getAlbum();
				ano = listaTags.get(0).getAno();
				genero = listaTags.get(0).getGenero();
				discoNumero = listaTags.get(0).getDiscoNumero();
				discoTotal = listaTags.get(0).getDiscoTotal();
			}

			// cria listas
			ArrayList<JLabel> listLabels = new ArrayList<JLabel>();
			ArrayList<JTextField> listTextFieldNumero = new ArrayList<JTextField>();
			ArrayList<JTextField> listTextFieldFaixas = new ArrayList<JTextField>();
			ArrayList<JTextField> listTextFieldArtista = new ArrayList<JTextField>();

			// declara variáveis para o conteúdo das listas
			String nomeDoArquivo = ConstantesUI.STRING_VAZIA;
			String numero = ConstantesUI.STRING_VAZIA;
			String faixas = ConstantesUI.STRING_VAZIA;
			String artistas = ConstantesUI.STRING_VAZIA;

			// popula as listas
			for (int i = 0; i < listaTags.size(); i++) {
				listLabels.add(new JLabel());
				listTextFieldNumero.add(new JTextField(3));
				listTextFieldFaixas.add(new JTextField(15));
				listTextFieldArtista.add(new JTextField(15));
			}

			for (int i = 0; i < listaTags.size(); i++) {

				//pegas os valores, e seta nas listas
				nomeDoArquivo = listaTags.get(i).getNomeDoArquivo();
				numero = listaTags.get(i).getNumero();
				faixas = listaTags.get(i).getNomeDaMusica();
				artistas = listaTags.get(i).getArtista();

				listLabels.get(i).setText(nomeDoArquivo);
				listTextFieldNumero.get(i).setText(numero);
				listTextFieldFaixas.get(i).setText(faixas);
				listTextFieldArtista.get(i).setText(artistas);
			}

			// Ordenando a lista
			int contador = 1;
			do {
				for (int i = 0; i < listTextFieldNumero.size() - 1; i++) {
					int valor = Integer.parseInt(listTextFieldNumero.get(i)
							.getText());
					int next = Integer.parseInt(listTextFieldNumero.get(i + 1)
							.getText());
					if (valor > next) {
						trocaValores(listTextFieldNumero, i);
						trocaValores(listTextFieldFaixas, i);
						trocaValores(listLabels, i);
					}
				}
				contador++;
			} while (contador < listTextFieldNumero.size());

			byte[] image = listaTags.get(0).getImage();
			tag.setImage(image);

			// Atualizando a UI
			atualizador.habilitarTodosOsComponentes();
			atualizador.updateFaixas(listLabels, listTextFieldNumero,listTextFieldFaixas, listTextFieldArtista);
			atualizador.updateImage(tag.getImage());
			atualizador.updatetagsGerais(artista, album, ano, genero, discoNumero, discoTotal);
			
		} catch (NullPointerException e) {
			throw new ListaNulaException();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ListaVaziaException();
		} catch (IndexOutOfBoundsException e) {
			throw new ListaVaziaException();
		}
	}

	/**
	 * Método para inverter os valores do ArrayList de tags, auxilia na ordenação
	 * @param lista
	 * @param indice da lista
	 */
	private <Tipo> void trocaValores(ArrayList<Tipo> lista, int i) {
		Tipo tmp;
		tmp = lista.get(i);
		lista.set(i, lista.get(i + 1));
		lista.set(i + 1, tmp);
	}

	public ArrayList<Tags> getListaTags() {
		return listaTags;
	}

	public File getDisco() {
		return disco;
	}

	public PainelTagsGerais getPainelTagsGerais() {
		return painelTagsGerais;
	}

	public PainelFaixas getPainelFaixas() {
		return painelFaixas;
	}

}
