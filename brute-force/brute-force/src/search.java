import java.util.Scanner;
import java.util.Random;

public class search {
	public static int[] shuffle(int[] a, int n) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = i;
		}
		
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			int ind = rnd.nextInt(i+1);
			int t = ans[i];
			ans[i] = ans[ind];
			ans[ind] = t;
		}
		return ans;
	}
	
	public static int[] run(int n, int[][] a, int num_iter) {
		int[] ord = new int[n];
		for (int i = 0; i < n; i++) {
			ord[i] = i;
		}
		
		int[] best_ord = null;
		int max = 0;
		for (int i = 0; i < num_iter; i++) {
			ord = shuffle(ord, n);
			int sum = 0;
			for (int x = 0; x < n; x++) {
				for (int y = x+1; y < n; y++) {
					sum += a[ord[x]][ord[y]];
				}
			}
			if (sum > max) {
				max = sum;
				best_ord = ord;
			}
		}
		System.out.println("Max value is: " + max);
		return best_ord;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num_data = input.nextInt();
		int num_iter = 500000;
		
		for (int i = 0; i < num_data; i++) {
			int num_ver = input.nextInt();
			int[][] a = new int[num_ver][num_ver];
			for (int x = 0; x < num_ver; x++) {
				for (int y = 0; y < num_ver; y++) {
					a[x][y] = input.nextInt();
				}
			}
			int[] ans = run(num_ver, a, num_iter);
			for (int k = 0; k < num_ver; k++) {
				System.out.print((1 + ans[k]) + " ");
			}
			System.out.println();
		}
		input.close();
	}
}
