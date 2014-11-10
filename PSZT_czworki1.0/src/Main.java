import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.Controller;

public class Main {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Controller());
	}
}
