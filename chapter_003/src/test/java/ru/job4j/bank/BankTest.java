package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void whenAddAndDeleteUsers() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "5941549");
        User user2 = new User("Petr", "7589437");
        User user3 = new User("Ivan2", "5941549");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        Account account1 = new Account("6421678449668", 10000);
        Account account2 = new Account("9474823476746", 20000);
        Account accountSame = new Account("6421678449668", 10000);
        Account account3 = new Account("8463763782226", 30000);
        Account account4 = new Account("8463763782226", 40000);
        bank.addAccountToUser("5941549", account1);
        bank.addAccountToUser("5941549", account2);
        bank.addAccountToUser("5941549", account3);
        bank.addAccountToUser("5941549", accountSame);
        bank.addAccountToUser("7589437", account4);
        bank.deleteAccountFromUser("5941549", account2);
        bank.deleteUser(user2);

        assertThat(bank.getUserAccounts("5941549") + " "
                + bank.getUserAccounts("7589437"), is(
                "[Счет: 6421678449668 Баланс: 10000.0, Счет: 8463763782226 Баланс: 30000.0] null"
        ));
    }

    @Test
    public void whenTransferMoneyOk() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "5941549");
        User user2 = new User("Petr", "7589437");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account("6421678449668", 10000);
        Account account2 = new Account("9474823476746", 20000);
        Account account4 = new Account("8463763782226", 40000);
        bank.addAccountToUser("5941549", account1);
        bank.addAccountToUser("5941549", account2);
        bank.addAccountToUser("7589437", account4);
        bank.transferMoney("5941549",
                "6421678449668",
                "5941549",
                "9474823476746",
                5000);
        bank.transferMoney("5941549",
                "9474823476746",
                "7589437",
                "8463763782226",
                6000);

        assertThat(bank.getUserAccounts("5941549") + " "
                + bank.getUserAccounts("7589437"), is(
                "[Счет: 6421678449668 Баланс: 5000.0, Счет: 9474823476746 Баланс: 19000.0] "
                        + "[Счет: 8463763782226 Баланс: 46000.0]"
        ));
    }

    @Test
    public void whenNoEnoughMoney() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "5941549");
        User user2 = new User("Petr", "7589437");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account("6421678449668", 10000);
        Account account4 = new Account("8463763782226", 40000);
        bank.addAccountToUser("5941549", account1);
        bank.addAccountToUser("7589437", account4);

        assertThat(bank.transferMoney("5941549",
                "6421678449668",
                "7589437",
                "8463763782226",
                20000),
                is(false));
    }

    @Test
    public void whenNoAnotherUser() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "5941549");
        bank.addUser(user1);
        Account account1 = new Account("6421678449668", 10000);
        bank.addAccountToUser("5941549", account1);

        assertThat(bank.transferMoney("5941549",
                "6421678449668",
                "7589437",
                "8463763782226",
                20000), is(false));
    }


}