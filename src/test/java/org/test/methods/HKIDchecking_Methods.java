package org.test.methods;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class HKIDchecking_Methods {
    public static String HKIDgenerated;

    /*
     * Generate random integer between specific range
     * @param {number}  min - Minimum value
     * @param {number}  max - Maximum value
     * @returns  {number}
     */
    public static int getRandomInt(int min, int max) {
        
        return (int) (Math.random() * (max - min + 1) + min);
//        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }

    public static String calculateCheckDigit (String charPart, String numPart) {
        // Maximum alphabet should be 2
        if (charPart.length() > 3 || numPart.length() != 6) {
            return "false";
        }

    // Calculate checksum for character part
        String strValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.println("Checking..charPart = " + charPart + " & numPart = " + numPart);
    int checkSum = 0;
    if (charPart.length() == 2) {
        checkSum += 9 * (10 + strValidChars.indexOf(charPart.charAt(0)));
        checkSum += 8 * (10 + strValidChars.indexOf(charPart.charAt(1)));
//        System.out.println("Char length 2; check Sum = " + checkSum);
    } else {
        checkSum += 9 * 36;
        checkSum += 8 * (10 + strValidChars.indexOf(charPart));
//        System.out.println("Char length else; check Sum = " + checkSum);
    }

    int checkSumNum = 0;
    // Calculate checksum for numeric part
  for (int i = 0, j = 7; i < numPart.length(); i++, j--) {
      checkSum += j * Integer.parseInt(String.valueOf(numPart.charAt(i)));
//      checkSumNum += j * Integer.parseInt(String.valueOf(numPart.charAt(i)));
//      System.out.println("Char for num part; check Sum = " + checkSumNum);
  }
//  checkSum += checkSumNum;
  System.out.println("Char for numPart; check Sum in total = " + checkSum);

    // Verify the check digit
  int remaining = checkSum % 11;
  System.out.println("Remainder = " + remaining);
  String verify = (remaining == 0) ? "0" : ((11 - remaining == 10) ? "A" : Integer.toString(11 - remaining));
    System.out.println("Verify check digit; verify = " + verify);

  return verify;
}


    public static String getHKIDgenerated() {
        System.out.println("Generating HKID...");

    // Generate a random number between 1 - 10
        double hkidMode = getRandomInt(1, 10);

        // Generate A - Z from ASCII code 65 - 90
        String randomAlphabet = Character.toString((char) getRandomInt(65, 90));
        if (hkidMode == 10) {
            randomAlphabet += Character.toString((char)getRandomInt(65, 90));
        }
//        System.out.println("randomAlphabet = " + randomAlphabet);

        int [] randomDigit = new int[6];
        String randomNumber = null;
        // Generate 6 Number
        for (int i = 0; i < 6; i++) {
            randomDigit[i] = getRandomInt(0,9);
//            System.out.println("The " + i + " digit = " + randomDigit[i]);
            randomNumber = StringUtils.join(ArrayUtils.toObject(randomDigit),"");
        }
//        System.out.println("randomNumber = " + randomNumber);

        // Calculate check digit
        String checkdigit = calculateCheckDigit(randomAlphabet, randomNumber);

        // Debug Message
//    System.out.println("Alphabet: \t\t" + randomAlphabet +
//                "\nNumber: \t\t" + randomNumber +
//                "\nCheckDigit: \t\t" + checkdigit);
        HKIDgenerated = randomAlphabet + randomNumber + checkdigit;

        System.out.println("HKID generated = " + HKIDgenerated);
        return HKIDgenerated;
    }

    public static void verifyHKID (String hkid) {
        String regEx = "^[A-Z]{1,2}[0-9]{6}[0-9A]$";
        String temp = hkid.replaceAll("[()]", "");
//        String temp = hkid.replaceAll("[\\(\\)]", "");
        System.out.println("The HKID = " + temp);
        System.out.println("Matches regEx? " + temp.matches(regEx));
        boolean result;

        if (!hkid.matches(regEx)){
            return;
        }

        int len = temp.length();
        System.out.println("Length is " + len);
//        System.out.println("1st Char? " + temp.charAt(0));
//        System.out.println("1st Char Num? " + temp.codePointAt(0));
//        System.out.println("1st Code = " + (temp.codePointAt(0) - 55 ));

        int sum = (len == 9)
                ? 9 * (temp.codePointAt(0) - 55) + 8 * (temp.codePointAt(1) - 55)
                : 8 * (temp.codePointAt(0) - 55) + 36 * 9;
        System.out.println("Sum of Alphabetic Char = " + sum);

        StringBuffer arrange = new StringBuffer(temp).reverse();
//        System.out.println("Rearrange = " + arrange.substring(1,7));
        String arrToString = arrange.substring(1,7);
        System.out.println("Rearrange to String = " + arrToString);

        String[] arrStringArray = arrToString.split("");
//        System.out.println("Length of StringArray = " + arrStringArray.length);
        int [] arrArray = new int[arrStringArray.length];
//        System.out.println("Length of IntArray = " + arrStringArray.length);

        for (int i = 0; i < arrStringArray.length; i++) {
            arrArray [i] = Integer.parseInt(arrStringArray[i]);
//            System.out.println("Array now is: " + arrArray[i]);
        }

        int[] arrMultiple = {2, 3, 4, 5, 6, 7};
        for (int i = 0; i < arrArray.length; i++) {
//            System.out.println("Number = " + arrArray[i]);
//            System.out.println("Number = " + arrMultiple[i]);
            sum += arrArray[i] * arrMultiple[i];
//            System.out.println("Sum now is: " + sum);
        }

//        System.out.println("Last Char? " + temp.charAt(len-1));
        int lastCode = temp.codePointAt(len-1);
//        System.out.println("Last Char Num? " + temp.codePointAt(len-1));

        if (lastCode == 65) {
            lastCode = temp.codePointAt(len-1) - 55;
        } else
            lastCode = temp.codePointAt(len-1) - 48;
        System.out.println("Last Code = " + lastCode);

        int mod = sum % 11;
        System.out.println("The Modulus = " + mod);

        int checkCode = 11 - mod;
        System.out.println("Checking code = " + checkCode);

        result = mod == 0 && lastCode == 0 || mod == 10 && lastCode == 1 || checkCode > 1 && checkCode < 10 && lastCode == checkCode;
        System.out.println("Modulus = " + mod + " & Checking digit = " + temp.charAt(len-1));
        System.out.println("The HKID of " + hkid + " is " + result);
        if (!result) {
            if (mod == 0) {
                System.out.println("i.e. Verify code should be 0");
            } else {
                if (checkCode == 10) {
                    System.out.println("i.e. Verify code should be A");
                } else {
                    System.out.println("Verify code should be " + checkCode);
                }
            }
        }
    }
//        Code to test:
//        let reg = /^[A-Z]{1,2}[0-9]{6}\([0-9A]\)$/
//        if (!str.match(reg)) { return false };
//        let temp = str.replace(/[\(\)]/g, '');
//        let len = temp.length;
//        let sum = (len === 9)
//                ? 9 * (temp[0].charCodeAt() - 64) + 8 * (temp[1].charCodeAt() - 64)
//                : 8 * (temp[0].charCodeAt() - 64);
//        let arr = temp.split('').reverse().join('').substr(1, 6);
//        let arrResult = [2, 3, 4, 5, 6, 7];
//        for (let i = 0; i < arr.length; i++) {
//            sum += Number(arr[i]) * arrResult[i];
//        }
//        let mod = sum % 11;
//        let lastCode = temp[len - 1];
//        if (mod === 0 && Number(lastCode) === 0) { return true };
//        let checkCode = 11 - mod;
//        if (checkCode === 10 && lastCode === 'A') { return true };
//        if (checkCode > 0 && checkCode < 10 && Number(lastCode) === checkCode) { return true };
//        return false;
}
