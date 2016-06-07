package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = 8171459631556961069L;
	private Calculator calc;
	private JPanel background;
	private JComboBox<String> dropBox;
	private JButton calculate;
    private JTextField[] matrixBoxes;
    private JLabel vectorDot2, vectorCross2, vectorProj, vectorScal,
    			   vectorAngCos, vectorAngSin;
    private int operation;
    private DecimalFormat df;
    
    public Window() {
    	setTitle("Matrix Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(370, 320);
        
        background = new JPanel();
        background.setBounds(0, 0, 500, 300);
        background.setBackground(new Color(70, 90, 110));
        background.setLayout(null);
        int[] bounds = setMatrixSize(2, 3);
        setCommas(); //TODO
        setOpDisplay(0);
        
        calculate = new JButton("Calculate!");
        calculate.setBounds(bounds[0]+75, bounds[1], 100, 25);
        calculate.addActionListener(this);
        add(calculate);
        
        setDropBox();
        
        add(background);
        setVisible(true);
        
        calc = new Calculator();
        df = new DecimalFormat("#.####");
    }
    
    public int[] setMatrixSize(int num, int size) {
    	int total = num*size;
		int w = 0, j = 0;
    	matrixBoxes = new JTextField[total];
    	for (int i = 0; i < total; i++) {    		
    		matrixBoxes[i] = new JTextField();
    		if (i == total/2 && i != 0) {
    			w += 50;
    			j = 0;
    		}
    		matrixBoxes[i].setBounds(65+45*j, 100+w, 30, 25);
    		j++;
    	}
    	int[] bounds = new int[2];
    	bounds[0] = matrixBoxes[matrixBoxes.length-1].getX();
    	bounds[1] = matrixBoxes[matrixBoxes.length-1].getY();
    	return bounds;
    }
    
    public void setCommas() {
    	int size = matrixBoxes.length;
    	String text;
		JLabel[] commas = new JLabel[size];
    	String[] names = {"u", "v", "w", "x", "y", "z"};
    	int time = 0; //TODO get rid of?
		int w = 0, j = 0;
    	for (int i = 0; i < size; i++) {
    		text = ",";
    		commas[i] = new JLabel();
    		commas[i].setFont(new Font("Calibri", Font.PLAIN, 20));
    		commas[i].setForeground(Color.white);
    		if (i == size/2 && i != 0) {
    			w = 50;
    			j = 0;
    		}
    		else if (i == 2 || i == 5) text = ">";
    		commas[i].setBounds(100+45*j, 102+w, 30, 25);
    		commas[i].setText(text);
    		background.add(commas[i]);
    		j++;
    		
    		if (i%(size/2) == 0) { //TODO fix
    			JLabel name = new JLabel(names[time] + " = <");
    			name.setFont(new Font("Calibri", Font.PLAIN, 20));
    			name.setForeground(Color.white);
    			name.setBounds(20, 104+w, 60, 20);
    			background.add(name);
    			time++;
    		}
    		background.add(matrixBoxes[i]);
    	}
    }
    
    public void setDropBox() {
    	dropBox = new JComboBox<String>();
    	dropBox.addItem("2 Vector Dot Product");
    	dropBox.addItem("2 Vector Cross Product");
    	dropBox.addItem("Vector Projection");
    	dropBox.addItem("Vector Scalar");
    	dropBox.addItem("Vector Cosine Angle");
    	dropBox.addItem("Vector Sine Angle");
    	dropBox.setBounds(20, 40, 200, 20);
    	dropBox.addActionListener(this);
    	background.add(dropBox);
    }
    
    public void setOpDisplay(int op) { //TODO
    	operation = op;
    	switch (op) {
    	case 0: //Dot
    		vectorDot2 = new JLabel("u · v = ");
    		vectorDot2.setFont(new Font("Calibri", Font.PLAIN, 20));
    		vectorDot2.setForeground(Color.white);
    		vectorDot2.setBounds(20, 200, 500, 40);
			background.add(vectorDot2);
			break;
    	case 1: //Cross
    		vectorCross2 = new JLabel("u × v = ");
    		vectorCross2.setFont(new Font("Calibri", Font.PLAIN, 20));
    		vectorCross2.setForeground(Color.white);
    		vectorCross2.setBounds(20, 200, 500, 40);
			background.add(vectorCross2);
    		break;
    	case 2: //Proj
    		vectorProj = new JLabel("<html><body>proj<sub>v</sub>u = </html></body>");
    		vectorProj.setFont(new Font("Calibri", Font.PLAIN, 20));
    		vectorProj.setForeground(Color.white);
    		vectorProj.setBounds(20, 200, 500, 40);
			background.add(vectorProj);
    		break;
    	case 3: //Scal
    		vectorScal = new JLabel("<html><body>scal<sub>v</sub>u = </html></body>");
    		vectorScal.setFont(new Font("Calibri", Font.PLAIN, 20));
    		vectorScal.setForeground(Color.white);
    		vectorScal.setBounds(20, 200, 500, 40);
			background.add(vectorScal);
    		break;
    	case 4: //Angle from cos
    		vectorAngCos = new JLabel("<html><body>&theta; = </html></body>");
    		vectorAngCos.setFont(new Font("Calibri", Font.PLAIN, 20));
    		vectorAngCos.setForeground(Color.white);
    		vectorAngCos.setBounds(20, 200, 500, 40);
			background.add(vectorAngCos);
    		break;
    	case 5: //Angle from sin
    		vectorAngSin = new JLabel("<html><body>&theta; = </html></body>");
    		vectorAngSin.setFont(new Font("Calibri", Font.PLAIN, 20));
    		vectorAngSin.setForeground(Color.white);
    		vectorAngSin.setBounds(20, 200, 500, 40);
			background.add(vectorAngSin);
    		break;
    	}
    }
    
    public String vectorOp(int op) {
    	String result = "";
    	int[] vectorA;
    	int[] vectorB;
    	vectorA = new int[matrixBoxes.length/2];
		vectorB = new int[matrixBoxes.length/2];
		for (int i = 0; i < matrixBoxes.length; i++) {
			if (matrixBoxes.length/2 <= i && i != 0) {
				if (matrixBoxes[i].getText() == "") vectorB[i] = 0;//TODO fix by creating TextBox class to override getText()
				else vectorB[i-matrixBoxes.length/2] = Integer.parseInt(matrixBoxes[i].getText());
			}
			else {
				if (matrixBoxes[i].getText() == null) vectorA[i] = 0;//TODO fix
				else vectorA[i] = Integer.parseInt(matrixBoxes[i].getText());
			}
		}
		
    	switch (op) {
    	case 0: //Dot
    		result = calc.vectorDotProduct(vectorA, vectorB) + "";
    		break;
    	case 1: //Cross2
    		int[] cross = calc.vectorCrossProduct(vectorA, vectorB);
    		result = "&#65308; " + cross[0] + ", " + cross[1] + ", " + cross[2] + "&#65310;";
    		break;
    	case 2: //proj
    		Fraction[] proj = calc.vectorProjection(vectorA, vectorB);
    		result = "&#65308; " + proj[0].getString() + " , " + proj[1].getString() + " , " 
    				+ proj[2].getString() + " &#65310;";
    		break;
    	case 3: //scal
    		result = calc.vectorScalar(vectorA, vectorB).getString() + "";
    		break;
    	case 4: //angle from cos
    		Fraction cosAng = calc.vectorCosAngle(vectorA, vectorB);
    		double cosAngRadians = Math.acos(cosAng.toDouble());
    		double cosAngDegrees = cosAngRadians * (180/Math.PI);
    		result = "cos<sup><font size=-.7>-1</sup>( " + cosAng.getString() 
    				+ " )  or  " + df.format(cosAngRadians) + "  or  " 
    				+ df.format(cosAngDegrees) + "&#176";
    		break;
    	case 5: //angle from sin
    		Fraction sinAng = calc.vectorSinAngle(vectorA, vectorB);
    		double sinAngRadians = Math.asin(sinAng.toDouble());
    		double sinAngDegrees = sinAngRadians * (180/Math.PI);
    		result = "sin<sup><font size=-.7>-1</sup>( " + sinAng.getString() 
    				+ " )  or  " + df.format(sinAngRadians) + "  or  " 
    	    		+ df.format(sinAngDegrees) + "&#176";
    		break;
    	}
    	return result;
    }
    
    public void repaint() {
    	background.removeAll();
    	background.add(dropBox);
    	background.add(calculate);
    	setCommas();
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		repaint();
		if (ae.getSource() == calculate) {
			if (operation == 0) {
				vectorDot2.setText("u · v = " + vectorOp(0));
				background.add(vectorDot2);
			}
			else if (operation == 1) {
				vectorCross2.setText("<html><body> u × v = " + vectorOp(1) + "</html></body>");
				background.add(vectorCross2);
			}
			else if (operation == 2) {
				vectorProj.setText("<html><body>proj<sub>v</sub>u = " + vectorOp(2) + "</html></body>");
				background.add(vectorProj);
			}
			else if (operation == 3) {
				vectorScal.setText("<html><body>scal<sub>v</sub>u = " + vectorOp(3) + "</html></body>");
				background.add(vectorScal);
			}
			else if (operation == 4) {
				vectorAngCos.setText("<html><body>&theta; = " + vectorOp(4) + "</html></body>");
				background.add(vectorAngCos);
			}
			else if (operation == 5) {
				vectorAngSin.setText("<html><body>&theta; = " + vectorOp(5) + "</html></body>");
				background.add(vectorAngSin);
			}
		}
		if (ae.getSource() == dropBox) {
			if (dropBox.getSelectedItem().toString().equals("2 Vector Dot Product")) {
				setOpDisplay(0);
			}
			else if (dropBox.getSelectedItem().toString().equals("2 Vector Cross Product")) {
				setOpDisplay(1);
			}
			else if (dropBox.getSelectedItem().toString().equals("Vector Projection")) {
				setOpDisplay(2);
			}
			else if (dropBox.getSelectedItem().toString().equals("Vector Scalar")) {
				setOpDisplay(3);
			}
			else if (dropBox.getSelectedItem().toString().equals("Vector Cosine Angle")) {
				setOpDisplay(4);
			}
			else if (dropBox.getSelectedItem().toString().equals("Vector Sine Angle")) {
				setOpDisplay(5);
			}
		}
		background.repaint();
	}
}
