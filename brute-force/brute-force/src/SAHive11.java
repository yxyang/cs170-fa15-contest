import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class SAHive11 {	
	static int tot_edges;
	
	public static int calc_sum(int[] perm, int[][] g, int n) {
		/*for (int i = 0; i < n; i++) {
			System.out.print(perm[i] + " ");
		}
		System.out.println();*/
		int sum = 0;
		for (int x = 0; x < n; x++) {
			for (int y = x+1; y < n; y++) {
				sum += g[perm[x]][perm[y]];
			}
		}
		return sum;
	}
	
	public static int calc_delta(int[] perm, int[][] g, int n, int x, int y) {
		/*for (int i = 0; i < n; i++) {
			System.out.print(perm[i] + " ");
		}
		System.out.println();*/
		int ans = 0;
		for (int i = x; i <= y; i++) {
			ans = ans + g[perm[y]][perm[i]] - g[perm[x]][perm[i]];
		}
		return ans;
	}
	
	public static double calc_prob(double temp, int curr_sum, int new_sum) {
		if (new_sum > curr_sum) return 1;
		double x = (new_sum - curr_sum) * 2;
		//temp = Math.sqrt(temp);
		double sig = 1 / (1 + Math.exp(-x));
		//System.out.println(x);
		//System.out.println(sig * temp * 2);
		return sig * temp;
	}
	
	public static int[] run(int num_ver, int[][] a, int num_iter, int[] ord) {
		Random rnd = new Random();
		//System.out.println("before, Max value is: " + calc_sum(ord, a, num_ver));
		
		int curr_sum = calc_sum(ord, a, num_ver);
		for (int i = num_iter; i >= 0; i--) {
			int x = rnd.nextInt(num_ver - 1);
			int y = x + 1;
			while (x == y) {
				y = rnd.nextInt(num_ver);
			}
			int new_sum = calc_delta(ord, a, num_ver, x, y) + curr_sum;
			int t = ord[x]; ord[x] = ord[y]; ord[y] = t;
			double temp = ((double) i) / ((double) num_iter);
			//System.out.println("new: " + new_sum + " old: " + curr_sum);
			//System.out.println("prob: " + calc_prob(temp, curr_sum, new_sum));
			if (rnd.nextDouble() < calc_prob(temp, curr_sum, new_sum)) {
				curr_sum = new_sum;				
			} else {
				t = ord[x]; ord[x] = ord[y]; ord[y] = t;
			}
		}

		//System.out.println("After, Max value is: " + curr_sum);
		return ord;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner input = new Scanner(System.in);
		int num_data = 621;
		int num_iter = 600000000;
		File initF = new File("./sate.out");
		Scanner init = new Scanner(initF);
		Random rnd = new  Random();
		
		for (int i = 1; i <= num_data; i++) {
			File file = new File("./instances/" + i + ".in");
			Scanner input = new Scanner(file);
			int num_ver = input.nextInt();
			int[][] a = new int[num_ver + 2][num_ver + 2];
			tot_edges = 0;
			for (int x = 0; x < num_ver; x++) {
				for (int y = 0; y < num_ver; y++) {
					a[x][y] = input.nextInt();
					tot_edges += a[x][y];
				}
			}
			
			int[] ori = new int[num_ver];
			for (int x = 0; x < num_ver; x++) {
				ori[x] = init.nextInt() - 1;
			}
			
			/*for (int x = 0; x < num_ver; x++) {
				int ind = rnd.nextInt(num_ver);
				int t = ori[x];
				ori[x] = ori[ind];
				ori[ind] = t;
			}*/
			int[] ans = run(num_ver, a, num_iter, ori);
			for (int k = 0; k < num_ver; k++) {
				System.out.print((1 + ans[k]) + " ");
			}
			System.out.println();
			
			input.close();
			
		}
		init.close();
		//input.close();
	}
}
