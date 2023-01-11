package com.example.baseappsample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestRandom {

    /**
     * this function will generate random number between two numbers
     * the start and end and these two numbers are included in the range
     * <p>
     * it will throw exception if the start number is greater than the end since it will
     * be a wrong range also end should be greater than 0
     * <p>
     * internally it will
     * <p>
     * - initiate exclude list if it's null
     * <p>
     * - clear exclude list if it's filled
     * <p>
     * - add generated numbers to the exclude list to avoid duplicate
     * <p>
     * - return random number
     *
     * @param start    - first integer can be generated randomly (the smallest number)
     * @param end      - last integer can be generated randomly (the greatest number)
     * @param excludes - previous generated numbers (validate that new number is not exist in that list)
     *                 <p>
     *                 ex:
     *                 input(0, 99, [])
     *                 output:
     *                 - excludes = [5]
     *                 - return = 5
     */
    public int nextIntInRangeButExclude(int start, int end, List<Integer> excludes) {
        // method will generate random number including start and end
        int size = end - start + 1; // this method works with size not end
        if (start > end || end == 0)
            throw new RuntimeException("Start should be less than end");
        if (excludes == null) {
            excludes = new ArrayList<>();
        }
        if (excludes.size() == size) {
            // in case the exclude array filled
            excludes.clear();
            int randomNumber = new Random().nextInt(size - start) + start;
            excludes.add(randomNumber);
            return randomNumber;
        }

        int rangeLength = size - excludes.size();
        int randomInt = new Random().nextInt(rangeLength) + start;
        for (int i = 0; i < excludes.size(); i++) {
            if (excludes.get(i) > randomInt) {
                excludes.add(i, randomInt);
                return randomInt;
            }
            randomInt++;
        }
        excludes.add(randomInt);
        return randomInt;
    }

    /**
     * This function will increase length of provided string number to
     * the provided length that is paddingSize with a char provided that is
     * paddingChar
     *
     * @param number      number that we need to add padding to it
     * @param paddingSize size of number after padding
     * @param paddingChar char that we need to add to the number as padding
     *                    <p>
     *                    Note: in case the number length is greater than paddingSize it will substring that
     *                    number to th paddingSize
     *                    <p>
     *                    ex:
     *                    input("123", 6, '0')  output: "123000"
     *                    input("123456", 4, '0')  output: "1234"
     */
    public String numberPadding(String number, int paddingSize, char paddingChar) {
        number = number.trim();
        if (number.length() > paddingSize) {
            return number.substring(0, paddingSize);
        }
        StringBuilder paddingNumber = new StringBuilder(number);
        int missingChars = paddingSize - number.length();
        for (int i = 0; i < missingChars; i++) {
            paddingNumber.append(paddingChar);
        }
        return paddingNumber.toString();
    }
}