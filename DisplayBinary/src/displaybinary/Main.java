package displaybinary;

import java.io.*;
import java.util.*;

/**
 * Maeda Hanafi
 * Convert to Binary
 */
public class Main {
    public Main(){
        showMenu();
    }

    public static void main(String[] args) {
        Main l = new Main();
    }

   public void showMenu(){
       int k = 0;
     
       do{
            System.out.println("\tMAIN MENU\n");
            System.out.println("\t1. Enter numbers");
            System.out.println("\t2. Quit\n");

            Scanner sc = new Scanner(System.in);
            int choice = 0;
            try{
               choice = sc.nextInt();
            }catch(InputMismatchException ex){
                System.out.println("Please enter integer only");
            }

            if (choice == 1){
                   int num1 = 0, num2 = 0;

                   System.out.println("Enter first integer");
                   num1 = getInput();

                   System.out.println("Enter second integer");
                   num2 = getInput();

                   System.out.println("NUMBER\tINTERNAL REPRESENTATION");
                   if(num1>num2){
                       int temp = num1;
                       num1 = num2;
                       num2 = temp;
                   }
                    for(int counter=num1; counter<=num2; counter++){
                        //if (convert2Binary(counter).equals(binary(counter))) {
                        //} else {
                            System.out.println(" " + counter + "\t"+ format32CharWidth(binary(counter)));
                        //}
                    }
               }else if(choice == 2){
                    break;
               }else{
                    System.out.println("Invalid entry. Please try again!\n");
                }
           }while(k!=1);
        
    }

   public int getInput() {
       int number = 0;
       boolean continueInput = true;
       Scanner sc = new Scanner(System.in);
       do{
           try {
               number = sc.nextInt();
               continueInput = false;
           } catch (InputMismatchException ex) {
               System.out.println("Please enter integer only.");
               sc.nextLine();
           }
       }while(continueInput);
       return number;
    }

    public String binary(int x){
        String backwardBinaryNumber = new String();
        String binaryNumber = new String();

        int binaryDigit;
        int quotient = x;

        if(quotient<0){
            quotient = Math.abs(x);
        }
        //dividing by 2 to obtain remainders
        do{
            binaryDigit = quotient % 2;
            backwardBinaryNumber = backwardBinaryNumber + binaryDigit;
            quotient = quotient / 2;
        }while(quotient != 0);

        for(int k=1; k<=backwardBinaryNumber.length(); k++ ){
               binaryNumber = binaryNumber +
                       backwardBinaryNumber.charAt(backwardBinaryNumber.length()-k);
        }
        //negative of the binary
        if(x<0){
            binaryNumber = negateBinary(format32CharWidth(binaryNumber));
        }
        return binaryNumber;
    }

    public String negateBinary(StringBuffer inBinary){
        String newString = new String();
        String one = "00000000000000000000000000000001";
        String backwardResult = new String();
        String carry = "0";
        String result = new String();
        //1's complement
        for(int k=0; k<inBinary.length(); k++){
            if(inBinary.charAt(k)=='0'){
                newString = newString + '1';
            }else{
                newString = newString + '0';
            }
        }
        //add the binary number with one
        for(int k=newString.length()-1; k>=0; k--){
            String bitResult = addBitsAndCarry(newString.substring(k, k+1), one.substring(k, k+1),
                    carry);

            backwardResult = backwardResult + bitResult.substring(1, 2);
            carry = bitResult.substring(0, 1);
        }

        for(int k=1; k<=backwardResult.length(); k++ ){
               result = result +
                       backwardResult.charAt(backwardResult.length()-k);
        }
        return result;
    }

    public String addBitsAndCarry(String bit1, String bit2, String inCarry){
        String bitResult = new String();

        if(bit1.equals("0") && bit2.equals("1") || bit1.equals("1") && bit2.equals("0")){
            if(inCarry.equals("0")){
                bitResult = "1";
            }else{
                bitResult = "0";
                inCarry = "1";
            }
        }else if(bit1.equals("0") && bit2.equals("0")){
            if(inCarry.equals("0")){
                bitResult = "0";
            }else{
                bitResult = "1";
                inCarry = "0";
            }
        }else if(bit1.equals("1") && bit2.equals("1")){
            if(inCarry.equals("0")){
                bitResult = "0";
                inCarry = "1";
            }else{
                bitResult = "1";
                inCarry = "1";
            }
        }
        return inCarry + bitResult;
    }

    //format the output
    public StringBuffer format32CharWidth(String inString){
        StringBuffer strBuf = new StringBuffer();
        String dummyStr = "00000000000000000000000000000000";

        strBuf.append(dummyStr);
        strBuf.append(inString);
        strBuf.delete(0, inString.length());

        return strBuf;
    }

    public String convert2Binary(int x){
        //Just for accuracy check
        return Integer.toBinaryString(x);
    }
}

    

