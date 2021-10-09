package process.test;

public class main {

	 public static void main(String args[]) {
		   
		  // déclarer et initialiser un tableau 2D
		 String[][] change  = {
				 {"boul","boulevard"},
				 {"boul.","boulevard"},
				 {"bd","boulevard"},
				 {"av.","avenue"},
				 {"faub.","faubourg"},
				 {"fg","faubourg"},
				 {"pl.","place"}
		 };
		  for (String[] tab: change) {
		     for (String s: tab) {
		         System.out.print(s + "\t");
		     }
		     System.out.println("\n");
		  }
		 }
		}
