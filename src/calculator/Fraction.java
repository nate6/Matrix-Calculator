package calculator;

public class Fraction {
	private int numerator = 1;
	private int denominator = 1;
	private Root rootNumer;
	private Root rootDenom;

	public Fraction(int n, int d) {
		numerator = n;
		denominator = d;
		rootNumer = new Root(-1);
		rootDenom = new Root(-1);
		reduce();
	}
	
	public Fraction(int n, Root d) {
		numerator = n;
		rootDenom = d;
		rootNumer = new Root(-1);
		reduce();
	}
	
	public Fraction(Root n, int d) {
		rootNumer = n;
		denominator = d;
		rootDenom = new Root(-1);
		reduce();
	}
	
	public Fraction(Root n, Root d) {
		rootNumer = n;
		rootDenom = d;
		reduce();
	}

	public String getString() {
		if (denominator == 0) return "cannot divide by 0.";
		else if ((numerator*-rootNumer.root())%(denominator*-rootDenom.root()) == 0){ 
			return numerator/denominator + "";
		}
		return "<font size=-.7><sup>" + setString(numerator) + rootNumer.getString() + 
			   "</sup><font size=+1>/<font size=-.7><sub>" + setString(denominator) + 
			   rootDenom.getString() + "</sub><font size=+1>";
	}
	
	public String setString(int num) {
		return num==1 ? "" : num+"";
	}
	
	public int getTop() {
		return numerator;
	}
	
	public int getBottom() {
		return denominator;
	}
	
	public void reduce() {
		if (rootNumer.root() >= 0 && (int)(Math.sqrt(rootNumer.root())
				*Math.sqrt(rootNumer.root())) == rootNumer.root()) {
			numerator *= Math.sqrt(rootNumer.root());
			rootNumer = new Root(-1);
		}
		if (rootDenom.root() >= 0 && (int)(Math.sqrt(rootDenom.root()))
				*(int)(Math.sqrt(rootDenom.root())) == rootDenom.root()) {
			denominator *= Math.sqrt(rootDenom.root());
			rootDenom = new Root(-1);
		}
		
		int n = denominator;
		while (n != 1) {
			if (numerator%n == 0 && denominator%n == 0) {
				numerator /= n;
				denominator /= n;
			}
			n--;
		}
		
		if (denominator < 0) {
			numerator *= -1; 
			denominator *= -1;
		}
	}

	public Fraction negate() {
		numerator *= -1;
		return this;
	}

	public void multiply(boolean b, int root) {
		if (b) numerator *= root;
		else denominator *= root;
		reduce();
	}
	
	public void multiply(boolean b, Root root) {
		if (b) rootNumer.multiply(root);
		else rootDenom.multiply(root);
		reduce();
	}

	public double toDouble() {
		double div = 1d*numerator/denominator;
		if (rootNumer.root() < 0) {
			if (rootDenom.root() < 0) return div;
			return div / Math.sqrt(rootDenom.root());
		}
		if (rootDenom.root() < 0) return div * Math.sqrt(rootNumer.root());
		return div * Math.sqrt(rootNumer.root()) / Math.sqrt(rootDenom.root());
	}
}
