import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class RomanToArabicConverter extends JFrame {
	
	private final JLabel arabicLabel; // label for Arabic text field
	private final JLabel romanLabel; // label for Roman text field
	private final JTextField arabicTextField;
	private final JTextField romanTextField;
	
	public RomanToArabicConverter() { // constructor
		
		super("Roman <--> Arabic"); // set title for JFrame
		setLayout(new GridLayout(2, 2, 0, 5)); // create a 2x2 grid layout for labels and text fields
		
		arabicLabel = new JLabel("Arabic Numeral"); // set title for Arabic label
		add(arabicLabel); // add arabicLabel to JFrame
		
		arabicTextField = new JTextField(); // create empty text field for Arabic label
		add(arabicTextField); // add arabicTextField to JFrame
		
		romanLabel = new JLabel("Roman Numeral"); // set title for Roman label
		add(romanLabel); // add romanLabel to JFrame
		
		romanTextField = new JTextField(); // create empty text field for Roman label
		add(romanTextField); // add romanTextField to JFrame
		
		ArabicTextFieldHandler arabicTextFieldHandler = new ArabicTextFieldHandler();
		arabicTextField.addKeyListener(arabicTextFieldHandler); // add KeyListener to arabicTextField
		RomanTextFieldHandler romanTextFieldHandler = new RomanTextFieldHandler();
		romanTextField.addKeyListener(romanTextFieldHandler); // add KeyListener to romanTextField	
	}
	
	private class ArabicTextFieldHandler implements KeyListener { // text field handler for Arabic numbers
		
			public void keyPressed (KeyEvent e) {
				// method not implemented
			}
			
			@Override
			public void keyTyped (KeyEvent e) {
				// method not implemented
			}
			
			@Override
			public void keyReleased (KeyEvent e) {
				
				char keyChar = e.getKeyChar();
				
					if (keyChar == '0' || keyChar == '1' || keyChar == '2' || keyChar == '3' || keyChar == '4' || 
						keyChar == '5' || keyChar == '6' || keyChar == '7' || keyChar == '8' || keyChar == '9') {
						
						String inputNumberString = arabicTextField.getText();
						int arabicNumber = Integer.parseInt(arabicTextField.getText());
						String romanNumerals = ArabicToRomanConversion(arabicNumber);
						romanTextField.setText("" + romanNumerals);
						
						String inputNumberString2 = arabicTextField.getText();
						if (inputNumberString2.length() >= 1) {
							inputNumberString = inputNumberString2.replaceFirst("^0", "");
							arabicTextField.setText(inputNumberString);
						}
						
						if (arabicNumber < 1) { // typed number can't be less than 1
							String arabicNumbers = arabicTextField.getText();
							String string = Character.toString(keyChar);
							arabicNumbers = arabicNumbers.replaceAll(string, "");
							arabicTextField.setText(arabicNumbers);
							romanTextField.setText("");
						}
						
						else if (arabicNumber > 3999) { // typed number can't be greater than 3999
							String arabicNumbers = arabicTextField.getText();
							arabicNumbers = arabicNumbers.substring(0, arabicNumbers.length() - 1);
							arabicTextField.setText(arabicNumbers);
							int numbers2 = Integer.parseInt(arabicNumbers);
							romanTextField.setText(ArabicToRomanConversion(numbers2));
						}
					}
					
					else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
						
							if (arabicTextField.getText().equals("")) {
								romanTextField.setText("");
							}
							
							else {
								int arabicNumber = Integer.parseInt(arabicTextField.getText());
								String romanNumerals = ArabicToRomanConversion(arabicNumber);
								romanTextField.setText("" + romanNumerals);
							}
					}
					
					else {
						String arabicNumber = arabicTextField.getText();
						String string = Character.toString(keyChar);
						arabicNumber = arabicNumber.replaceAll(string, "");
						arabicTextField.setText(arabicNumber);
					}
			}
	}
	
	private class RomanTextFieldHandler implements KeyListener { // text field handler for Roman numerals
		
			public void keyPressed (KeyEvent e) {
				// method not implemented
			}
					
			@Override
			public void keyTyped (KeyEvent e) {
				// method not implemented
			}
					
			@Override
			public void keyReleased (KeyEvent e) {
				char keyChar = e.getKeyChar();
						
				if (keyChar == 'I' || keyChar == 'V' || keyChar == 'X' || keyChar == 'L' ||
					keyChar == 'C' || keyChar == 'D' || keyChar == 'M' || keyChar == 'i' ||
					keyChar == 'v' || keyChar == 'x' || keyChar == 'l' || keyChar == 'c' ||
					keyChar == 'd' || keyChar == 'm') {
								
					String romanNumerals = romanTextField.getText();
					int arabicNumber = Integer.parseInt(RomanToArabicConversion(romanNumerals));
					arabicTextField.setText("" + arabicNumber);
				}
							
				else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE) {
								
					if (romanTextField.getText().equals("")) {
						arabicTextField.setText("");
					}
								
					else {
						String romanNumerals = romanTextField.getText();
						int arabicNumber = Integer.parseInt(RomanToArabicConversion(romanNumerals));
						arabicTextField.setText("" + arabicNumber);
					}
				}
				else {
					String romanNumerals = romanTextField.getText();
					String string = Character.toString(keyChar);
					romanNumerals = romanNumerals.replaceAll(string, "");
					romanTextField.setText(romanNumerals);
				}
			}
		}
			
		public boolean checkForValidArabicNumber(int inputNumber) { // the user is only allowed to enter digits, in the range [1, 3999]
			
			String string = String.valueOf(inputNumber);
				
			for (int i = 0; i < string.length(); i++) { 
				if (Character.isDigit(string.charAt(i)) == false) {
					return false;
				}
			}
				
			if (inputNumber < 1 || inputNumber > 3999) { 
				return false;
			}
			return true;
		}
			
		public boolean checkForValidRomanNumeral(String inputCharacters) { // the user is not allowed to enter any character other than M, D, C, L, X, V, I, m, d, c, l, x, v, and i
			
			for (int i =0; i < inputCharacters.length(); i++) { 
				
				if (inputCharacters.charAt(i) != 'I' && inputCharacters.charAt(i) != 'V' && inputCharacters.charAt(i) != 'X' && inputCharacters.charAt(i) != 'L' &&
					inputCharacters.charAt(i) != 'C' && inputCharacters.charAt(i) != 'D' && inputCharacters.charAt(i) != 'M' && inputCharacters.charAt(i) != 'i' &&
					inputCharacters.charAt(i) != 'v' && inputCharacters.charAt(i) != 'x' && inputCharacters.charAt(i) != 'l' && inputCharacters.charAt(i) != 'c' && 
					inputCharacters.charAt(i) != 'd' && inputCharacters.charAt(i) != 'm') {
						
					return false;
				}
			}
			return true;
		}
			
		public String ArabicToRomanConversion (int inputNumber) { // converts from Arabic to Roman number systems
			
			if (checkForValidArabicNumber(inputNumber)) {
				String roman = "";
				
				while (inputNumber >= 1000) {
			        roman += "M";
			        inputNumber -= 1000;        
			    }
				
			    while (inputNumber >= 900) {
			        roman += "CM";
			        inputNumber -= 900;
			    }
			    
			    while (inputNumber >= 500) {
			        roman += "D";
			        inputNumber -= 500;
			    }
			    
			    while (inputNumber >= 400) {
			        roman += "CD";
			        inputNumber -= 400;
			    }
			    
			    while (inputNumber >= 100) {
			        roman += "C";
			        inputNumber -= 100;
			    }
			    
			    while (inputNumber >= 90) {
			        roman += "XC";
			        inputNumber -= 90;
			    }
			    
			    while (inputNumber >= 50) {
			        roman += "L";
			        inputNumber -= 50;
			    }
			    
			    while (inputNumber >= 40) {
			        roman += "XL";
			        inputNumber -= 40;
			    }
			    
			    while (inputNumber >= 10) {
			        roman += "X";
			        inputNumber -= 10;
			    }
			    
			    while (inputNumber >= 9) {
			        roman += "IX";
			        inputNumber -= 9;
			    }
			    
			    while (inputNumber >= 5) {
			        roman += "V";
			        inputNumber -= 5;
			    }
			    
			    while (inputNumber >= 4) {
			        roman += "IV";
			        inputNumber -= 4;
			    }
			    
			    while (inputNumber >= 1) {
			        roman += "I";
			        inputNumber -= 1;
			    }
			    return String.valueOf(roman);
			}
			return null;
		}
			
		public String RomanToArabicConversion (String inputCharacters) { // converts from Roman to Arabic number systems
			
			if (checkForValidRomanNumeral(inputCharacters)) {
				int arabic = 0;
				int lastDigit = 0;
				int currentDigit = 0;
					
				for (int i = 0; i < inputCharacters.length(); i++) {
						
					if (inputCharacters.charAt(i) == 'I' || inputCharacters.charAt(i) == 'i') {
						currentDigit = 1;
					}
						
					if (inputCharacters.charAt(i) == 'V' || inputCharacters.charAt(i) == 'v') {
						currentDigit = 5;
					}
						
					if (inputCharacters.charAt(i) == 'X' || inputCharacters.charAt(i) == 'x') {
						currentDigit = 10;
					}
						
					if (inputCharacters.charAt(i) == 'L' || inputCharacters.charAt(i) == 'l') {
						currentDigit = 50;
					}
						
					if (inputCharacters.charAt(i) == 'C' || inputCharacters.charAt(i) == 'c') {
						currentDigit = 100;
					}
						
					if (inputCharacters.charAt(i) == 'D' || inputCharacters.charAt(i) == 'd') {
						currentDigit = 500;
					}
						
					if (inputCharacters.charAt(i) == 'M' || inputCharacters.charAt(i) == 'm') {
						currentDigit = 1000;
					}
						
					if (lastDigit < currentDigit && lastDigit != 0) {
						currentDigit -= lastDigit;
						arabic -= lastDigit;
						arabic += currentDigit;
						lastDigit = currentDigit;
						currentDigit = 0;
					}
						
					else {
						lastDigit = currentDigit;
						arabic += currentDigit;
						currentDigit = 0;
					}
				}
				return String.valueOf(arabic);
			}
				
			else {
				return null;
			}
		}
	}