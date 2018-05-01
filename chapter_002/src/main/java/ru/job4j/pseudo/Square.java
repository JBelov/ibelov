package ru.job4j.pseudo;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 01.05.2018
 */

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++");
        pic.append("+  +");
        pic.append("+  +");
        pic.append("++++");
        return pic.toString();
    }
}
