import java.util.*;
public class BuildingTowersEasy {
	private static final int MAX_BLDG_HEIGHT = 100000 - 1;
	public int maxHeight(int N, int[] x, int[] t) {
		int[][] dp = new int[x.length + 1][N];
		for (int idx = 0; idx < N; idx++) {
			dp[0][idx] = idx;
		}
		for (int cidx = 1; cidx <= t.length; cidx++) {
			int b = x[cidx - 1] - 1; // One indexed to Zero indexed
			int h = t[cidx - 1];
			dp[cidx][b] = Math.min(dp[cidx - 1][b], h);
			for (int bidx = b + 1; bidx < N; bidx++) {
				dp[cidx][bidx] = dp[cidx][bidx - 1] + 1;
			}
			for (int bidx = b - 1; bidx >= 0; bidx--) {
				int dy = dp[cidx - 1][bidx] - dp[cidx][bidx + 1];
				if (dy > 1) {
					dp[cidx][bidx] = dp[cidx][bidx + 1] + 1;
				}
				else {
					dp[cidx][bidx] = dp[cidx - 1][bidx];
				}
			}
		}
		return Arrays.stream(dp[dp.length - 1]).max().getAsInt();
	}
}