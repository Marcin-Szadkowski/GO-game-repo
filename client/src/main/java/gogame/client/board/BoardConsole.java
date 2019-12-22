package gogame.client.board;

public class BoardConsole implements Board {
    public static int size;
	public BoardConsole(int type) {
		if(type == 9) {
			size = 9;
			
		}
		else if(type == 13) {
			size = 13;
			
		}
		else if(type == 19) {
			size = 19;
			
		}
		else {
            throw new IllegalArgumentException("Unknown boardType." + type);
        }
	}
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
