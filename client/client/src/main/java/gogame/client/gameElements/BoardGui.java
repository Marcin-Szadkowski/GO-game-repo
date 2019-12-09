package gogame.client.gameElements;


public class BoardGui  {
	//public int widthStep=49;
	//public int heightStep=49;
	//public int size=13;
	
    public static int size;
    public static int stepWidth;
    public static int stepHeight;
	
	 public BoardGui(String type) {
		 if(type.contentEquals("Board9")) {
			 size =9;
			 stepWidth=70;
			 stepHeight=70;
		 }
		 else if(type.contentEquals("Board13")) {
			 size =13;
			 stepWidth=49;
			 stepHeight=49;
		 }
		 else if(type.contentEquals("Board19")) {
			 size =19;
			 stepWidth=33;
			 stepHeight=33;
		 }
		 
	 }

	
	//method returns array of  (x,y) coordinates of each line cross - possible stone position
	

}
