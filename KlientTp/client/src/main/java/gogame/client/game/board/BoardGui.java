package gogame.client.game.board;

public class BoardGui  {
	
    public static int size;
    public static int stepWidth;
    public static int stepHeight;
	
	 public BoardGui(int type) {
		 
		 if(type==1) {
			 size =9;
			 stepWidth=65;
			 stepHeight=65;
		 }
		 else if(type==2) {
			 size =13;
			 stepWidth=47;
			 stepHeight=47;
		 }
		 else if(type==3) {
			 size =19;
			 stepWidth=33;
			 stepHeight=33;
		 }
		 
	 }

	
	//method returns array of  (x,y) coordinates of each line cross - possible stone position
	

}