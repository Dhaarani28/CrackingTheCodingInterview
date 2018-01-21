import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class Parens {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Set<String> parens = parens(n);

        int i = 1;
		for (String s : parens) {
            System.out.println(i + ": " + s);
            i++;
        }
	}

      private static Set<String> parens(int n) {
        // Base case
		if (n == 0) return new HashSet<>(Arrays.asList(""));
        // Recursion
		Set<String> setPrev = parens(n-1);
		Set<String> setNew = new HashSet<>();
		for (String prev : setPrev) {
			for (int i = 0; i <= 2*(n-1); i++) {
			    StringBuilder sb = new StringBuilder(2*n);
				sb.append(prev.substring(0, i));
				sb.append("()");
				sb.append(prev.substring(i));
                setNew.add(sb.toString());  // Adds only if not already exists
			}
		}
		return setNew;
	}
}

