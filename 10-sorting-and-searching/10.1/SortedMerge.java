class SortedMerge {

	public static void main(String[] args) {
		int[] b = {1, 2, 79, 356};
		int[] aTmp = { 4, 5, 6, 999};
		int[] a = new int[aTmp.length + b.length];
		for (int i = 0; i < aTmp.length; i++) a[i] = aTmp[i];

		merge(a, b);

        for (int i : a) System.out.print(i + ", ");
		System.out.println();
	}

	private static void merge(int[] a, int[] b) {
		int i = moveToEnd(a, a.length - b.length);
		int j = 0;
		int k = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j])
				a[k] = a[i++];
			else
				a[k] = b[j++];
			k++;
		}
		if (j < b.length)
			moveToEnd(b, j, a);
	}

	private static int moveToEnd(int[] a, int size) {
		for (int i = 0; i < size; i++) {
            a[a.length-1-i] = a[size-1-i];
		}
		return a.length-size;
	}

	private static void moveToEnd(int[] from, int index, int[] to) {
		int n = from.length;
		for (int i = 0; i < n; i++) {
			to[to.length-n+i] = from[index+i];
		}
	}

}

