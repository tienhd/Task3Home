package com.qsoft;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 5/17/13
 * Time: 8:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Calculator {



    public int add(String numberString) {

        int result = 0;
        if (numberString.isEmpty()) {
            return 0;
        }
        else {
            //String pre = getDefineDelimiterS(numberString);
            ArrayList<String> pre = getDefineDelimiter(numberString);
            String regex = " ";
            if (pre.isEmpty()) {
                regex = "([,.!\\s+\\n;*/*]*)";
            }
            else {

                String[] string = numberString.split("\\n");
                numberString = string [1];

                for (int i =0; i < pre.size(); i++) {

                    String tempString = pre.get(i);
                    String temp = "";
                    for (int j = 0 ; j< tempString.length(); j++) {
                        char t = tempString.charAt(j);
                        if ((t == '*') || (t == '?') || (t == '+') || (t =='[') || (t==']') || ( t== '(') || ( t==')')) {
                            temp += "\\" + t;
                        }
                        else {
                            temp += t;
                        }
                    }
                    //System.out.println("B temp " + temp);
                    numberString = numberString.replaceAll(temp, " ");
                }
                numberString = numberString.replaceAll("[(\\[)(\\])]", " ");
                regex = " ";

            }

            String[] numbers = numberString.split(regex);
            for ( int i = 0; i < numbers.length; i++) {
                if ((!numbers[i].isEmpty()) && (!numbers[i].equals("[")) && (!numbers[i].equals("]"))) {
                    //System.out.println(numbers[i]);
                    if (Integer.parseInt(numbers[i]) >= 0) {
                        result += Integer.parseInt(numbers[i]);
                    }
                    else {
                        throw new NumberFormatException ("Negative is not allowed");
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<String> getDefineDelimiter(String numberString) {
        ArrayList<String> result = new ArrayList<String>();

        String regex1 = "(//)(\\[(.*)\\])\\n(.*)";
        ////String regex2 = "([)(.*)(])";
        if (numberString.matches(regex1)) {
            //System.out.println("MATCH ");

            String[] temp = numberString.split("\\n");
            String pattern = temp[0];
            String delimiterString = pattern.substring(2,pattern.length());
            //System.out.println("DELIMITER " + delimiterString);
            delimiterString = delimiterString.replaceAll("\\["," ");
            delimiterString = delimiterString.replaceAll("\\]"," ");
            String[] splitString = delimiterString.split(" ");
            for (String si : splitString) {
                if (!si.isEmpty()) {
                    result.add(si);
                }
            }
            return result;
        }
        else {
            result.clear();
            return result;
        }
    }

    public void testGit() {

    }
}


