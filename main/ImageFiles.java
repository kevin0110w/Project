package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This class is responsible for creating a list of 60 images, split into three lists.
 * There are separate lists for holding seen and unseen image file paths that will be used to create the rest of a user's challenge set.
 *
 */
public class ImageFiles {
	private static final int NUMBEROFREGISTRATIONIMAGES = 60;
	private List<String> listOne, listTwo, listThree, seenImages, unseenImages;

	/**
	 * Lists one, two and three will contain 20 image paths each, for each panel in the  registration panel. 
	 * The seen images will also contains these 60 images.
	 * The Unseen images will contain only unseen images. Images from previous registrations attempts will also be removed from this list.
	 * Depending on the login method, either images from the seen or unseen images lists will be used to create the rest of the challenge set
	 */
	public ImageFiles() {
		this.listOne = new ArrayList<String>();
		this.listTwo = new ArrayList<String>();
		this.listThree = new ArrayList<String>();
		this.seenImages = new ArrayList<String>();
		this.unseenImages = new ArrayList<String>();
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
	public int getNumberofregistrationimages() {
		return NUMBEROFREGISTRATIONIMAGES;
	}

	/**
	 * This method will return the file paths of the images from computer memory, depending on which selection is chosen in the GUI
	 * It'll store the list of files from a particular folder as an array of strings before checking that each one is valid and then adding it to a list that'll contain all the paths
	 * @Return List<String> - a list of all file paths of images in a particular folder
	 */
	private List<String> returnFiles(int selection) {
		String path = System.getProperty("user.dir");
		List<String> allImages = new ArrayList<String>();
		path += this.setFolderPath(selection);
		File[] files = new File(path).listFiles();

		for (File file : files) {
			if (file.isFile() && isCorrectFile(file)) {
				allImages.add(file.getAbsolutePath());
			}
		}
		return allImages;
	}

	/**
	 * Returns the specific folder path for a picture selection
	 * @param selection selected in gui
	 * @return the last part of the file path for a particular picture set
	 */
	private String setFolderPath(int selection) {
		String directory = "";
		switch (selection) {
		case 1:
			directory = "\\art";
			break;
		case 2:
			directory = "\\mikons_1";
			break;
		case 3:
			directory = "\\mikons_2";
			break;
		}
		return directory;
	}

	/**
	 * This method will check that a file is valid if it ends in the correct extension
	 * @param file in the file directory
	 * @return whether is a file or not
	 */
	private boolean isCorrectFile(File file) {
		return file.getAbsolutePath().endsWith(".gif") || file.getAbsolutePath().endsWith(".jpeg")
				|| file.getAbsolutePath().endsWith(".jpg");
	}

	/**
	 * This method will create a set of registration images and return it to the model. 
	 * The unseen list will initially be popul
	 * ated with all the images. As each image path is added to either lists one, two or three, that image path will be removed from the list of unseen
	 * images.
	 * If the user has registered before, the image paths that made up the registration screen for that registration will also be removed from the unseen list.
	 * This will ensure that the unseen list will definitely contain completely unseen images and be no duplicates.
	 * A random number is generated to select the index of a list to be used as a registration image, a set is used to hold these chosen indexes to ensure no duplicates.
	 * @param pictureSet - the picture set chosen for this registration
	 * @param alternativeLoginSeenImages - a list of images if this particular user has registered before
	 */
	public void createRegistrationSet(int pictureSet, Set<String> alternativeLoginSeenImages) {
		List<String> allImages = new ArrayList<String>();
		allImages = returnFiles(pictureSet);
		allImages.removeAll(alternativeLoginSeenImages);
		this.unseenImages.addAll(allImages);
		int counter = 0;
		Random random = new Random();
		Set<Integer> chosenNumbers = new HashSet<Integer>(); // to ensure no duplication
		while (counter < getNumberofregistrationimages()) {
			int index = random.nextInt(allImages.size());
			if (chosenNumbers.add(index)) {
				if (counter <= 19) {
					this.listOne.add(allImages.get(index));
				} else if (counter >= 20 && counter <= 39) {
					this.listTwo.add(allImages.get(index));
				} else if (counter >= 40 && counter <= 59) {
					this.listThree.add(allImages.get(index));
				}
				this.seenImages.add(allImages.get(index));
				this.unseenImages.remove(allImages.get(index));
				counter++;
			}
		}
	}
}