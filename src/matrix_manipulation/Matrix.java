
package matrix_manipulation;
import java.util.*;

/**
 *
 * @author Adam
 */
public class Matrix {
    
    private int[][] matrix =  {  {0, 0, 0},
                                 {6, 12, 18},
                                 {7, 8, 9}  };
    private int[] aug = {3, 6, 1};
    
    public Matrix() {
        
    }
    public Matrix(int[][] m, int[] a) {
        this.matrix = m;
        this.aug = a;
    }
    
    public void showMatrix() {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("| " + aug[i]);
        }
        System.out.println();
    }
    
    public int getNumber(int r, int c) {
        return matrix[r][c];
    }
    
    public void rowEchelonForm() {
        showMatrix();
        boolean firstFound = false;
        int belowNum;
        int rowsLeft;
        int pivotLoc = 0;
        int[] tempArr;
        int tempAug;
        int[] tempAugArr;
        int pivot = 1;
        //find first non-zero number in the row (pivot).
        //make the pivot 1 by dividing the entire row by itself.
        //make every number blow the pivot 0 by adding or subtracting a multiple of the row from that row.
        for(int i = 0; i < matrix.length; i++) {
            firstFound = false;
            for(int j = 0; j < matrix[i].length; j++) { //find the pivot and make it 1
                if((matrix[i][j] != 0) && firstFound == false) {
                    firstFound = true;
                    pivotLoc = j;
                    pivot = matrix[i][j];
                    System.out.println("Making pivot 1... " + "row " + (i + 1 ));
                    for(int m = 0; m < matrix[i].length; m++) {
                        matrix[i][m] = matrix[i][m] / pivot;
                        if(m == 0) {
                            aug[i] = aug[i] / pivot;
                        }
                        showMatrix();
                    }
                }
            }
            for(int n = i + 1; n < matrix.length; n++) { //add a multiple of the pivot row to get 0s under the pivot
                tempArr = Arrays.copyOf(matrix[i],matrix[i].length);
                tempAugArr = Arrays.copyOf(aug,aug.length);
                /*for(int k : tempArr) {
                    k *= matrix[n][pivotLoc];
                }*/
                for(int k = 0; k < tempArr.length; k++) {
                    tempArr[k] = tempArr[k] * matrix[n][pivotLoc];
                }
                tempAug = tempAugArr[i] *= matrix[n][pivotLoc];
                System.out.println("Subtracting " + matrix[n][pivotLoc] + " times row " + (i + 1) + " from row " + (n + 1));
                for(int g = 0; g < matrix[n].length; g++) {
                    matrix[n][g] -= tempArr[g];
                }
                aug[n] -= tempAug;
                showMatrix();
                
            }
        }
        
    }
}
