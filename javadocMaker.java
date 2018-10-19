import java.util.ArrayList;
import java.text.DecimalFormat;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

/**
*
*@author: Hudson Chromy
*@dat:10/5/18
*/

public class IcosahedronList {
/**
*Creates an object that is a list of icosahedron objects.
*/

   private String listName;
   private ArrayList<Icosahedron> icosahedronList;
    /**
    *Creates a list of Icosahedron objects.
    *
    *@param lisstNameIn
    *@param iListIn
    */
   IcosahedronList(String listNameIn, ArrayList<Icosahedron> iListIn) {
      listName = listNameIn;
      icosahedronList = iListIn;
      
   }
   public int numberOfIcosahedrons() {
      return icosahedronList.size();
   }
   public String getName() {
      return listName;
   }
   public ArrayList<Icosahedron> getList() {
      return icosahedronList;
   }
   
   public IcosahedronList readFile(String fileName) throws IOException {
      Scanner scanFile = new Scanner(new File(fileName));
      String nameList = scanFile.nextLine();
      listName = nameList;
      while(scanFile.hasNext()) {
         String name = scanFile.nextLine();
         String color = scanFile.nextLine();
         double sideLength =  Double.parseDouble(scanFile.nextLine());
         Icosahedron i = new Icosahedron(name, color, sideLength);
         icosahedronList.add(i);
      }
      IcosahedronList newList = new IcosahedronList(nameList, icosahedronList);
      return newList;
   }
   
   public void addIcosahedron(String label, String color, double edge) {
      Icosahedron newI = new Icosahedron(label, color, edge);
      icosahedronList.add(newI);
   }
   
   public Icosahedron findIcosahedron(String label) {
      label = label.toUpperCase();
      for (Icosahedron ico: icosahedronList) {
         if(ico.getLabel().toUpperCase().equals(label)) {
            return ico;
         }
      }
      return null;
   }
   
   public Icosahedron deleteIcosahedron(String label) {
      label = label.toUpperCase();
      for (Icosahedron ico : icosahedronList) {
         if(ico.getLabel().toUpperCase().equals(label)) {
            icosahedronList.remove(ico);
            return ico;
         }
      }
      return null;
   }
   
   public boolean editIcosahedron(String label, String color, double edge) {
      for (Icosahedron ico : icosahedronList) {
         //System.out.println(ico.getLabel());
         //System.out.println(label);
         if(ico.getLabel().toUpperCase().equals(label.toUpperCase())) {
            ico.setColor(color);
            ico.setEdge(edge);
            return true;
         }
      }
      return false;
   }
   public String toString() {
      String out = listName + "\n";
      int size = numberOfIcosahedrons();
      int i = 0;
      while (i < size) {
         out += "\n" + icosahedronList.get(i).toString() + "\n";
         i++;
      }
      return out;
   }
   public double totalSurfaceArea() {
      int i = 0;
      double out = 0;
      while (i < numberOfIcosahedrons()) {
         out += icosahedronList.get(i).surfaceArea();
         i++;
      }
      return out;
   }
   public double totalVolume() {
      int i = 0;
      double out = 0;
      while (i < numberOfIcosahedrons()) {
         out += icosahedronList.get(i).volume();
         i++;
      }
      return out;
   }
   public double averageSurfaceArea() {
      double out = 0;
      if (numberOfIcosahedrons() != 0) {
         out = totalSurfaceArea() / numberOfIcosahedrons();
      } 
      return out;
   }
   public double averageVolume() {
      double out = 0;
      if (numberOfIcosahedrons() != 0) {
         out = totalVolume() / numberOfIcosahedrons();
      }
      return out;
   }
   public double averageSurfaceToVolumeRatio() {
      double out = 0;
      int i = 0;
      while (i < numberOfIcosahedrons()) {
         out += icosahedronList.get(i).surfaceToVolumeRatio();
         i++;
      }
      if (numberOfIcosahedrons() != 0) {
         out /= numberOfIcosahedrons();
      }
      return out;
   }
   public String summaryInfo() {
      DecimalFormat df = new DecimalFormat("#,##0.0##");
      String out = "";
      if (numberOfIcosahedrons() != 0) {
         out += "----- Summary for Icosahedron Test List -----";
      }
      else {
         out += "----- Summary for Icosahedron Empty Test List -----";
      }
      //----- Summary for Icosahedron ___t_________________
      out += "\nNumber of Icosahedrons: " + numberOfIcosahedrons();
      out += "\nTotal Surface Area: " + df.format(totalSurfaceArea());
      out += "\nTotal Volume: " + df.format(totalVolume());
      out += "\nAverage Surface Area: " + df.format(averageSurfaceArea());
      out += "\nAverage Volume: " + df.format(averageVolume());
      out += "\nAverage Surface/Volume Ratio: " 
         + df.format(averageSurfaceToVolumeRatio());
      return out;
   }
}
