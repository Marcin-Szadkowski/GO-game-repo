package gogame.client.board;

public class BoardGui implements Board {

	public static int size;
	public static int stepWidth;
	public static int stepHeight;
	
	public BoardGui(int type) {
		if(type == 9) {
			size = 8;
			stepWidth=65;
			stepHeight=65;
		}
		else if(type == 13) {
			size = 12;
			stepWidth=47;
			stepHeight=47;
		}
		else if(type == 19) {
			size = 18;
			stepWidth=33;
			stepHeight=33;
		}
		 else {
	            throw new IllegalArgumentException("Unknown boardType." + type);
	        }
	}
	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	public int stepWidth() {
		// TODO Auto-generated method stub
		return stepWidth;
	}
	public int stepHeight() {
		// TODO Auto-generated method stub
		return stepHeight;
	}

	
	
   

}