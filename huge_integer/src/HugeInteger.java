//	Class file HugeInteger for huge_integer
//	Class to create and manipulate HugeInteger objects
//	HugeInteger objects are arrays that house up to 40 digits
//	Used to represent 40-digit numbers

public class HugeInteger{ //class to create, store, and manipulate huge numbers
	
	private static final int NUM_DIGITS=40; //maximum digits in number
	private int digits[]=new int[NUM_DIGITS]; //array to store huge number
	private boolean positive=true; /*boolean to tell if positive (true) or 
									negative (false)*/
	
//constructor
	public HugeInteger(String num){ //method to construct a HugeInteger object
		for(int i=(num.length()-1), j=(NUM_DIGITS-1); i>=0; i--, j--) { 
														//loop to create array
			if(num.charAt(i)=='-'){ //tests if this is negative
				this.positive=false; //sets positive to false if negative
			}else{
				//takes input from string, converts it, and plugs it in
				this.digits[j]=(Integer.parseInt(num.valueOf(num.charAt(i))));
			}
		} //end of loop
	} //end of method
	
//getter	
	public int[] getHugeInteger(){ //method to get value of this
		return this.digits; //returns this
	} //end of method
	
//methods for comparison
	public boolean isEqualTo(HugeInteger hi){ //method to test for equality
		for(int i=0; i<NUM_DIGITS; i++){ //loop to check each digit
			if(this.positive!=hi.positive) { //checks if the signs are the same
				return false; //returns false if the signs are different
			}else if(this.digits[i]!=hi.digits[i]){ //checks for equality
				return false; //returns false if not equal
			}
		} //end of loop
		
		return true; //returns true if all digit pairs were equal
	} //end of method
	
	public boolean isGreaterThan(HugeInteger hi){ /*method to test for being 
																	larger*/
		if(isEqualTo(hi)){ //tests for equality
			return false; //returns false if they are equal
		}else if(this.positive!=hi.positive){ //tests for the same sign
			if(this.positive){ //tests if this is positive
				return true; /*returns true if signs are different and this is 
																	positive*/
			}else{
				return false; /*returns false if signs are different and this 
																is negative*/
			}
		}else{
			if(this.positive){ //tests if this is positive
				for(int i=0; i<NUM_DIGITS; i++){ /*loop to check each integer
				 														pair*/
					if(this.digits[i]>hi.digits[i]){ /*tests if this digit is
					 												greater*/
						return true; //returns true if this is larger
					}else if(this.digits[i]<hi.digits[i]){ /*tests if this
					 									integer is smaller*/
						return false; //returns false if this is smaller
					}
				} //end of loop
			}else{ //same as above, but if both are negative
				for(int i=0; i<NUM_DIGITS; i++){ /*loop to check each integer
				 														pair*/
					if(this.digits[i]>hi.digits[i]){ /*tests if this integer 
																is greater*/
						return false; /*returns false if this is greater
						 						(because they are negative)*/
					}else if(this.digits[i]<hi.digits[i]){
						return true; /*returns true if this is smaller 
												(because they are negative)*/
					}
				} //end of loop
			}
			
			return false; //returns false otherwise
		}
	} //end of method
	
	public boolean isNotEqualTo(HugeInteger hi){ /*method to test for 
																inequality*/
		if(isEqualTo(hi)){ //checks if they are equal
			return false; //returns false if they are equal
		}else {
			return true; //returns true if they are not equal
		}
	} //end of method
	
	public boolean isLessThan(HugeInteger hi){ /*method to test for being 
																	smaller*/
		if(isEqualTo(hi)){ //tests if they are equal
			return false; //returns false if they are equal
		}else if(isGreaterThan(hi)){ /*tests to see if it is larger than the 
																	other*/
			return false; //returns false if it is larger
		}else{
			return true; //returns true if it is smaller
		}
	} //end of method
	
	public boolean isGreaterThanOrEqualTo(HugeInteger hi){ /*method to test for
	 												equality or being larger*/
		if(isEqualTo(hi)||isGreaterThan(hi)){ /*tests if they are equal or it 
																is larger*/
			return true; //returns true if it is equal or larger
		}else{
			return false; //returns false if it is smaller
		}
	} //end of method
	
	public boolean isLessThanOrEqualTo(HugeInteger hi){ /*method to test for 
												equality or being smaller*/
		if(isEqualTo(hi)||isLessThan(hi)){ /*tests to see if it is equal or 
																	smaller*/
			return true; //returns true if it is equal or smaller
		}else{
			return false; //returns false if it is larger
		}
	} //end of method
//end of methods for comparison
	
//methods for calculation
	public void add(HugeInteger hi){ //method to add two HugeIntegers
		if(this.positive==hi.positive){ //checks if both have the same sign
			this.digits=addArrayDigits(this.digits, hi.digits); //adds them
		}else{
			if(this.positive){ //checks if this is positive
				this.negate(); //gives this the same sign as hi
				
				if(this.isEqualTo(hi)){ //checks if they are equal
					for(int i=0; i<NUM_DIGITS; i++){ //loop to set this to zero
						this.digits[i]=0; //sets an element to zero
					} //end of loop
				}else if(this.isGreaterThan(hi)){ /*checks if the |this| is 
														greater than |hi|*/
					//subtracts this from hi (adding negatives)
					this.digits=subtractArrayDigits(hi.digits, this.digits); 
					this.negate(); //changes signs (larger negative)
				}else{
					//subtracts hi from this (adding negatives)
					this.digits=subtractArrayDigits(this.digits, hi.digits); 
				}
				
				this.negate(); //set this to the proper sign
			}else{
				this.negate(); //gives this the same sign as hi
				
				if(this.isEqualTo(hi)){ //checks if they are equal
					for(int i=0; i<NUM_DIGITS; i++){ //loop to set this to zero
						this.digits[i]=0; //sets an element to zero
					} //end of loop
				}else if(this.isGreaterThan(hi)){ /*checks if the |this| is 
														greater than |hi|*/
					//subtracts hi from this (adding negatives)
					this.digits=subtractArrayDigits(this.digits, hi.digits); 
				}else{
					//subtracts this from hi (adding negatives)
					this.digits=subtractArrayDigits(hi.digits, this.digits); 
					this.negate(); //changes signs (larger positive)
				}
				
				this.negate(); //set this to the proper sign
			}
		}
	} //end of method
	
	public void subtract(HugeInteger hi){ //method to subtract two HugeIntegers
		if(this.positive==hi.positive){ //checks if they have the same sign
			if(this.positive){ //checks if they are positive
				if(this.isEqualTo(hi)){ //checks if they are equal
					for(int i=0; i<NUM_DIGITS; i++){ //loop to set this to zero
						this.digits[i]=0; //sets an element to zero
					} //end of loop
				}else if(this.isGreaterThan(hi)){ //checks if this is greater
					//subtracts them
					this.digits=subtractArrayDigits(this.digits, hi.digits); 
				}else{
					//subtracts them
					this.digits=subtractArrayDigits(hi.digits, this.digits); 
					this.negate(); //changes the sign (higher number subtracted)
				}
			}else{
				if(this.isEqualTo(hi)){ //checks if they are equal
					for(int i=0; i<NUM_DIGITS; i++){ //loop to set this to zero
						this.digits[i]=0; //sets an element to zero
					} //end of loop
				}else if(this.isLessThan(hi)){ /*checks if this is less (higher 
															negative number)*/
					//subtracts them
					this.digits=subtractArrayDigits(this.digits, hi.digits); 
				}else{
					//subtracts them
					this.digits=subtractArrayDigits(hi.digits, this.digits); 
					this.negate(); //changes the sign (higher number subtracted)
				}
			}
		}else{
			if(this.positive){
				this.digits=addArrayDigits(this.digits, hi.digits);
			}else{
				if(this.isLessThan(hi)){ /*checks if this is lower (higher 
															negative number)*/
					//adds them (negative-positive)
					this.digits=addArrayDigits(this.digits, hi.digits); 
				}else{
					//subtracts them
					this.digits=subtractArrayDigits(this.digits, hi.digits); 
					this.negate(); //changes the sign (higher number subtracted)
				}
			}
		}
	} //end of method
	
	public void multiply(HugeInteger hi){ //method to multiply two HugeIntegers
		if(this.positive!=hi.positive){ /*checks if they do not have the same 
																		sign*/
			//sets this to negative (negative*positive=negative)
			this.positive=false; 
		}else{
			this.positive=true; /*sets this to positive (positive*positive or 
												negative*negative=positive)*/
		}
		
		int[] newProduct=new int[NUM_DIGITS+1]; //array for products
		int[] total=new int[NUM_DIGITS]; //array for end result
		
		int carry=0; //creates carry value
		for(int j=(NUM_DIGITS-1), k=0; j>=0; j--, k++){ /*loop to multiply all 
																	values*/
			int product=0; //resets product
			
			for(int i=(NUM_DIGITS-1); i>=0; i--){ //loop to multiply a line
				product=((this.digits[i]*hi.digits[j])+carry); /*multiplies one
				 												number pair*/
				if(product>=10){ //tests if product needs to be carried
					carry=(product/10); //sets carry value for next time
					product-=(carry*10); //sets product to be plugged into array
				}else{
					carry=0; //removes carry value for next time
				}
				
				newProduct[i]=product; //plugs product into this line
			} //end of loop
			
			for(int b=0; b<k; b++){ /*loop to add trailing 0's to the end of a 
																		line*/
				for(int a=0; a<(NUM_DIGITS); a++){ /*loop to move input to the 
																		left*/
					newProduct[a]=newProduct[a+1]; //moves input to the left
				} //end of loop
			} //end of loop
			 
			total=addArrayDigits(total, newProduct); //adds product to total
		} //end of loop
		
		this.digits=total; //sets this HugeInteger to total product
	} //end of method
//end of methods for calculation	
	
	public void negate(){ //method to change the sign
		if(this.positive){ //tests if this is positive
			this.positive=false; //changes sign to negative
		}else{
			this.positive=true; //changes sign to positive
		}
	} //end of method
	
	public boolean isZero(){ //method to check if this is equal to zero
		for(int i=0; i<NUM_DIGITS; i++){ //loop to check every digit
			if(this.digits[i]!=0){ //checks if this digit is equal to zero
				return false; //returns false if the digit is not zero
			}
		} //end of loop
		
		return true;
	} //end of method
	
	public String toString(){ //method to convert this to a string
		if(isZero()){
			return "0";
		}
		
		String result=""; //creates empty string to be written to
		int start=0; //creates starting digit
		
		if(!positive){ //tests if this is negative
			result+="-"; //inserts negative sign
		}
		
		for(int i=0; i<NUM_DIGITS; i++){ //loop to remove leading zeros
			if(digits[i]!=0){ //checks if leftmost digit is zero
				break; //ends loop if not zero
			}else{
				start++; /*moves starting point to the right (effectively 
														removing the zero)*/
			}
		} //end of loop
		
		for(int i=start; i<NUM_DIGITS; i++){ //loop to add each digit to string
			result+=digits[i]; //adds digit to string
		} //end of loop
		
		return result; //returns string
	} //end of method
	
//methods for adding and subtracting digits
	private static int[] addArrayDigits(int[] array1, int[] array2){ /*method 
											to add the digits of two arrays*/
		int[] newArray=new int[NUM_DIGITS]; //creates a new array
		
		int carry=0; //creates a carry value
		for(int i=(NUM_DIGITS-1); i>=0; i--){ //loop to add each digit
			int sum=(array1[i]+array2[i]+carry); /*adds the digits and the 
															carry value*/
			if(sum>=10){ //checks to see if carrying needs to be done
				carry=1; //adds a carry value for next time
				sum-=10; //changes this value to single-digit
			}else{
				carry=0; //removes carry value for next time
			}
			newArray[i]=sum; //plugs digit into the array
		}		
		return newArray; //returns the sum of the arrays
	} //end of method
	
	private static int[] subtractArrayDigits(int[] array1, int[] array2){ 
								//method to subtract the digits of two arrays
		int[] newArray=new int[NUM_DIGITS]; //creates a new array
				
		int carry=0; //creates a carry value
		for(int i=(NUM_DIGITS-1); i>=0; i--){ //loop to subtract each digit
			int sum=((array1[i]-array2[i])-carry); /*subtracts the digits and 
															the carry value*/
			if(sum<0){ //checks to see if the difference is negative
				carry=1; //adds carry value for next time
				sum+=10; //changes the value to positive
			}else{
				carry=0; //removes carry value for next time
			}
			newArray[i]=sum; //plugs digit into the array
		}
		return newArray; //returns the difference of the arrays
	} //end of method
} //end of class