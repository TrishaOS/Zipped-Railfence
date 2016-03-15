package ie.gmit.dip;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RailFenceMenu {

	//this will display a menu, to allow the user one of FOUR options:
	// type in text to be encrypted
	// enter a filename to be decrypted
	// type in text to be decrypted
	//  enter a filename to be decrypted
	//THE USER MUST ALSO PROVIDE THE KEYLENGTH FOR THE ENCRYPTION
	
	
		
			private String menuText = null; //An instance variable
			private Scanner s = new Scanner(System.in);
	//
			private boolean keepRunning = true;
	
			public RailFenceMenu() {
				this.buildMenu();
			
				System.out.println(menuText);
				try {
					this.getUserOption();
				} catch (IOException e) {
					System.out.println("<< ERRORR HAS OCCURRED - TRY AGAIN>>");
					e.printStackTrace();
				}
			}
			
			private void getUserOption() throws FileNotFoundException, IOException{
				while (keepRunning){
					int option = s.nextInt();
					
					if (option == 1) {
						//Encrypt from console
						Encrypt e = new Encrypt();
						e.encryptFromConsole();
						keepRunning = false;					
					System.out.println("Rail Fence Cypher Exited");
					} else if (option == 2){
						//Encrypt from data file
						Encrypt e = new Encrypt();
						e.encryptFromFile();
						keepRunning = false;
						System.out.println("Rail Fence Cypher Exited");
					} else if (option == 3){
						//Decrypt to console
						Decrypt d = new Decrypt();
						d.decryptFromConsole();
						keepRunning = false;
						System.out.println("Rail Fence Cypher Exited");
					} else if (option == 4){
						//Decrypt to datafile
						Decrypt d = new Decrypt();
						d.decryptFromFile();
						keepRunning = false;
						System.out.println("Rail Fence Cypher Exited");
					} else if (option == 5){
						System.out.println("Bye!");
						keepRunning = false;
						System.out.println("Rail Fence Cypher Exited");
					} 
				
				}
				s.close();
			}
	
			private void buildMenu(){
				StringBuffer sb = new StringBuffer(); //A local variable
				sb.append("-------------------------------\n");
				sb.append("\t RAIL FENCE CYPHER\n");
				sb.append("-------------------------------\n");
				
				sb.append("1. ENCRYPT A MESSAGE FROM CONSOLE\n");
				sb.append("2. ENCRYPT A  TEXT FILE\n");
				sb.append("3. DECRYPT TO CONSOLE\n");
				sb.append("4. DECRYPT A TEXT FILE\n");
				sb.append("5. EXIT RAIL FENCE CYPHER\n");
				
				sb.append("ENTER OPTION [1-5]>");
				menuText = sb.toString();
			}	
		}	
	
	
