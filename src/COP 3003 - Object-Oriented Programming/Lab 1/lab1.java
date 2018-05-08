class HugeInteger { // 4. the LSD is at digits[NUM_DIGITS - 1]
	
	private static final int NUM_DIGITS = 40; 
	private int digits[] = new int[NUM_DIGITS]; // by default, all array elements
												// are initialized to zero
	private boolean positive;
	
	public HugeInteger(String num) { // Converts an integer string to an array of digits
			this.positive = true;
			this.parse(num);
		
	}
	// getters and setters for digits and indexes
	public int getDigit(int digit) {  			 
		return this.getIndexOfArray(NUM_DIGITS - digit);
	}
	
	public int getIndexOfArray(int index) { 
		if( (index < NUM_DIGITS) && (index >= 0)) {
			return this.digits[index];
		}
		
		else {
			System.out.println("getIndex failed: index out of bounds");
			return -1;
		}
	}
	
	private void setDigit(int digit, int value) {
		this.setIndex((NUM_DIGITS-digit), value);
	}
	
	private void setIndex(int index, int value) {
		if( (index < NUM_DIGITS) && (index >= 0)) {
			if( (value < 10) && (value >= 0)) {
				this.digits[index] = value;
			}
			
			else {
				System.out.println("Value should be an integer within the range 0-9.");
			}
		}
		
		else {
			System.out.println("Index out of bounds.");
		}
	}
	
	public void parse(String value) { // Parses the string value 
		if( (value.length() <= NUM_DIGITS) && (value.length() > 0)) {
			for(int i = 1; i <= value.length(); i++) {
				int charDigit = value.length() - i;
				
				if( Character.isDigit( value.charAt( charDigit ) ) ) {
					this.setDigit(i, Character.getNumericValue(value.charAt(charDigit)));
				}
				
				else {
					positive = false;
				}
			}
		}
	}
	
	// methods for calculation
	public void add(HugeInteger hi){ //adds the value in "hi" to the current object
		// two huge integer objects are accessible here
		// 1. this
		// 2. hi
		
		if(positive!=hi.positive){ 
			// it is in fact doing subtraction
			if(this.positive){
				// "this" is positive, hi is negative
				hi.negate(); // negate hi temporarily
					     // so we have two positive numbers
				
				if(this.isGreaterThan(hi)){ 
					// |this| > |hi| [example: 8+(-5)]
					this.digits = subtractArrayDigits(this.digits, hi.digits);
				}
				else{	// example 8+(-15)
					// |this| <= |hi| [example: 8+(-15)]
					this.digits = subtractArrayDigits(hi.digits, this.digits);
					// negate the "this" HugeInteger since the result is 
					negate();
				}
	
				hi.negate(); // restore hi's sign
			}
			else{
				// "this" is negative, hi is positive
				this.negate();
				
				if(this.isGreaterThan(hi)) {
					this.digits = subtractArrayDigits (this.digits, hi.digits);
					negate();
				}
				else {
					this.digits = subtractArrayDigits (hi.digits, this.digits);
				}
				negate();
				this.negate();
			}
		}
		else{
			// same sign, easy :)
			this.digits = addArrayDigits(this.digits, hi.digits);			
		}	
	}
		
	public void subtract(HugeInteger hi) { // subtracts the value in "hi" from the current object
		// two huge integer objects are accessible here
					// 1. this
					// 2. hi		
		if (this.positive == true && hi.positive == true) {
			// if both "this" and "hi" are negative
			if(this.isGreaterThan(hi)) {
				// if |this| > |hi| [example: -8-(-6)]
				this.digits = subtractArrayDigits(this.digits, hi.digits);	
			}
			else {
				// if |this| <= |hi| [example: -6-(-8)]
				this.digits = subtractArrayDigits(hi.digits, this.digits);
				this.negate();
				
			}
		}
		
		else if(this.positive == false && hi.positive == false) {
			// if "this" is negative and "hi" is positive
			this.negate();	
			hi.negate();
			
			if(this.isGreaterThan(hi)) {
				this.digits = subtractArrayDigits(this.digits, hi.digits);
				this.negate();
				hi.negate();
			}
			else {
				this.digits = subtractArrayDigits(hi.digits, this.digits);
				hi.negate();
			}
		}
		
		else if((this.positive == true) && (hi.positive == false)) {
			// if "this" is positive and "hi" is negative
				// [example: 8-(-6) or 6-(-8)]
			hi.negate();			
			this.digits = addArrayDigits(this.digits, hi.digits);
			hi.negate();
			}
		else {
			// if "this" and "hi" are positive
				this.negate();
				this.digits = addArrayDigits(this.digits, hi.digits);
				this.negate();
		}
	}
	
	public void multiply(HugeInteger hi){ // multiplies the value in "hi" by the current object
		// two huge integer objects are accessible here
		// 1. this
		// 2. hi
		if((this.positive == true) && (hi.positive == true)) {
			// if "this" is positive and "hi" is positive
			// [example: 6*6]
			this.digits = multiplyArrayDigits(this.digits, hi.digits);
		}
		else if((hi.positive == true) && (this.positive == false)) { 
			// if "this" is negative, hi is "positive"
			// [example: -6*5]
			this.negate();
			this.digits = multiplyArrayDigits(this.digits, hi.digits);
			negate();
			
			this.negate();			
		}
		else if((this.positive == true) && (hi.positive == false)) {
			// if "this" is positive, hi is "negative"
			// [example: 6*(-5)]
			hi.negate();
			this.digits = multiplyArrayDigits(this.digits, hi.digits);
			negate();

			hi.negate();
		} 
		else {
			// if "this" and "hi" are both negative
			// [example: (-6)*(-5)]
			this.negate();
			hi.negate();
			this.digits = multiplyArrayDigits(this.digits, hi.digits);

			this.negate();
			hi.negate();
		}
	}
	
	public void negate() { // method used to negate the sign
		positive = (positive == true)? false:true;
	}
		
	public boolean isZero() { // returns true if a zero is found
		for(int i = 0; i < NUM_DIGITS; i++) {
			if(this.getIndexOfArray(i) != 0) {
				return false;
			}
		}
		return true;
	}
	
	// methods for comparison
	public boolean isEqualTo(HugeInteger hi) { // this == hi
		for(int i = 0; i < NUM_DIGITS; i++) {
			if(this.digits[i] != hi.digits[i]) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isNotEqualTo(HugeInteger hi) { // this != hi
		return !(isEqualTo(hi));
	}
	
	public boolean isGreaterThan(HugeInteger hi) { // this > hi
		
		if(this.positive == true && hi.positive == true) {
			boolean isLargerThan = true;
			for (int i = 0; i < (this.digits).length - 1; i++) {
				if(this.digits[i] < hi.digits[i]) {
					isLargerThan = false;
					break;
				}
				else if(this.digits[i] > hi.digits[i]) {
					break;
				}
				else {
					continue;
				}
			}
			
			if (isLargerThan == false) {
				return false;
			}
			else {
				return true;
			}
		}
			
		else if (this.positive == true && hi.positive == false) {
			return true;
		}
		else if (this.positive == false && hi.positive == true) {
			return false;
		}
		else {
			boolean isLargerThan = true;
			for (int i = 0; i < (this.digits).length - 1; i++) {
				if (this.digits[i] < hi.digits[i]) {
					isLargerThan = false;
					break;
				}
				else if (this.digits[i] > hi.digits[i]) {
					break;
				}
				else {
					continue;
				}
			}
		if (isLargerThan == true) {
			return false;
		}
		else {
			return true;
		}
	}
}
	
	public boolean isGreaterThanOrEqualTo(HugeInteger hi) { // this >= hi
		return (this.isEqualTo(hi) || this.isGreaterThan(hi));
	}
	
	public boolean isLessThan(HugeInteger hi) { // this < hi
		if(this.positive == true && hi.positive == true) {
			boolean isLessThan = true;
			for (int i = 0; i < (this.digits).length - 1; i++) {
				if(this.digits[i] > hi.digits[i]) {
					isLessThan = false;
					break;
				}
				else if(this.digits[i] < hi.digits[i]) {
					break;
				}
				else {
					continue;
				}
			}
			
			if (isLessThan == false) {
				return false;
			}
			else {
				return true;
			}
		}
			
		else if (this.positive == true && hi.positive == false) {
			return false;
		}
		else if (this.positive == false && hi.positive == true) {
			return true;
		}
		else {
			boolean isLessThan = true;
			for (int i = 0; i < (this.digits).length - 1; i++) {
				if (this.digits[i] > hi.digits[i]) {
					isLessThan = false;
					break;
				}
				else if (this.digits[i] < hi.digits[i]) {
					break;
				}
				else {
					continue;
				}
			}
			if (isLessThan == true) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	public boolean isLessThanOrEqualTo(HugeInteger hi) { // this <= hi
		return (this.isEqualTo(hi) || this.isLessThan(hi));
	}
	
	
	public String toString() { // Prints the string to h1 and h2
		
		StringBuffer stringValue = new StringBuffer(NUM_DIGITS);
		
		if( this.isZero() ) {
			stringValue.append(0);
		}
		
		else {
			int i = 0;
			
			while( (this.getIndexOfArray(i) == 0) && (i < NUM_DIGITS) ) {
				i++;
			}
			
			for( ; i < NUM_DIGITS; i++) {
				stringValue.append(this.getIndexOfArray(i));
			}
		}
		return (positive?"":"-")+stringValue.toString();
	}
	// Adds one array to another, and stores the result in another array 
	private static int[] addArrayDigits(int[] array1, int [] array2) {
		int sum = 0;
		int carry = 0;
		
		int[] array3 = new int[NUM_DIGITS];
		for(int i = NUM_DIGITS - 1; i > 0; i--) {
			sum  =  array1[i] + array2[i] + carry;
			array3[i] = sum%10;
			carry = sum/10;
			}
			return array3;
		}
	// Subtracts one array from another, and stores the result in another array
	private static int[] subtractArrayDigits(int [] array1, int [] array2) {
		int borrow = 0, difference = 0;
		int [] array3 = new int[NUM_DIGITS];
		for (int i = NUM_DIGITS - 1; i > 0; i--){
			difference = array1[i] - array2[i] - borrow;
			if (difference < 0) {
				borrow = 1;
				array3[i] = difference + 10;
			}
			else {
				borrow = 0;
				array3[i] = difference;
			}
			
		}
		return array3;
		}
	// Multiplies two arrays, and stores the result in another array
	public static int[] multiplyArrayDigits(int [] array1, int [] array2) {
		
		int product = 0;
		int carry = 0;
		int [] array3 = new int[NUM_DIGITS];
		
        for(int i = NUM_DIGITS - 1; i > 0; i--) {
            for(int j = NUM_DIGITS - 1; j > 0; j--) {
                product = (array1[i] * array2[j]) + carry;
                array3[i] = product % 10;
                carry = product / 10;
            }
        } 
        return array3;
	}         
}