/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix_manipulation;
import java.util.*;

/**
 *
 * @author Adam
 */
public class Matrix {
    
    private int[][] matrix =  {  {1, 0, 0},
                                 {0, 1, 0},
                                 {0, 0, 1}  };
    private int[] aug = {1, 1, 1};
    
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
        int pivot = 0;
        int[] tempArr;
        int tempAug;
        //find first non-zero number in the row (pivot).
        //make the pivot 1 by dividing the entire row by itself.
        //make every number blow the pivot 0 by adding or subtracting a multiple of the row from that row.
        for(int i = 0; i < matrix.length; i++) {
            firstFound = false;
            for(int j = 0; j < matrix[i].length; j++) { //find the pivot and make it 1
                if((matrix[i][j] != 0) && firstFound == false) {
                    firstFound = true;
                    pivot = j;
                    for(int m = 0; m < matrix[i].length; m++) {
                        matrix[i][m] = matrix[i][m] / matrix[i][j];
                        if(m == 0) {
                            aug[i] = aug[i] / matrix[i][j];
                        }
                        showMatrix();
                        System.out.println("Making pivot 1... " + "row " + i );
                    }
                }
            }
            for(int n = i + 1; n < matrix.length; n++) { //add a multiple of the pivot row to get 0s under the pivot
                for(int l = 0; l < matrix[i].length; l++) {
                    tempArr = Arrays.copyOf(matrix[i],matrix[i].length);
                    for(int k : tempArr) {
                        k *= matrix[n][pivot];
                    }
                    tempAug = aug[i] *= matrix[n][pivot];
                    System.out.println("Subtracting " + matrix[n][pivot] + " times row " + i + " from row " + n);
                    for(int g = 0; g < matrix[n].length; g++) {
                        matrix[n][g] -= tempArr[g];
                    }
                    aug[n] -= tempAug;
                    showMatrix();
                } 
            }
        }
        
    }
}
