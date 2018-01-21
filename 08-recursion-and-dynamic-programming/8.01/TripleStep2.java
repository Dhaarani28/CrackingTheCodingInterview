class TripleStep2 {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.println(calc(n));
	}

	private static int calc(int n) {
		return calcRec(n, new int[n+1]);
	}

	private static int calcRec(int n, int[] memo) {
		switch (n) {
			case 1: return 1;
			case 2: return 2;
			case 3: return 4;
		}
		if (memo[n] == 0) {
			memo[n] = calcRec(n-1, memo) + calcRec(n-2, memo) + calcRec(n-3, memo);
		}
		return memo[n];
	}
}

