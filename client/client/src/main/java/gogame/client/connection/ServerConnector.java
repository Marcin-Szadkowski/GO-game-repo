package gogame.client.connection;

  interface ServerConnector {
  int[] move(int[] x);
  boolean quit(boolean x);
  boolean pass(boolean x);
  int size(int x);
  int[] you_moved(int[] x);
  int your_turn(int x);
  int[][] delete(int[][] x);
  boolean onther_player_left(boolean x);
  boolean decide(boolean x);
 
}
