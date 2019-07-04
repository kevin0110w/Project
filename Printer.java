import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Printer {
	private List<Image> seenImages;
	List<String> hiddenImages;
	
	public Printer() {
		seenImages = new ArrayList<Image>();
		hiddenImages = new ArrayList<String>();
	}
	
	public void setSeenImages(List<Image> list) {
		this.seenImages = list;
	}
	
	public void setHiddenImages(List<String> decoys) {
		this.hiddenImages = decoys;
	}
	
	public void printToFile() {
		File f = new File("output.txt");
		FileWriter out = null;
		try {
			out = new FileWriter(f);
			String s = "Seen\n";
			s += getSeenImages();
			s += "Unseen\n";
			s += getUnseenImages();
			out.write(s);
		} catch (Exception e) {
			
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				
			}
		}
	}
	
	private String getUnseenImages() {
		String s = "";
		for (String i : hiddenImages) {
			s = s + i + "\n";
		}
		return s;
	}

	public String getSeenImages() {
		String s = "";
		for (Image i : seenImages) {
			s = s + i.getImagePath() + "\n";
		}
		return s;
	}
}


