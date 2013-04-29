package Controller;

import gui.PopUp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import Base.Tags;
import Base.TipoPopUp;

public class Controller {

	private ArrayList<Tags> listaTags;

	public Controller() {
		listaTags = new ArrayList<Tags>();
	}

	public ArrayList<Tags> parserFileToTagsList(File disco) {
		File[] files = disco.listFiles();
		Tags tags = new Tags();
		System.out.println(files[0].getName());
		AudioFile audioFile = null;

		try {
			 for (int i = 0; i < files.length; i++) {

			if (validator(files[i])) {
				audioFile = AudioFileIO.read(files[i]);
				Tag tag = audioFile.getTag();
				String album = tag.getFirst(FieldKey.ALBUM);
				String artista = tag.getFirst(FieldKey.ARTIST);
				String ano = tag.getFirst(FieldKey.YEAR);
				String genero = tag.getFirst(FieldKey.GENRE);
				String nomeDaMusica = tag.getFirst(FieldKey.TITLE);
				String numero = tag.getFirst(FieldKey.TRACK);

				tags.setArtista(artista);
				tags.setAlbum(album);
				tags.setAno(ano);
				tags.setGenero(genero);
				tags.setNomeDaMusica(nomeDaMusica);
				tags.setNumero(numero);

				System.out.println("artista = " + artista + ", " + " album = "
						+ album + ", " + " Ano = " + ano + ", Genero = "
						+ genero + ", titulo = " + nomeDaMusica + ", numero = "
						+ numero);

				listaTags.add(tags);
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

	public ArrayList<Tags> getListaTags() {
		return listaTags;
	}

	public void setListaTags(ArrayList<Tags> listaTags) {
		this.listaTags = listaTags;
	}

}
