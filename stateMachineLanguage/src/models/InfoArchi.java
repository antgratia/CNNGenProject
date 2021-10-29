package models;

public class InfoArchi {
	
	private static InfoArchi info;
	
	private int maxSizeImg;
	private int currentSizeImg;
	private int nbFilter;
	
	
	private InfoArchi(int maxSizeImg) {
		super();
		this.maxSizeImg = maxSizeImg;
		this.currentSizeImg = maxSizeImg;
	}
	
	public static InfoArchi getInfoArchi() {
		if (info == null) {
			info = new InfoArchi(28);
		}
		return info;
	}


	public int getMaxSizeImg() {
		return maxSizeImg;
	}


	public void setMaxSizeImg(int maxSizeImg) {
		this.maxSizeImg = maxSizeImg;
	}


	public int getCurrentSizeImg() {
		return currentSizeImg;
	}


	public void setCurrentSizeImg(int currentSizeImg) {
		this.currentSizeImg = currentSizeImg;
	}
	
	

}
