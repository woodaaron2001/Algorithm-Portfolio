package HanoiGame;

//class to deal with discs stored on stack
public class disc {

	//size of disc 
	private int size;
	private int totalSize;
	//max size of disc
	
	//constructor
	public disc(int size,int totalSize) {
		this.size = size;
		this.totalSize = totalSize;
	}
	
	//getter+setter
	public void setSize(int size) {
		this.size = size;
	}
	public int size() {
		return this.size;
	}
	
	
	public String toString() {
		String disc = "";
		
		for(int x = 0;x<size;x++) {
			disc = disc+ "+";
		}
		//we use this here so the disks can be spaced correctly without having a size
		for(int x= 0;  x<totalSize-size;x++) {
			disc = disc + "  ";
		}

		return disc;
	}
	

}
