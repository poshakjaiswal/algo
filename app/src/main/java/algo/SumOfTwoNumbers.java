package algo;

public class SumOfTwoNumbers {


    private static String addTwoStrings(String firstNumber, String secondNumber){


        final String addition = "";

        int carry = 0;
        int iterateUpto = 0;
        StringBuilder stringBuilder = new StringBuilder();


        iterateUpto = Math.min(firstNumber.length(),secondNumber.length());

        int offsetFirst = 0;
        int offsetSecond = 0;



        if (firstNumber.length() < secondNumber.length()) offsetSecond = secondNumber.length() - firstNumber.length();
        else offsetFirst = firstNumber.length() - secondNumber.length();



        if (firstNumber.length() < secondNumber.length()) {

            String delta = "";

            for ( int j = 0 ; j < offsetSecond; j++) delta = delta + "0";//firstNumber

            firstNumber = delta + firstNumber;

        }
        else {

            String delta = "";


            for ( int k = 0 ; k < offsetFirst; k++) delta = delta + "0";//firstNumber

            secondNumber = delta + secondNumber;
        }



        for ( int i = firstNumber.length() -1 ; i >= 0  ; i -- ){

            // 123 , 15

            char first =  firstNumber.charAt(i );

            char second =  secondNumber.charAt(i);

            int sumOfTwoDigits =  (int)first - '0' +  (int)second -'0' + carry;


            carry = sumOfTwoDigits/10;


            stringBuilder.append( sumOfTwoDigits%10 );

        }




        if (carry > 0) stringBuilder.append(carry);



        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args){

        final String firstNumber = "0897977";
        final String secondNumber= "988";

        System.out.println(addTwoStrings(firstNumber,secondNumber));


    }
}
