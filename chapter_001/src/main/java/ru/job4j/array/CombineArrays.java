package ru.job4j.array;

public class CombineArrays {
    public static int[] combine(int[] first, int[] second){
        int[] resultArray = new int[first.length + second.length];
        int k = 0;
        int j = 0;
        int i = 0;
        while (i < first.length && j < second.length)
        {
                if (first[i] < second[j]) resultArray[k++] = first[i++];
                else resultArray[k++] = second[j++];
        }
        while (i < first.length) {
            resultArray[k++] = first[i++];
        }
        while (j < second.length) {
            resultArray[k++] = second[j++];
        }
        return resultArray;
    }
}
