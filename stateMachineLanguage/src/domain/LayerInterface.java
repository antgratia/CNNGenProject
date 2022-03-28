package domain;

public interface LayerInterface {

	int getLayerPos();

	void setLayerPos(int i);

	void setLast(boolean b);

	void setNbFilter(int inputFilter);

	void setImgSize(int maxSizeImg);

	int getNbFilter();

	int getImgSize();

	void setReduction(boolean b);

	boolean isReduction();

	boolean isLast();

	
}
