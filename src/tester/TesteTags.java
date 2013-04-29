package tester;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class TesteTags {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int retorno = fileChooser.showOpenDialog(null);
			if(retorno == JFileChooser.APPROVE_OPTION){
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getName());
			}
			
			File musica = new File("sample/outrascoisas-fumaca.mp3");
			AudioFile f = AudioFileIO.read(musica);
			Tag tag = f.getTag();
//			String album = tag.getFirst(FieldKey.ALBUM);
//			String artista = tag.getFirst(FieldKey.ARTIST);
			String title = tag.getFirst(FieldKey.TITLE);
			String track = tag.getFirst(FieldKey.TRACK);
			
			System.out.println(track+" - "+title);
			
		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
