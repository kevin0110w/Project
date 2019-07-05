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
	private static final int NUMBEROFREGISTRATIONIMAGES = 60;
	private List<Image> listOne, listTwo, listThree, screenImages, hiddenImages;
	private Set<Image> images;
	Set<Image> otherImages;
	
	public FileNames() {
		this.listOne = new ArrayList<Image>();
		this.listTwo = new ArrayList<Image>();
		this.listThree = new ArrayList<Image>();
		this.screenImages = new ArrayList<Image>();
		this.hiddenImages = new ArrayList<Image>();
		this.imageSet = new HashSet<Map<String, String>>();
		this.images = new HashSet<Image>();
//		this.NUMBEROFREGISTRATIONIMAGES = 60;
//		this.imageSetPath = new HashSet<String>();
		createRegistrationSet();
	}
	
	private void createRegistrationSet() {
		this.otherImages = new HashSet<Image>();
//		File[] files = returnFiles();
//		List<HashMap<String, String>> results = returnFiles();
		List<Image> allImages = returnFiles();
		otherImages.addAll(allImages);
		int counter = 0;
		Random random = new Random();
		Set<Integer> decidedNumbers = new HashSet<Integer>();
		while (counter < getNumberofregistrationimages()) {
//		while (counter < 20) {
//			int index = random.nextInt(files.length);
//			this.imageSet.add(files[index]);
//			counter++;
			boolean successfuladdition = false;
			while (!successfuladdition) {
//				int index = random.nextInt(results.size());
				int index = random.nextInt(allImages.size());
				if (decidedNumbers.add(index)) {
					if (counter <= 19) {
						this.listOne.add(allImages.get(index));
						this.screenImages.add(allImages.get(index));
						this.images.add(allImages.get(index));
						otherImages.remove((allImages).get(index));
					} else if (counter >= 19 && counter <= 39) {
						this.listTwo.add(allImages.get(index));
						this.screenImages.add(allImages.get(index));
						this.images.add(allImages.get(index));
						otherImages.remove((allImages).get(index));
					} else if (counter >= 39 && counter <= 59) {
						this.listThree.add(allImages.get(index));
						this.screenImages.add(allImages.get(index));
						this.images.add(allImages.get(index));
						otherImages.remove((allImages).get(index));
					}
//					this.imageSet.add(results.get(index)); 
					
					successfuladdition = true;
				}
			}
			counter++;
//			successfuladdition = false;
		}
//		System.out.println(this.imageSet.size());
//		System.out.println(decidedNumbers.size());
		createOtherImageList();
	}
	/**
	 * Getting a list of key-value (filenames/filepaths) pairs for all images in memory
	 * @return
	 */
//	public List<HashMap<String, String>> returnFiles() {
	public List<Image> returnFiles() {
//		Map<String, String> allFiles = new HashMap<String, String>();
//		List<String> results = new ArrayList<String>();
		List<HashMap<String, String>> results2 = new ArrayList<HashMap<String, String>>();
		List<Image> allImages = new ArrayList<Image>();
		
		String p = "C:\\Users\\woohoo\\eclipse-workspace\\project\\mikons2";
		File[] files = new File(p).listFiles();
		
		for (File file : files) {
			if (file.isFile() && file.getAbsolutePath().endsWith(".gif")) {
//				results.add(file.getName());
				HashMap<String, String> aFile = new HashMap<String, String>();
				aFile.put(file.getName(), file.getPath());
				results2.add(aFile);
				allImages.add(new Image(file.getName(), file.getPath()));
			}
		}
//		return files;
//		return results;
//		return results2;
		return allImages;
	}
	
	
	
	public List<Image> getAllImages() {
		return screenImages;
	}
	
	
	public Set<Map<String, String>> getImageSet() {
		return this.imageSet;
	}
	/**
	 * @return the listOne
	 */
	public List<Image> getListOne() {
		return listOne;
	}
	/**
	 * @param listOne the listOne to set
	 */
	public void setListOne(List<Image> listOne) {
		this.listOne = listOne;
	}
	/**
	 * @return the listTwo
	 */
	public List<Image> getListTwo() {
		return listTwo;
	}
	/**
	 * @param listTwo the listTwo to set
	 */
	public void setListTwo(List<Image> listTwo) {
		this.listTwo = listTwo;
	}
	/**
	 * @return the listThree
	 */
	public List<Image> getListThree() {
		return listThree;
	}
	/**
	 * @param listThree the listThree to set
	 */
	public void setListThree(List<Image> listThree) {
		this.listThree = listThree;
	}
	/**
	 * @return the numberofregistrationimages
	 */
	public static int getNumberofregistrationimages() {
		return NUMBEROFREGISTRATIONIMAGES;
	}
	
	public void createOtherImageList() {
		hiddenImages.addAll(otherImages);
	}
	public List<Image> getHidden() {
		return hiddenImages;
	}
	
	
	/*
	public static void main(String[] args) {
		System.out.println(String.format("%-20s", "FILES"));
		FileNames fnt = new FileNames();
//		fnt.createRegistrationSet();

//		Iterator<String> i = fnt.getImageSet().iterator();
//		int number = 0;
//		Iterator<Map<String, String>> i = fnt.getImageSet().iterator();
//		while (i.hasNext()) {
//			number++;
//			for (String s : i.next().values()) {
//				System.out.println(s);	
//			}
//			
//		}
//		System.out.println(number);
		
		System.out.println(fnt.getListOne().size());
		System.out.println(fnt.getListOne().size());
		System.out.println(fnt.getListOne().size());
		System.out.println(fnt.images.size());
		System.out.println(fnt.otherImages.size());
//		while (i.hasNext()) {
//			String filename = (String) i.next();
//			System.out.println("Image " + counter++ + ": " + filename);
//		}
	}
	*/
}

