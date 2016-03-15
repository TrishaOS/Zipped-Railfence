package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Decrypt {
private Scanner s;
private Scanner s2;

	// constructor for decrypt
	public Decrypt(){
		
		}
	
	public void decryptFromConsole() {
	
		s2 = new Scanner(System.in);
		StringBuffer text = new StringBuffer();
		
		System.out.println("ENTER THE TEXT TO BE DECRYPTED > ");
		
		text.append(s2.nextLine());
		
		//read in the entered data
		
		System.out.println("ENTER THE KEYLENGTH USED >");
		
		int keylength = s2.nextInt();
	//System.out.println(text);
	//System.out.println(keylength);
		
	// check that only cap's have been entered:
			 checkForCapitals(text);
			
	
			this.decryptText( keylength,  text);
			System.out.println("THE DECRYPTED MESSAGE IS >" + text);
	
	}
	


	public void decryptFromFile(){
		
// need to get the user to inut the name of the file to be decrypted, and
// the keylength for the decryption
		
			InputStream is = null; 
	      InputStreamReader isr = null;
	      BufferedReader br = null;
	    
	      s = new Scanner(System.in);
	      StringBuffer text = new StringBuffer(); 
	      StringBuffer fileName = new StringBuffer();
	      StringBuffer result = new StringBuffer();
	      StringBuffer decryptedFileName = new StringBuffer();
	      
	      
	    // get the user to enter the name of the file to be encrypted

	      System.out.println("ENTER THE NAME OF THE FILE TO BE DECRYPTED (INCLUDING PATH)>");
	      fileName.append(s.nextLine());
	      System.out.println("ENTER THE NAME OF THE FILE FOR DECRYPTED TEXT (INCLUDING PATH) >");
	      decryptedFileName.append(s.nextLine());
	    // the user must also enter the keylength
	      System.out.println("ENTER THE KEYLENGTH >");
			int keylength = s.nextInt();
			
// open the new file;	  
// wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = null;
			try {
				FileWriter fileWriter = new FileWriter(decryptedFileName.toString());

				     bufferedWriter = new BufferedWriter(fileWriter);
			} catch (Exception e1) {
				System.out.println("<< ERROR OPENING FILE FOR ENCRYPTED TEXT>>");
				System.exit(0);
			}
			
// check that the file we're reading from exists
			String str = fileName.toString();
		         try {
					is = new FileInputStream(str);
				} catch (FileNotFoundException e1) {
					System.out.println("<< FILE DOES NOT EXIST ; TRY RUNNING CYPHER AGAIN >>");
					System.exit(0);
					
				}
	 // the file I have used while testing this application is:
	 //	 c:/Users/Trisha/Desktop/encryptedText.txt
	 
	         // create new input stream reader
	         isr = new InputStreamReader(is); 
	         
	         // create new buffered reader
	         br = new BufferedReader(isr);
	         
	        	String thisLine = null;
	        	  try{
	        	         // open input stream test.txt for reading purpose.
        	         while ((thisLine = br.readLine()) != null) {
	        	            text.append(thisLine);       
	        	            checkForCapitals(text);	        	            
	        	            decryptText(keylength, text);
	        	            bufferedWriter.write(text.toString());
	    	       			bufferedWriter.newLine();

	    	       		 
	    	       			text.setLength(0);
	    	       			result.setLength(0);
	        	           
	        	         } 
	        	         
	        	      }catch(Exception e){
	        	         e.printStackTrace();
	        	      
	        	   
	        	   }finally  {
	        	   }
	        
	         // releases resources associated with the streams
	         
	         if(br!=null)
				try {
					bufferedWriter.flush();
					bufferedWriter.close();
					br.close();
				} catch (IOException e) {
					System.out.println("<< AN ERROR HAS OCCURED TRY AGAIN >>");
					e.printStackTrace();
				}
	         System.out.println("YOUR FILE HAS SUCCESSFULLY BEEN DECRYPTED TO> " + decryptedFileName);
	}
	
	private void checkForCapitals(StringBuffer text) {
		try {
			for (int i = 0; i < text.length()  ; i++) {
				if (text.charAt(i) < 'A' || text.charAt(i) >'Z' ){
					System.out.println("Error ... Only Capital letters can be decrypted");
					System.exit(0);
								}
				
				}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	
		
	}
	
	public StringBuffer decryptText(int keylength, StringBuffer text) {
		
		// filling in for the first row:
		char [][] railfence = new char[keylength][text.length()];
		int row = 0;
		int col = 0;
		boolean movingDown = true;
				int t = 0;
// the method will zigzag through the array 'keylength' times, the row builder
// is used so that it will fill in the 'zigzag' location for each row of the array
// which is the oppposite to how it was built.
				for (int rowBuilder = 0; rowBuilder < keylength ; rowBuilder ++) {
					movingDown = true;
					row = 0;
					col = 0;			
					for (int i = 0; i < text.length(); i++) {
						if (row == rowBuilder){
							railfence[row][col] = text.charAt(t);
							t = t + 1;
						}
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
				
					}			
						
//		This loop was used to see the 2-d array when testing,left here incase any runtime errors
//			occur with the program
/*					for (int i2 = 0; i2 <= keylength-1; i2++) {
						for (int j = 0; j <= text.length()-1; j++) {
							System.out.print(railfence[i2][j]);
						}
						System.out.println(" ");
						
						}
	*/		
//now print out the decrypted message	
				StringBuffer result = new StringBuffer();
					col = 0;
					row = 0;
					movingDown = true;
					for (int i = 0; i < text.length(); i++) {
						
						result.append(railfence[row][col]);
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
					
					
					text.setLength(0);
					text.append(result.toString());
					return text;
		
		
		
	}
		}

		
			
	
		
	
	
	



