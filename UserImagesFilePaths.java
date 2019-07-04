import java.util.ArrayList;
import java.util.List;

public class UserImagesFilePaths {
	private List<String> images;
	public UserImagesFilePaths() {
		this.images = new ArrayList<String>();
	}
	/**
	 * @return the images
	 */
	public List<String> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(List<String> images) {
		this.images = images;
	}
	
	public void addImage(String filePath) {
		getImages().add(filePath);
	}

}
