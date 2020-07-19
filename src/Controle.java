import Download.ClientDown;
import Download.ServDown;

public class Controle {
	public static void main (String [] args ) {
		ServDown servDown = new ServDown();
		new Thread((Runnable) new ClientDown()).start();
	}

}
