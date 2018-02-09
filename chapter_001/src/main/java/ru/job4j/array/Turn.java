package ru.job4j.array;

public class Turn {
    public static int[] back(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int temp1 = array[index];
            int temp2 = array[array.length - 1 - index];
            array[index] = temp2;
            array[array.length - 1 - index] = temp1;
            }
        return array;
        }


    }
