package ru.job4j.bank;

import java.util.*;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */

public class Bank {

    /**
     * Создаем карту пользователей и счетов.
     */
    private final Map<User, LinkedList<Account>> map = new HashMap<>();

    /**
     * Возвращает объект User с заданным паспортом.
     *
     * @param passport заданный номер паспорта.
     * @return возвращаем User с этим паспортом, если таких нет, то null.
     */
    private User getUserByPassport(String passport) {
        for (User user : map.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Проверяем уникальность паспорта
     *
     * @param passport заданный номер паспорта.
     * @return усли паспорт уникальный
     */
    private boolean checkUniquePassport(String passport) {
        for (User user : map.keySet()) {
            if (user.getPassport().equals(passport)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяем уникальность счета
     *
     * @param account заданный номер счета.
     * @return усли паспорт уникальный
     */
    private boolean checkUniqueAccount(String account) {
        for (LinkedList<Account> accounts : map.values()) {
            for (Account acc : accounts) {
                if (acc.getRequisites().equals(account)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Для заданного User ищем счет с такими реквизитами.
     *
     * @param user      заданный User.
     * @param requisite Реквизиты его счета.
     * @return Возвращаем Account если такой есть, иначе null.
     */
    private Account getUsersAccountByRequisite(User user, String requisite) {
        for (Account account : map.get(user)) {
            if (account.getRequisites().equals(requisite)) {
                return account;
            }
        }
        return null;
    }


    public void addUser(User user) {
        if (checkUniquePassport(user.getPassport())) {
            map.putIfAbsent(user, new LinkedList<>());
        }
    }

    public void deleteUser(User user) {
        map.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        if (!checkUniquePassport(passport) && checkUniqueAccount(account.getRequisites())) {
            getUserAccounts(passport).add(account);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        for (User user : map.keySet()) {
            if (user.getPassport().equals(passport)) {
                map.get(user).remove(account);
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        for (User user : map.keySet()) {
            if (user.getPassport().equals(passport)) {
                return map.get(user);
            }
        }
        return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        User userSrc = getUserByPassport(srcPassport);
        User userDest = getUserByPassport(destPassport);
        if (userDest != null && userSrc != null) {
            Account accountSrc = getUsersAccountByRequisite(userSrc, srcRequisite);
            Account accountDst = getUsersAccountByRequisite(userDest, dstRequisite);
            if (accountSrc != null
                    && accountDst != null
                    && accountSrc.getValue() >= amount) {
                accountSrc.setValue(accountSrc.getValue() - amount);
                accountDst.setValue(accountDst.getValue() + amount);
                result = true;
            }
        }
        return result;
    }

}








