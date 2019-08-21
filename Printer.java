
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Printer {
	private List<String> seenImages, decoyImages;
	private int counter;

	
	public Printer() {
		seenImages = new ArrayList<String>();
		decoyImages = new ArrayList<String>();
	}
	
	public void setSeenImages(List<String> list) {
		this.seenImages.addAll(list);
	}
	
	public void setDecoyImages(List<String> decoys) {
		this.decoyImages.addAll(decoys);
	}
	
	public void setCounter(int n) {
		this.counter = n;
	}
	public void printToFile() {
		File f = new File("output " + counter + ".txt");
		FileWriter out = null;
//		String s = "Picture";
		String lines = null;
		int counter = 1;
		try {
			out = new FileWriter(f);
			String s = "Seen\n";
			s += getSeenImages();
			s += "Decoys\n";
			s += getDecoyImages();
			out.write(s);
//			for (int i = 0; i < 60; i++) {
//			lines += s + counter + "\n";
//			counter++;
//			}
//			out.write(lines);
		} catch (Exception e) {
			
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				
			}
		}
	}
	
	private String getDecoyImages() {
		String s = "";
		for (String i : decoyImages) {
			s = s + i + "\n";
		}
		return s;
	}

	public String getSeenImages() {
		String s = "";
		for (String i : seenImages) {
			s = s + i + "\n";
		}
		return s;
	}
	
	public static void main(String[] args) {
		Printer p = new Printer();
		p.printToFile();
	}
}


