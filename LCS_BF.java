/**
 * 5311 - 011 Design and Analysis of Algorithms
 * Vignesh Manikandan - 1002012757
 * Ruban Eswaravelu - 1002018133
 */
import java.util.*;
import java.io.File;
import java.io.IOException;

public class LCS_BF {
    
    /**
    * Function Name: lcsBF
    * Purpose: Returns length of LCS for arrStr1 and arrStr2
    */
    public int lcsBF(char[] arrStr1, char[] arrStr2, int x, int y){
        if (x == 0 || y == 0)
        return 0;
        if (arrStr1[x-1] == arrStr2[y-1])
        return 1 + lcsBF(arrStr1, arrStr2, x-1, y-1);
        else
        return Integer.max(lcsBF(arrStr1, arrStr2, x, y-1), lcsBF(arrStr1, arrStr2, x-1, y));
    }
    
    /**
    * Function Name: main
    * Purpose: Reads the two strings from the text file and calculates the LCS by calling the lcsBF method.
    */
    public static void main(String[] args)
    {
        try{
            LCS_BF objLcs = new LCS_BF();
            String str1 = "", str2 = "";
            String fileName="LCS1.txt";

            File strFile = new File(fileName); 
            Scanner Reader = new Scanner(strFile);
            while (Reader.hasNextLine()) { //Reading from the file line by line and calculating the lcs for the two strings.
                String data = Reader.nextLine();
                String[] strArr = data.split(",");

                str1 = strArr[0];
                str2 = strArr[1];

                //Storing the string as character array
                char[] arrStr1=str1.toCharArray();
                char[] arrStr2=str2.toCharArray();
                int x = arrStr1.length;
                int y = arrStr2.length;

                Long startTime = System.nanoTime();//Used to get the start time to calculate the time taken for the algorithm 
                int lcsLen = objLcs.lcsBF( arrStr1, arrStr2, x, y );
                Long endTime = System.nanoTime(); //Used to get the end time to calculate the time taken for the algorithm 
                Long elapsedTime = endTime - startTime;
            
                System.out.println();
                System.out.println("String 1=\""+str1+"\"     String 2=\""+str2+"\"");
                System.out.println("Length of the Longest Common Subsequence of the two strings is "+lcsLen );
                System.out.println("Time taken to find the length is "+elapsedTime+"ns");
            }
            Reader.close();

        }catch(IOException e1){
                System.out.println("An error has occurred.");
                e1.printStackTrace();
        }
    }
}