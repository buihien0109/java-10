package com.example.demofragment.utils;

import java.util.Random;

public class Utils {
    public static int randomNumber() {
        Random rd = new Random();
        return rd.nextInt(3);
    }
}
