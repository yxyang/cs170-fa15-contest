import java.util.Random;

public class temp {
	public static void main(String[] args) {
		Random rnd = new Random();
		int n = 100;
		int[][] g = new int[n][n];
		int[] ord = new int[n];
		System.out.println(1);
		System.out.println(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (rnd.nextBoolean()) {
					g[i][j] = 1;
				} else {
					g[i][j] = 0;
				}
			}
			ord[i] = i;
		}
		
		for (int i = 0; i < n; i++) {
			int ind = rnd.nextInt(100);
			int t = ord[i];
			ord[i] = ord[ind];
			ord[ind] = t;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(g[ord[i]][ord[j]] + " ");
			}
			System.out.println();
		}
	}
}
