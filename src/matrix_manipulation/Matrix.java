
package matrix_manipulation;
import java.util.*;

/**
 *
 * @author Adam
 */
public class Matrix {
    
    private double[][] matrix =  {  {0, 0, 0},
                                 {0, 12, 18},
                                 {7, 8, 9}  };
    private double[] aug = {3, 6, 1};
    
    public Matrix() {
        
    }
    public Matrix(double[][] m, double[] a) {
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
    
    public double getNumber(int r, int c) {
        return matrix[r][c];
    }
    
    public void rowEchelonForm() {
        showMatrix();
        boolean firstFound;
        int belowNum;
        int rowsLeft;
        int pivotLoc = 0;
        double[] tempArr;
        double tempAug;
        double[] tempAugArr;
        double pivot = 1;
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
        formatMatrix();
    }
    
    public void formatMatrix() {
        /*idea: scrap all this and sort the rows based on leading zeroes, with most zeroes being lower. 
        * Use a while loop to do bubble sort until they are all in place.
        * Use another array to store the amount of zeroes in each row.
        */
        double[] leadingZeroesPerRow = Arrays.copyOf(aug, aug.length); //make the array start at 0
        for(int m = 0; m < leadingZeroesPerRow.length; m++) {
            leadingZeroesPerRow[m] = 0;
        }
        boolean nonZeroFound;
        for(int i = 0; i < matrix.length; i++) { //count the number of zeroes in each row
            nonZeroFound = false;
            for(int j = 0; j < matrix[i].length; j++) {
                if(!nonZeroFound && matrix[i][j] == 0) {
                    leadingZeroesPerRow[i]++;
                }
                else if(matrix[i][j] != 0) {
                    nonZeroFound = true;
                }
            }
        }
        /*for(int n = 0; n < leadingZeroesPerRow.length; n++) { //Test to see if the zeros are correctly counted.
            System.out.println(leadingZeroesPerRow[n]);
        }*/
        
        //Now, we need to sort the rows based on the amount of zeroes in each row.
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for(int m = 0; m < matrix.length - 1; m++) {
                if(leadingZeroesPerRow[m] > leadingZeroesPerRow[m+1]) {
                    double[] temp = Arrays.copyOf(leadingZeroesPerRow, leadingZeroesPerRow.length);
                    leadingZeroesPerRow[m] = temp[m+1];
                    leadingZeroesPerRow[m+1] = temp[m];
                    swapRows(m + 1, m + 2);
                    sorted = false;
                }
            }
        }
        
        /*for(int n = 0; n < leadingZeroesPerRow.length; n++) { //Test to see if the zeros are correctly counted.
            System.out.println(leadingZeroesPerRow[n]);
        }*/
        /*int rowRank;
        boolean zeroRowSwap;
        System.out.println("Formatting Matrix...");
        boolean allZeroes;
        for(int i = 0; i < matrix.length; i++) {
            allZeroes = true;
            zeroRowSwap = false;
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] != 0) {
                    allZeroes = false;
                }
            }
            if(allZeroes) {
                //System.out.println("Zero row " + (i + 1) + " found");
                rowRank = i;
                //showMatrix();
                for(int k = matrix.length - 1; k > 0; k--) {
                    allZeroes = true;
                    for(int m = 0; m < matrix[k].length; m++) {
                        if(matrix[k][m] != 0) {
                            allZeroes = false;
                        }
                        
                    }
                    if(allZeroes == false && (k > i) && zeroRowSwap == false) {
                        System.out.println("Swapping row " + (i + 1) + " with row " + (k + 1));
                        int[] tempMatrix = Arrays.copyOf(matrix[k],matrix.length);
                        int[] tempAugArr = Arrays.copyOf(aug,aug.length);
                        int tempAug = tempAugArr[k];
                        matrix[k] = Arrays.copyOf(matrix[i], matrix[i].length);
                        aug[k] = tempAugArr[i];
                        matrix[i] = Arrays.copyOf(tempMatrix,tempMatrix.length);
                        aug[i] = tempAugArr[k];
                        showMatrix();
                        zeroRowSwap = true;
                    }
                }
            }
        }*/
        showMatrix();
    }
    
    public void multRow(int row, int amount) {
        
        for(int i = 0; i < matrix[row].length; i++) {
            matrix[row - 1][i] *= amount;
        }
        aug[row - 1] *= amount;
        showMatrix();
    }
    public void addRow(int startRow, int destinationRow, int times) {
        double[] tempArr = Arrays.copyOf(matrix[startRow - 1],matrix[startRow - 1].length);
        double[] tempAugArr = Arrays.copyOf(aug,aug.length);
        double tempAug;
        for(int i = 0; i < tempArr.length; i++) {
            tempArr[i] *= times;
        }
        tempAugArr[startRow - 1] *= times;
        tempAug = tempAugArr[startRow - 1];
        
        for(int j = 0; j < matrix[destinationRow - 1].length; j++) {
            matrix[destinationRow - 1][j] += tempArr[j];
        }
        aug[destinationRow - 1] += tempAug;
        showMatrix();
    }
    
    public void swapRows(int firstRow, int secondRow) {
        System.out.println("Swapping row " + (firstRow) + " with row " + (secondRow));
        double[] tempMatrix = Arrays.copyOf(matrix[firstRow - 1],matrix.length);
        double[] tempAugArr = Arrays.copyOf(aug,aug.length);
        double tempAug = tempAugArr[firstRow - 1];
        matrix[firstRow - 1] = Arrays.copyOf(matrix[secondRow - 1], matrix[secondRow - 1].length);
        aug[firstRow - 1] = tempAugArr[secondRow - 1];
        matrix[secondRow - 1] = Arrays.copyOf(tempMatrix,tempMatrix.length);
        aug[secondRow - 1] = tempAugArr[firstRow - 1];
        showMatrix();
    }
}
