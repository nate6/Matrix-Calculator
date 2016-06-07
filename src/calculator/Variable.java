package calculator;

public class Variable {
	private char variable;
	private int constant;
	private int power;
	
	public Variable(char var) {
		variable = var;
		constant = 1;
		power = 1;
	}
	
	public void setConstant(int con) {
		constant = con;
	}
	
	public void multiply(int val) {
		constant *= val;
	}

	public void power(int pow) {
		power *= pow;
		Math.pow(constant, pow);
	}
	
	public int equate(int value) {
		Math.pow(value, power);
		return constant * value;
	}
	
	public Fraction equate(Fraction value) {
		Math.pow(value.getTop(), power);
		Math.pow(value.getBottom(), power);
		//TODO check if there is a Fraction constant or Root constant.
		return new Fraction(constant*value.getTop(), value.getBottom());
	}
	
	public int equate(Root value) {
		//TODO check if there is a Fraction constant or Root constant.
		if (power%2 == 0) {
			if (power > 2 && value.getRootPower()%2 == 0) {
				Math.pow(value.root(), power-2); //TODO reduce method in Root for power operations.
				return constant * value.root();
			}
			else if (power == 2) {
				
			}
		}
		
		return 0;
	}
	
	public char getVariableName() {
		return variable;
	}
	
	public int getConstant() {
		return constant;
	}
	
	public int getPower() {
		return power;
	}
}
