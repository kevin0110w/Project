import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FileNames2 {
	private static final int NUMBEROFREGISTRATIONIMAGES = 60;
	private List<String> listOne, listTwo, listThree, seenImages, unseenImages;
	
	/*
	 * Each separate list should contain 20 images for each panel in the registration selection phase
	 * Screen images will contain the 60 images that the user has seen
	 * The Unseen images will contain the remainder from the directory that the user hasn't seen
	 * Depending on the login method, either Screen or Unseen images will be used to create the decoy set
	 */
	public FileNames2() {
		this.listOne = new ArrayList<String>();
		this.listTwo = new ArrayList<String>();
		this.listThree = new ArrayList<String>();
		this.seenImages = new ArrayList<String>();
		this.unseenImages = new ArrayList<String>();
	}

	public void createRegistrationSet(int selection) {
		List<String> allImages = new ArrayList<String>();
		allImages = returnFiles(selection);
		this.unseenImages.addAll(allImages);
		int counter = 0;
		Random random = new Random();
		Set<Integer> decidedNumbers = new HashSet<Integer>();
		while (counter < getNumberofregistrationimages()) {
			boolean successfuladdition = false;
			while (!successfuladdition) {
				int index = random.nextInt(allImages.size());
				if (decidedNumbers.add(index)) {
					if (counter <= 19) {
						this.listOne.add(allImages.get(index));
					} else if (counter >= 19 && counter <= 39) {
						this.listTwo.add(allImages.get(index));
					} else if (counter >= 39 && counter <= 59) {
						this.listThree.add(allImages.get(index));
					}
					this.seenImages.add(allImages.get(index));
					this.unseenImages.remove((allImages).get(index));
					successfuladdition = true;
				}
			}
			counter++;
		}
	}
	
	/*
	 * This method will return the file paths of the images from memory, depending on which selection is chosen
	 */
	private List<String> returnFiles(int selection) {
		String path = null;
		List<String> allImages = new ArrayList<String>();
		switch (selection) {
		case 1:
			path = "C:\\Users\\woohoo\\eclipse-workspace\\project\\art";
			break;
		case 2:
			path = "C:\\Users\\woohoo\\eclipse-workspace\\project\\mikons1";
			break;
		case 3:
			path = "C:\\Users\\woohoo\\eclipse-workspace\\project\\mikons2";
			break;
		}
		
		File[] files = new File(path).listFiles();
		
		for (File file : files) {
			if (file.isFile() && isCorrectFile(file)) {
				allImages.add(file.getAbsolutePath());
			}
		}
		return allImages;
	}
	
	public boolean isCorrectFile(File file) {
		return file.getAbsolutePath().endsWith(".gif") || file.getAbsolutePath().endsWith(".jpeg") || file.getAbsolutePath().endsWith(".jpg"); 
	}

	/**
	 * @return the listOne
	 */
	public List<String> getListOne() {
		return listOne;
	}

	/**
	 * @param listOne the listOne to set
	 */
	public void setListOne(List<String> listOne) {
		this.listOne = listOne;
	}

	/**
	 * @return the listTwo
	 */
	public List<String> getListTwo() {
		return listTwo;
	}

	/**
	 * @param listTwo the listTwo to set
	 */
	public void setListTwo(List<String> listTwo) {
		this.listTwo = listTwo;
	}

	/**
	 * @return the listThree
	 */
	public List<String> getListThree() {
		return listThree;
	}

	/**
	 * @param listThree the listThree to set
	 */
	public void setListThree(List<String> listThree) {
		this.listThree = listThree;
	}

	/**
	 * @return the screenImages
	 */
	public List<String> getSeenImages() {
		return seenImages;
	}

	/**
	 * @param screenImages the screenImages to set
	 */
	public void setSeenImages(List<String> seenImages) {
		this.seenImages = seenImages;
	}

	/**
	 * @return the unseenImages
	 */
	public List<String> getUnseenImages() {
		return unseenImages;
	}

	/**
	 * @param unseenImages the unseenImages to set
	 */
	public void setUnseenImages(List<String> unseenImages) {
		this.unseenImages = unseenImages;
	}

	/**
	 * @return the numberofregistrationimages
	 */
	public static int getNumberofregistrationimages() {
		return NUMBEROFREGISTRATIONIMAGES;
	}
	
	public static void main(String[] args) {
		FileNames2 fnt = new FileNames2();
		fnt.createRegistrationSet(3);
		System.out.println(fnt.getListOne().size());
		System.out.println(fnt.getListTwo().size());
		System.out.println(fnt.getListThree().size());
		System.out.println(fnt.getSeenImages().size());
		System.out.println(fnt.getUnseenImages().size());
	}
}
