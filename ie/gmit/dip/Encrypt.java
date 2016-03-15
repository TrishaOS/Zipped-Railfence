package ie.gmit.dip;

import java.io.*;

import java.util.Scanner;



public class Encrypt {
	private Scanner s;
	private Scanner s2;


	//constructor
	public Encrypt(){
		
	}
	
	public void encryptFromConsole() {
		
		s = new Scanner(System.in);
		StringBuffer text = new StringBuffer();
		StringBuffer result = new StringBuffer();
		StringBuffer strb = new StringBuffer();
		
	//read in the text to be encrypted
		
		System.out.println("ENTER TEXT TO BE ENCRYPTED > ");
		
		text.append(s.nextLine());
	
	// use the method toUppperClass to convert all alphabetical characters to
	// uppercase and discard all others
		
		toUpperCase(text, strb);
	
		System.out.println("ENTER THE KEYLENGTH >");
		
		int keylength = s.nextInt();
	// encrypt the message and print it to the console	
		encryptText(keylength, text, result);
		System.out.println("THE ENCRYPTED MESSAGE IS  >" + result);
		
		
	}
	

	public void encryptFromFile() throws FileNotFoundException, IOException{

//		System.out.println("about to encrypt from file");
//initialise variables	

		      s2 = new Scanner(System.in);
			  InputStream is = null; 
			  InputStreamReader isr = null;
		      StringBuffer text = new StringBuffer(); 
		      StringBuffer fileName = new StringBuffer();
		      StringBuffer outputFileName = new StringBuffer();
		      StringBuffer result = new StringBuffer();
		      StringBuffer strb = new StringBuffer();
		      
		      
// get the user to enter the name of the file to be encrypted
		      System.out.println("ENTER THE NAME OF THE FILE TO BE ENCRYPTED (INCLUDING PATH) >");
		      fileName.append(s2.nextLine());
		      System.out.println("ENTER THE NAME OF THE FILE TO STORE THE ENCRYPTED TEXT (INCLUDING PATH)  >");
		      outputFileName.append(s2.nextLine());
		      // the user must also enter the keylength
		      System.out.println("ENTER THE KEYENGTH >");
			  int keylength = s2.nextInt();

	
// the file I have used while testing this application is:
 //	c:/Users/Trisha/Desktop/tester.txt
			// check that the input file exists
				String str = fileName.toString();
			         try {
						is = new FileInputStream(str);
					} catch (FileNotFoundException e1) {
						System.out.println("file does not exist, try again running cypher again");
						System.exit(0);
						
					}
// open the new file
//create a file to write the encrypted text to.
			         
			        BufferedWriter bufferedWriter = null;
					try {
						FileWriter fileWriter = new FileWriter(outputFileName.toString());

						     bufferedWriter = new BufferedWriter(fileWriter);
					} catch (Exception e1) {
						System.out.println("<< ERROR OPENING FILE FOR ENCRYPTED TEXT>>");
						e1.printStackTrace();
					}

// create new buffered reader         
		         FileReader fr = new FileReader(fileName.toString());
		         BufferedReader br =new BufferedReader(fr);

		       	String thisLine = null;    
		       	try {
					while ((thisLine = br.readLine()) != null) {
						text.setLength(0);
						text.append(thisLine);
       			
//convert line from file to uppercase chars only					
						this.toUpperCase(text, strb);
						result.setLength(0);
						this.encryptText(keylength, text, result);
						bufferedWriter.write(result.toString());
						bufferedWriter.newLine();
						}
					} catch (Exception e) {
						System.out.println("<<AN ERROR HAS OCCURED WHILE ENCRYPTING TEXT FILE>>");
						e.printStackTrace();
					}
	
	        	 
			         // releases resources associated with the streams
			           if(br!=null)
						try {
							bufferedWriter.flush();
							bufferedWriter.close();
							br.close();
						} catch (IOException e) {
							System.out.println("<<AN ERROR HAS OCCURRED, TRY AGAIN");
							e.printStackTrace();
						}
		
			           System.out.println("YOUR FILE AS SUCCESSFULLY BEEN ENCRYPTED TO >" + outputFileName);
	}



	private StringBuffer toUpperCase(StringBuffer text, StringBuffer strb) {
			strb.setLength(0);
		
			for (int i = 0; i < text.length()  ; i++) {
				if (text.charAt(i) >= 'A' && text.charAt(i) <='Z' ){
					strb.append(text.charAt(i));
							}
				if (text.charAt(i) >= 'a' && text.charAt(i) <='z'){
					strb.append(Character.toUpperCase(text.charAt(i)));
					}
				}

// stringstr is all capital letters, so put back into the Stringbuffer text
		text.setLength(0);

		text.append(strb); 

		return text;
		
	}
	
	
	private StringBuffer encryptText(int keylength, StringBuffer text, StringBuffer result){
		
			StringBuffer result1 = new StringBuffer();
		
	//define the 2d array that the stringbuffer will be zig-zagged into
				char[][] railfence = new char[keylength][text.length()];
	// initialise variables for filling the array in a zig-zag pattern
				buildFence(keylength, text, railfence);
			
	//now read through the fence line by line and send the encrypted message to 
	// a new stringbuffer called result
				this.writeText(keylength,text,railfence,result1);
				result.append(result1);	
			
				return result;
	}

	private StringBuffer writeText(int keylength, StringBuffer text, char[][] railfence, StringBuffer result1) {
// make rsure there is nothing in result1
		result1.setLength(0);
		
		for (int i = 0; i <= keylength-1; i++) {
			for (int j = 0; j <= text.length()-1; j++) {
// is the character a capital letter, if so append it to the result
				if (railfence[i][j] >= 'A' && railfence[i][j] <= 'Z'){ 
					result1.append(railfence[i][j]);}
				}
		}
		text.append(result1);
		
		return text;
		
	
		}

	
	private void buildFence(int keylength, StringBuffer text, char[][] railfence) {
		
		int row = 0;
		int col = 0;
		boolean movingDown = true;
// Start fillng in at the top left hand corner of the 'railfence'			
		for (int i = 0; i < text.length(); i++) {
			
				railfence[row][col] = text.charAt(i);
// the column place in the 'fence' increases by one each time
				
				col = col + 1;
// if we are in a moving-up phase, then the row decreases by one each time, else we are in a 
// moving down phase so the row increases by one each time.
//and check if the direction needs to be changed.
				if (row == 0){
					movingDown = true;}
				
				if (row == keylength-1){
					movingDown = false; }
				
			 	if (movingDown){
			 		row = row + 1;
					}else {
					row = row -  1;
					}
			}
	//as a check for self print out the 2-d array:
	/*		for (int i = 0; i <= keylength-1; i++) {
				for (int j = 0; j <= text.length()-1; j++) {
					System.out.print(railfence[i][j]);
					}
					System.out.println(" ");
				}
		*/		
	
	}
	
	
	
		
}
		
			
			
			
		

