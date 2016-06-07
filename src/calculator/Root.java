package calculator;

public class Root {
	private int root;
	private int rootPower = 2;
	
	public Root(int r) {
		root = r;
	}
	
	public String getString() {
		if (root == -1) return "";
		else if ((int)(Math.sqrt(root))*(int)(Math.sqrt(root)) == root) return root + "";
		return "&radic;<span style='text-decoration: overline'>" + root + "</span>";
	}
	
	public int root() {
		return root;
	}

	public void multiply(Root newRoot) {
		if (root == -1) root = 1;
		root *= newRoot.root();
	}
	
	public void setRootPower(int pow) {
		rootPower = pow;
	}
	
	public int getRootPower() {
		return rootPower;
	}
}
