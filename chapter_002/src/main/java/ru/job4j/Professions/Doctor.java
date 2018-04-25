package ru.job4j.Professions;
/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 18.04.2018
 */
public class Doctor extends Profession {
    /**
     * Класс Доктора.
     * @param name Имя.
     * Метод heal принимает на входе параметр класса Patient и возваращает диагноз класса Diagnose.
     */
    public Doctor (String name) {
        this.name = name;
    }
    public Diagnose heal(Paсient pacient) {
        Diagnose diagnose = new Diagnose();
        return diagnose;
    }
}
