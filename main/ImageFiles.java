package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This class is responsible for creating a list of 60 images, split into three
 * lists long with the decoy images for a user doing the registration process.
 *
 */
public class ImageFiles {
	private static final int NUMBEROFREGISTRATIONIMAGES = 60;
	private List<String> listOne, listTwo, listThree, seenImages, unseenImages;

	/*
	 * Each separate list should contain 20 images for each panel in the
	 * registration selection phase Screen images will contain the 60 images that
	 * the user has seen The Unseen images will contain the remainder from the
	 * directory that the user hasn't seen Depending on the login method, either
	 * Seen or Unseen images will be used to create the decoy set
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

	/*
	 * This method will return the file paths of the images from computer memory,
	 * depending on which selection is chosen in the GUI
	 * It'll store the list of files in a particular folder as an array of strings before checking that each one is valid and then adding it to a list
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
			directory = "\\mikons";
			break;
		case 3:
			directory = "\\doodle";
			break;
		}
		return directory;
	}

	/**
	 * Check that a file is valid if it ends in the correct extension
	 * @param file in the file directory
	 * @return whether is a file or not
	 */
	private boolean isCorrectFile(File file) {
		return file.getAbsolutePath().endsWith(".gif") || file.getAbsolutePath().endsWith(".jpeg")
				|| file.getAbsolutePath().endsWith(".jpg");
	}

	/**
	 * This method will create a set of registration images and return it to the model.
	 * It'll also add images to a list of seen images and remove the image from an unseen list as these will be used to create the decoy sets
	 * @param pictureSet
	 * @param alternativeLoginSeenImages
	 */
	public void createRegistrationSet(int pictureSet, List<String> alternativeLoginSeenImages) {
		List<String> allImages = new ArrayList<String>();
		allImages = returnFiles(pictureSet);
		this.unseenImages.addAll(allImages); // add all images to the unseen list and remove the seen ones from this
												// list
		int counter = 0;
		Random random = new Random();
		Set<Integer> decidedNumbers = new HashSet<Integer>(); // to ensure no duplication
		while (counter < getNumberofregistrationimages()) {
			int index = random.nextInt(allImages.size());
			if (decidedNumbers.add(index) && !alternativeLoginSeenImages.contains(allImages.get(index))) {
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
		this.unseenImages.removeAll(alternativeLoginSeenImages);
	}
}