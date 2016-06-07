package calculator;

public class Calculator {
	
	public Root vectorMagnitude(int[] vector) {
		int squared = (int)(Math.pow(vector[0], 2)
	   			 		  + Math.pow(vector[1], 2)
	   			          + Math.pow(vector[2], 2));
		Root root = new Root(squared);
		return root;
	}

	public int vectorDotProduct(int[] vectorA, int[] vectorB) {
		int dot = 0;
		int length = vectorA.length > vectorB.length ? 
					 vectorB.length : vectorA.length;
		for (int i = 0; i < length; i++) {
			dot += vectorA[i]*vectorB[i];
		}
		return dot;
	}

	public int[] vectorCrossProduct(int[] vectorA, int[] vectorB) {
		int[] cross = new int[3];
		cross[0] = vectorA[1]*vectorB[2] - vectorA[2]*vectorB[1];
		cross[1] = (vectorA[0]*vectorB[2] - vectorA[2]*vectorB[0])*-1;
		cross[2] = vectorA[0]*vectorB[1] - vectorA[1]*vectorB[0];
		return cross;
	}

	public Fraction[] vectorProjection(int[] vectorA, int[] vectorB) {
		Fraction[] proj = new Fraction[vectorB.length];
		int uDotv = vectorDotProduct(vectorA, vectorB);
		int vDotv = vectorDotProduct(vectorB, vectorB);
		
		for (int i = 0; i < vectorB.length; i++) {
			Fraction frac = new Fraction(uDotv*vectorB[i], vDotv);
			proj[i] = frac;
		}
		return proj;
	}
	
	public Fraction vectorScalar(int[] vectorA, int[] vectorB) {
		int uDotv = vectorDotProduct(vectorA, vectorB);
		Root scal = vectorMagnitude(vectorB);
		Fraction frac = new Fraction(uDotv, scal);
		return frac;
	}
	
	public Fraction vectorCosAngle(int[] vectorA, int[] vectorB) {
		int uDotv = vectorDotProduct(vectorA, vectorB);
		Fraction frac = new Fraction(uDotv, 1);
		Root u = vectorMagnitude(vectorA);
		Root v = vectorMagnitude(vectorB);
		frac.multiply(false, u);
		frac.multiply(false, v);
		return frac;
	}

	public Fraction vectorSinAngle(int[] vectorA, int[] vectorB) {
		Root vectorCrossMagnitude = vectorMagnitude(vectorCrossProduct(vectorA, vectorB));
		Fraction frac = new Fraction(vectorCrossMagnitude, 1);
		Root u = vectorMagnitude(vectorA);
		Root v = vectorMagnitude(vectorB);
		frac.multiply(false, u);
		frac.multiply(false, v);
		return frac;
	}

}
