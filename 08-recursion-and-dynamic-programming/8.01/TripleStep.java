class TripleStep {
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
		return calc(n-3) + calc(n-2) + calc(n-1);
	}
}

