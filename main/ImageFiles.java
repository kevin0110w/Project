package main;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This class is responsible for creating a list of 60 images, split into three lists long with the decoy images for a user doing the registration process.
 *
 */
public class ImageFiles {
	private static final int NUMBEROFREGISTRATIONIMAGES = 60;
	private List<String> listOne, listTwo, listThree, seenImages, unseenImages;
	
	/*
	 * Each separate list should contain 20 images for each panel in the registration selection phase
	 * Screen images will contain the 60 images that the user has seen
	 * The Unseen images will contain the remainder from the directory that the user hasn't seen
	 * Depending on the login method, either Seen or Unseen images will be used to create the decoy set
	 */
	public ImageFiles() {
		this.listOne = new ArrayList<String>();
		this.listTwo = new ArrayList<String>();
		this.listThree = new ArrayList<String>();
		this.seenImages = new ArrayList<String>();
		this.unseenImages = new ArrayList<String>();
	}

	/**
	 * Create the 60 images to display on the registration screen
	 * @param selection - picture set selection
	 */
	public void createRegistrationSet(int selection) {
		List<String> allImages = new ArrayList<String>();
		allImages = returnFiles(selection);
		this.unseenImages.addAll(allImages); // add all images to the unseen list and remove the seen ones from this list
		int counter = 0;
		Random random = new Random();
		Set<Integer> decidedNumbers = new HashSet<Integer>(); // to ensure no duplication
		while (counter < getNumberofregistrationimages()) {
			boolean successfuladdition = false;
			while (!successfuladdition) {
				int index = random.nextInt(allImages.size());
				if (decidedNumbers.add(index)) {
					if (counter <= 19) {
						this.listOne.add(allImages.get(index));
					} else if (counter >= 20 && counter <= 39) {
						this.listTwo.add(allImages.get(index));
					} else if (counter >= 40 && counter <= 59) {
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
		
		// store the list of files in the directory as an array of strings
		File[] files = new File(path).listFiles();
		
		// check if the file is a correct image file then add it to the list containing all image file paths
		for (File file : files) {
			if (file.isFile() && isCorrectFile(file)) {
				allImages.add(file.getAbsolutePath());
			}
		}
		return allImages;
	}
	
	/**
	 * Check that a file is valid if it ends in the correct extension
	 * @param file in the file directory
	 * @return whether is a file or not
	 */
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
}