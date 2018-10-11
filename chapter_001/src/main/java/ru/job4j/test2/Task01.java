package ru.job4j.test2;

/**
 * @author Ivan Belov (ivan@belov.org)
 */
public class Task01 {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0 && i % 7 != 0) {
                System.out.print(i);
            } else {
                if (i % 2 == 0) {
                    System.out.print("Two");
                }
                if (i % 7 == 0) {
                    System.out.print("Seven");
                }
            }
            System.out.println();
        }
    }
}
