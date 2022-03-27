import java.io.*;

public class Ficheros2 {

	public static void main(String[] args) throws IOException {
		FileReader in = null;
		FileWriter out = null;

		try {
			in = new FileReader("input3.txt");
			out = new FileWriter("output.txt");

			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				in.close();
			}
		}

	}
}
