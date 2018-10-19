import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
//import org.apache.commons.lang3.StringUtils;


public class javadocMaker {
   public static void main(String[] args) throws IOException {
      String nxt;
      String returnType;
      String label = "";
      int ends;
      int starts;
      boolean LABEL;
      
      System.out.print("File Name: ");
      Scanner userInput = new Scanner(System.in);
      String fileName = userInput.nextLine();
      System.out.print("Would you like to label the comments? ");
      String decision = userInput.nextLine();
      if(decision.charAt(0) == 'y') {
         LABEL = true;
      } else {
         LABEL = false;
      }
      FileWriter fw = new FileWriter("new" + fileName);
      PrintWriter pw = new PrintWriter(fw);
      Scanner scanFile = new Scanner(new File(fileName));
      while(scanFile.hasNextLine()) {
         //System.out.println("hello");
         nxt = scanFile.nextLine();
         if(nxt.trim().length() > 7 && nxt.trim().substring(0, 6).equals("public") && !nxt.trim().substring(7, 12).equals("class")) {
            ends = nxt.lastIndexOf(" ", nxt.indexOf("("));
            starts = nxt.lastIndexOf(" ", ends-1);
            returnType = nxt.substring(starts, ends);
            pw.println("\t/**");
            if(LABEL) {
               System.out.print(returnType + ": ");
               label = userInput.nextLine();
            }
            pw.println("\t*@return" + returnType + " " + label);
            if(nxt.indexOf("()") == -1) {
               ends = 0;
               starts = nxt.indexOf("(") + 3;
               starts = nxt.indexOf(" ", starts);
               if(nxt.indexOf(",") != -1){
                  while(nxt.indexOf(",", ends+1) != -1) {
                     ends = nxt.indexOf(",", starts);
                     pw.println("\t*@param" + nxt.substring(starts, ends));
                     starts = nxt.indexOf(",", ends) + 3;
                     starts = nxt.indexOf(" ", starts);
                  }
               }
               ends = nxt.indexOf(")");
               pw.println("\t*@param" + nxt.substring(starts, ends));
               
            }
            //pw.println("\t*");
            pw.println("\t*/");
         }
         pw.println(nxt);
      }
      pw.println("//Edited");
      pw.close();
   }
}