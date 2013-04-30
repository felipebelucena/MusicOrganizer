package Controller;

import gui.PainelFaixas;
import gui.PainelImagem;
import gui.PainelTagsGerais;

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

import Base.Tags;
import Exception.ListaNulaException;
import Exception.ListaVaziaException;

public class Controller {

	public Controller() {

	}

	public ArrayList<Tags> parserFileToTagsList(File disco) {
		File[] files = disco.listFiles();
		AudioFile audioFile = null;
		ArrayList<Tags> listaTags = new ArrayList<Tags>();
		
		try {
			int j = 0;
			for (int i = 0; i < files.length; i++) {

				Tags tags = new Tags();
				byte[] image = null;
				if (validator(files[i])) {
					audioFile = AudioFileIO.read(files[i]);
					Tag tag = audioFile.getTag();
					
					String album = tag.getFirst(FieldKey.ALBUM);
					String artista = tag.getFirst(FieldKey.ARTIST);
					String ano = tag.getFirst(FieldKey.YEAR);
					String genero = tag.getFirst(FieldKey.GENRE);
					String nomeDaMusica = tag.getFirst(FieldKey.TITLE);
					String numero = tag.getFirst(FieldKey.TRACK);
					String nomeDoArquivo = files[i].getName();
					
					try {
						Artwork artwork = tag.getFirstArtwork();
						image = artwork.getBinaryData();
					} catch (NullPointerException e) {
						Path path = Paths.get("imagem-padrao.jpg");
					    image = Files.readAllBytes(path);
					}
					
					tags.setArtista(artista);
					tags.setAlbum(album);
					tags.setAno(ano);
					tags.setGenero(genero);
					tags.setNomeDaMusica(nomeDaMusica);
					tags.setNumero(numero);
					tags.setNomeDoArquivo(nomeDoArquivo);
					tags.setImage(image);

					listaTags.add(j, tags);
					j++;
				}
			}

		} catch (CannotReadException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (TagException e) {
			System.out.println(e.getMessage());
		} catch (ReadOnlyFileException e) {
			System.out.println(e.getMessage());
		} catch (InvalidAudioFrameException e) {
			System.out.println(e.getMessage());
		}
		
		return listaTags;
	}

	private boolean validator(File file) {
		String[] extensoes = new String[] { "mp3" };
		boolean result = false;

		for (String extensao : extensoes) {
			if (file.getName().toLowerCase().endsWith(extensao)) {
				result = true;
			}
		}
		return result;
	}

	public void updateValues(File[] arquivos,
			PainelTagsGerais painelTagsGerais, PainelFaixas painelFaixas,
			PainelImagem painelImagem, ArrayList<Tags> listaTags) throws ListaNulaException,
			ListaVaziaException {

		String artista = "";
		String album = "";
		String ano = "";
		String genero = "";
		try {

			if (listaTags.get(0) != null) {
				artista = listaTags.get(0).getArtista();
				album = listaTags.get(0).getAlbum();
				ano = listaTags.get(0).getAno();
				genero = listaTags.get(0).getGenero();
			}
			
			ArrayList<JLabel> labels = new ArrayList<JLabel>();
			ArrayList<JTextField> textFieldsNumero = new ArrayList<JTextField>();
			ArrayList<JTextField> textFieldsFaixas = new ArrayList<JTextField>();

			String nomeDoArquivo = "";
			String numero = "";
			String faixas = "";

			for (int i = 0; i < listaTags.size(); i++) {
				labels.add(new JLabel());
				textFieldsNumero.add(new JTextField(3));
				textFieldsFaixas.add(new JTextField(15));
			}

			for (int i = 0; i < listaTags.size(); i++) {

				nomeDoArquivo = listaTags.get(i).getNomeDoArquivo();
				numero = listaTags.get(i).getNumero();
				faixas = listaTags.get(i).getNomeDaMusica();

				labels.get(i).setText(nomeDoArquivo);
				textFieldsNumero.get(i).setText(numero);
				textFieldsFaixas.get(i).setText(faixas);
			}

			/*
			 * Ordenação - Bubble Sort
			 * TODO [MELHORIA] melhorar o algoritmo de ordenação, para outro mais eficiente que o Bubble
			 */
			int contador = 1;
			do {
				for (int i = 0; i < textFieldsNumero.size() - 1; i++) {
					int valor = Integer.parseInt(textFieldsNumero.get(i).getText());
					int next = Integer.parseInt(textFieldsNumero.get(i + 1).getText());
					if (valor > next) {
						trocaValores(textFieldsNumero, i);
						trocaValores(textFieldsFaixas, i);
						trocaValores(labels, i);
					}
				}
				contador ++;
			} while (contador < textFieldsNumero.size());
			
			byte[] image = listaTags.get(0).getImage();
			Tags tag = new Tags();
			tag.setImage(image);
			
			// Atualizando a UI
			painelTagsGerais.updateValues(artista, album, ano, genero);
			painelFaixas.updateValues(labels, textFieldsNumero,
					textFieldsFaixas);
			painelImagem.updateValues(tag.getImage());

		} catch (NullPointerException e) {
			throw new ListaNulaException();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ListaVaziaException();
		}catch (IndexOutOfBoundsException e) {
			throw new ListaVaziaException();
		}
	}

	private <Tipo> void trocaValores(ArrayList<Tipo> lista, int i) {
		Tipo tmp;
		tmp = lista.get(i);
		lista.set(i, lista.get(i + 1));
		lista.set(i + 1, tmp);
	}
}
	
