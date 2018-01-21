class TripleStep3 {
    public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		System.out.println(calc(n));
	}

	private static int calc(int n) {
		switch (n) {
			case 1: return 1;
			case 2: return 2;
			case 3: return 4;
		}
        int a = 1;  // Result of calc(1)
		int b = 2;  // Result of calc(2)
		int c = 4;  // Result of calc(3)
		for (int i = 4; i <= n; i++) {
			int tmp = a + b + c;
			a = b;
			b = c;
			c = tmp;
		}
		return c;
	}
}

