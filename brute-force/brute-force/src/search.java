import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;

public class search {
	public static int[] shuffle(int[] a, int n, int[][] g) {
		int[] ans = new int[n];		
		Random rnd = new Random();
		HashSet<Integer> chosen = new HashSet<Integer>();
		int l = 0, r = n-1;
		
		while (l < r) {
			int x = rnd.nextInt(n);
			while (chosen.contains(x)) {
				x = rnd.nextInt(n);
			}
			
			int inDegree = 0, outDegree = 0;
			for (int i = 0; i < n; i++) {
				inDegree += g[i][x];
				outDegree += g[x][i];
			}
			if (inDegree <= outDegree) {
				ans[l] = x;
				l += 1;				
			} else {
				ans[r] = x;
				r -= 1;
			}
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
			ord = shuffle(ord, n, a);
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
		int num_iter = 50000;
		
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
