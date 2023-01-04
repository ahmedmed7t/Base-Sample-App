package com.example.baseappsample;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestRandom {
    Random randomGenerator = new Random();
//    public int getRandomWithExclusion(int start, int end, List<Integer> exclude) {
//        // very bad performance it loop sequentially
//        Log.v("Medhat", "Start  "+System.currentTimeMillis());
//        int random = start + new Random().nextInt(end - start + 1 - exclude.size());
//        Log.v("Medhat", "random  "+random);
//        for (int ex : exclude) {
//            if (random < ex) {
//                break;
//            }
//            random++;
//            Log.v("Medhat", "random  "+random);
//        }
//        Log.v("Medhat", "return random  "+random);
//        Log.v("Medhat", "End  "+System.currentTimeMillis());
//        return random;
//    }
    /*
      1. 48.498  -  48.501
      2. 37.947  -  37.952
    */

    public Integer generateRandomNumber(int start, int end, List<Integer> excludeArray){
        Log.v("Medhat", "Start  "+System.currentTimeMillis());

        int rangeLength = end - start - excludeArray.size();
        if(rangeLength <= 0){
            excludeArray.clear();
            int x =  randomGenerator.nextInt(end - start) + start;
            Log.v("Medhat", "random  "+x);
            return x;
        }


        int randomNumber;
        do{
            randomNumber = randomGenerator.nextInt(end - start) + start;
            Log.v("Medhat", "random  "+randomNumber);
        }while (excludeArray.contains(randomNumber));

        excludeArray.add(randomNumber);
        Log.v("Medhat", "End  "+System.currentTimeMillis());
        return randomNumber;
    }


    public int nextIntInRangeButExclude(int start, int end, List<Integer> excludes){
        Collections.sort(excludes);
        int rangeLength = end - start - excludes.size();
        if(rangeLength <= 0){
            excludes.clear();
            rangeLength = end - start;
            return new Random().nextInt(rangeLength) + start;
        }

        int randomInt = new Random().nextInt(rangeLength) + start;
        for(int i = 0; i < excludes.size(); i++) {
            if(excludes.get(i) > randomInt) {
                return randomInt;
            }

            randomInt++;
        }
        return randomInt;
    }
}
