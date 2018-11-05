import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;  
import java.util.Date;


public class javadocMaker {
   public static void main(String[] args) throws IOException {
      String nxt;
      //String date;
      String returnType;
      String label = "";
      /*********************************************/
      String NAME = "Hudson Chromy";
      /********************************************/
      int ends;
      int starts;
      boolean LABEL;
      boolean date_done = false;
      
      System.out.print("File Name: ");
      Scanner userInput = new Scanner(System.in);
      String fileName = userInput.nextLine();
      String constructor = fileName.substring(0, fileName.indexOf("."));
      //System.out.println(constructor);
      System.out.print("Would you like to label the comments? ");
      String decision = userInput.nextLine();
      if(decision.charAt(0) == 'y') {
         LABEL = true;
      } else {
         LABEL = false;
      }
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      FileWriter fw = new FileWriter("new" + fileName);
      PrintWriter pw = new PrintWriter(fw);
      Scanner scanFile = new Scanner(new File(fileName));
      
      while(scanFile.hasNextLine()) {
         //System.out.println("hello");
         nxt = scanFile.nextLine();
         //System.out.println(nxt);
         if(nxt.length() > 6 && !nxt.substring(0, 6).equals("import") && date_done == false) {
            pw.println("/**\n*\n*author: " + NAME + "\n*date: " + formatter.format(date) + "\n*/");
            date_done = true;
         }
         if(nxt.trim().length() > 7 && nxt.trim().substring(0, 6).equals("public") && !nxt.trim().substring(7, 12).equals("class") && nxt.indexOf("{") != -1) {
            if(nxt.indexOf(constructor) != -1) {
               pw.println(constructorMaker(constructor, nxt, LABEL));
            }
            else {
            pw.println(methodMaker(nxt, LABEL, false));
			   }
         }
         pw.println(nxt);
      }
      //pw.println("//Edited");
      pw.close();
   }
   
   static String constructorMaker (String constructorIn, String nxt, boolean LABEL) {
      //System.out.println("-------");
      return "/**\n*Constuctor for " + constructorIn + ".\n" + methodMaker(nxt, LABEL, true);
   }
   
   static String methodMaker (String nxt, boolean LABEL, boolean construct) {
      String returnType = "";
      String label = "";
      Scanner userInput = new Scanner(System.in);
      String outp = "";
      int ends = nxt.lastIndexOf(" ", nxt.indexOf("("));
	   int starts =	nxt.lastIndexOf(" ",	ends-1);
		System.out.println(nxt);
		
      if(!construct) {
         returnType = nxt.substring(starts, ends);
         outp += "\t/**\n";
      }
		
		//get	label	for return
		//System.out.println(returnType);
      if(returnType != "" && !returnType.equals(" void")) {
         if(LABEL) {
		      System.out.print(returnType +	": ");
			   label	= userInput.nextLine();
		   }
         
		   outp += "\t*@return"	+ returnType +	" " +	label + "\n";
      }
		if(nxt.indexOf("()")	==	-1) {
			ends = 0;
			starts =	nxt.indexOf("(") + 3;
			starts =	nxt.indexOf(" ", starts);
		   if(nxt.indexOf(",") != -1){
				while(nxt.indexOf(",", ends+1) != -1) {
					ends = nxt.indexOf(",",	starts);
					//gets label for param
					if(LABEL) {
						System.out.print(nxt.substring(starts,	ends)	+ ": ");
						label	= userInput.nextLine();
					}
					outp += "\t*@param" + nxt.substring(starts, ends) + " " + label + "\n";
					starts =	nxt.indexOf(",", ends) + 3;
					starts =	nxt.indexOf(" ", starts);
				}
			}
			ends = nxt.indexOf(")");
			//get	label	for param
			if(LABEL) {
				System.out.print(nxt.substring(starts,	ends)	+ ": ");
				label	= userInput.nextLine();
			}
			outp += "\t*@param" + nxt.substring(starts, ends) + " " +label + "\n";
					
		}
		//pw.println("\t*");
		outp += "\t*/\t";
      return outp;
	   
   }
}

