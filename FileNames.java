import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FileNames {
	private Set<Map<String, String>> imageSet;
	
	public FileNames() {
		this.imageSet = new HashSet<Map<String, String>>();
//		this.imageSetPath = new HashSet<String>();
		createRegistrationSet();
	}
	
	private void createRegistrationSet() {
//		File[] files = returnFiles();
		List<HashMap<String, String>> results = returnFiles();
		int counter = 0;
		Random random = new Random();
		Set decidedNumbers = new HashSet<Integer>();
		while (counter < 20) {
//			int index = random.nextInt(files.length);
//			this.imageSet.add(files[index]);
//			counter++;
			boolean successfuladdition = false;
			while (!successfuladdition) {
				int index = random.nextInt(results.size());
				if (decidedNumbers.add(index)) {
					this.imageSet.add(results.get(index));
					successfuladdition = true;
				}
			}
			counter++;
//			successfuladdition = false;
		}
		System.out.println(this.imageSet.size());
		System.out.println(decidedNumbers.size());
	}
	
	/**
	 * Getting a list of key-value (filenames/filepaths) pairs
	 * @return
	 */
	public List<HashMap<String, String>> returnFiles() {
		Map<String, String> allFiles = new HashMap<String, String>();
//		List<String> results = new ArrayList<String>();
		List<HashMap<String, String>> results2 = new ArrayList<HashMap<String, String>>();
		String p = "C:\\Users\\woohoo\\eclipse-workspace\\project\\mikons2";
		File[] files = new File(p).listFiles();
		
		for (File file : files) {
			if (file.isFile() && file.getAbsolutePath().endsWith(".gif")) {
//				results.add(file.getName());
				HashMap<String, String> x = new HashMap<String, String>();
				x.put(file.getName(), file.getPath());
				results2.add(x);
			}
		}
//		return files;
//		return results;
		return results2;
	}
	
	public Set<Map<String, String>> getImageSet() {
		return this.imageSet;
	}
	
	public static void main(String[] args) {
		System.out.println(String.format("%-20s", "FILES"));
		FileNames fnt = new FileNames();
//		fnt.createRegistrationSet();

//		Iterator<String> i = fnt.getImageSet().iterator();
		int number = 0;
		Iterator<Map<String, String>> i = fnt.getImageSet().iterator();
		while (i.hasNext()) {
			number++;
			for (String s : i.next().values()) {
				System.out.println(s);	
			}
			
		}
		System.out.println(number);
//		while (i.hasNext()) {
//			String filename = (String) i.next();
//			System.out.println("Image " + counter++ + ": " + filename);
//		}
	}
}

