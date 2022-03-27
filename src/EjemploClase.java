import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class EjemploClase {
	public static void main(String[] args) {
		
		String path = System.getProperty("user.dir") + "\\src\\test-text";
		String text = "texto añadido";
		try {
			Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
