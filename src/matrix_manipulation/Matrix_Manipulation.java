
package matrix_manipulation;
import java.util.Scanner;

/**
 *
 * @author Adam
 */

//TO DO: UI so the user can input and manipulate their own matrices
public class Matrix_Manipulation {


    public static void main(String[] args) {
        //Matrix m = new Matrix();
        System.out.println("What are the dimensions of your matrix? Enter the rows followed by the columns, separated by an x. (Ex. 4x3)");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] splitStr = str.split("x");
        for (String a : splitStr) 
            System.out.println(a); 
        int rows = Integer.parseInt(splitStr[0]) - 1;
        int cols = Integer.parseInt(splitStr[1]) - 1;
        double [][]theMatrix = new double[rows][cols];
        System.out.println("Enter the first row of your matrix, with each number separated by a space. (Ex. 5 4 3 2 1");
        str = input.nextLine();
        splitStr = str.split(" ");
        for(int i = 0; i < splitStr.length; i++) {
            theMatrix[0][i] = Double.parseDouble(splitStr[i]);
        }
        for(int j = 1; j < theMatrix.length; j++) {
            System.out.println("Enter the next row of your matrix.");
            str = input.nextLine();
            splitStr = str.split(" ");
            for(int k = 0; k < splitStr.length; k++) {
                theMatrix[j][k] = Double.parseDouble(splitStr[k]);
            } 
        }
        
        System.out.println("Enter your augmented matrix, with each number separated by a space");
        str = input.nextLine();
        splitStr = str.split(" ");
        double []theAug = new double[rows];
        for(int m = 0; m < rows + 1; m++) {
            theAug[m] = Double.parseDouble(splitStr[m]);
        }
        
        Matrix m = new Matrix(theMatrix, theAug);
        
    }
    
}
