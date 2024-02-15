/**
 * 5311 - 011 Design and Analysis of Algorithms
 * Vignesh Manikandan - 1002012757
 * Ruban Eswaravelu - 1002018133
 */
import java.util.*;
import java.io.File;
import java.io.IOException;

public class LCS_DP_CB {

    //table to store lengths of LCS of substrings.
    public String b[][];
    public int c[][];
    public String resultStr = "";
    
    /**
    * Function Name: lcsLength
    * Purpose: Calculates length of LCS using the bottom-up approach for arrStr1 and arrStr2 using two tables.
    */
    public void lcsLength(char[] arrStr1, char[] arrStr2){
        int x = arrStr1.length;
        int y = arrStr2.length;

        b = new String[x+1][y+1];
        c = new int[x+1][y+1];

        for(int i=1; i<=x; i++){
            c[i][0] = 0;
            b[i][0] = " "; //to display the array.
        }
        for(int j=0; j<=y; j++){
            c[0][j] = 0;
            b[0][j] = " "; //to display the array.
        }

        for(int i=1; i<=x; ++i){
            for(int j=1; j<=y; ++j){
                if(arrStr1[i-1] == arrStr2[j-1]){
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i][j] = "\\";
                }else if(c[i-1][j] >= c[i][j-1]){
                    c[i][j] = c[i-1][j];
                    b[i][j] = "^";
                }else{
                    c[i][j] = c[i][j-1];
                    b[i][j] = "<";
                }
            }
        }
    }

    /**
    * Function Name: printLcs
    * Purpose: prints the two tables calculated in lcsLength of X and Y.
    */
    public void printLcs(String b[][], char[] arrStr1, char[] arrStr2, int x, int y ){
        System.out.println("++++++++++++++++++++++++++++++++++++");

        for(int i=0; i<=x; i++){
            if(i==0){
                System.out.print("    Y   ");
                for(int m=0; m<y;m++){
                    System.out.print(arrStr2[m]+"   ");
                }
                System.out.println();
                System.out.print("X ");
            }
            for(int j=0; j<=y; j++){
                if(j==0 && i!=0){
                    System.out.print(arrStr1[i-1]+" ");
                }
                System.out.print(b[i][j]+" "+c[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("++++++++++++++++++++++++++++++++++++");
    }

    /**
    * Function Name: printLcsStr
    * Purpose: prints the LCS of X and Y.
    */
    public void printLcsStr(String b[][], char[] arrStr1, int i, int j){
        if(i==0 || j==0)
            return ;
        if(b[i][j] == "\\"){
            printLcsStr(b, arrStr1, i-1, j-1);
            resultStr += arrStr1[i-1];
        }else if(b[i][j] == "^"){
            printLcsStr(b, arrStr1, i-1, j);
        }else{
            printLcsStr(b, arrStr1, i, j-1);
        }
    }

    /**
    * Function Name: main
    * Purpose: Reads the two strings from the text file and calculates the LCS by calling the lcsLength method.
    */
    public static void main(String[] args) {
        try{
            LCS_DP_CB objLcs = new LCS_DP_CB();
            String strX = "", strY = "";
            String fileName="LCS2.txt";

            File strFile = new File(fileName); 
            Scanner Reader = new Scanner(strFile);
            while (Reader.hasNextLine()) { //Reading from the file line by line and calculating the lcs for the two strings.
                String data = Reader.nextLine();
                String[] strArr = data.split(",");

                strX = strArr[0];
                strY = strArr[1];
                char[] arrStr1=strX.toCharArray();
                char[] arrStr2=strY.toCharArray();
                int x = arrStr1.length;
                int y = arrStr2.length;

                Long startTime = System.nanoTime();//Used to get the start time to calculate the time taken for the algorithm 
                objLcs.lcsLength(arrStr1, arrStr2);
                Long endTime = System.nanoTime(); //Used to get the end time to calculate the time taken for the algorithm 
                Long elapsedTime = endTime - startTime;

                System.out.println();
                System.out.println("X=\""+strX+"\"     Y=\""+strY+"\"");
                objLcs.printLcs(objLcs.b, arrStr1, arrStr2, x, y);   
                objLcs.printLcsStr(objLcs.b, arrStr1, x, y);

                System.out.println("Length of the Longest Common Subsequence is: "+objLcs.c[x][y]);
                System.out.println("The Longest Common Subsequence of \""+strX+"\" and \""+strY+"\" is \""+objLcs.resultStr+"\"");
                System.out.println("Time taken to find the length is "+elapsedTime+"ns");

                //clearing the two arrays for the next iteration.
                objLcs.b = null;
                objLcs.c = null;
                objLcs.resultStr = "";
            }
            Reader.close(); 

        }catch(IOException e1){
            System.out.println("An error has occurred.");
            e1.printStackTrace();
        }
    }
}
