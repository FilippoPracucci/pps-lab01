import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int RIGHT_DEPOSIT_AMOUNT = 100;
    public static final int STANDARD_EXPECTED_BALANCE = 100;
    public static final int WITHDRAW_AMOUNT = 70;
    public static final int WRONG_USER_ID = 2;
    public static final int INITIAL_BALANCE = 0;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testInitialHolderId() {
        assertEquals(bankAccount.getHolder().getId(), accountHolder.getId());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), RIGHT_DEPOSIT_AMOUNT);
        assertEquals(STANDARD_EXPECTED_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int wrongDepositAmount = 50;
        bankAccount.deposit(accountHolder.getId(), RIGHT_DEPOSIT_AMOUNT);
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(WRONG_USER_ID, wrongDepositAmount)),
                () -> assertEquals(STANDARD_EXPECTED_BALANCE, bankAccount.getBalance())
        );
    }

    @Test
    void testWithdraw() {
        final int expectedBalance = 29;
        bankAccount.deposit(accountHolder.getId(), RIGHT_DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), RIGHT_DEPOSIT_AMOUNT);
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(WRONG_USER_ID, WITHDRAW_AMOUNT)),
            () -> assertEquals(STANDARD_EXPECTED_BALANCE, bankAccount.getBalance())
        );
    }

    @Test
    void testWithdrawExceed() {
        final int withdrawExceedAmount = 120;
        bankAccount.deposit(accountHolder.getId(), RIGHT_DEPOSIT_AMOUNT);
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(accountHolder.getId(), withdrawExceedAmount)),
            () -> assertEquals(STANDARD_EXPECTED_BALANCE, bankAccount.getBalance())
        );
    }

    private void multipleWithdraws() {
        final int numberOfWithdraws = 3;
        final int withdrawAmount = 20;
        for (int i = 0; i < numberOfWithdraws; i++) {
            bankAccount.withdraw(accountHolder.getId(), withdrawAmount);
        }
    }

    @Test
    void testMultipleWithdraw() {
        final int expectedBalance = 37;
        bankAccount.deposit(accountHolder.getId(), RIGHT_DEPOSIT_AMOUNT);
        multipleWithdraws();
        assertEquals(expectedBalance, bankAccount.getBalance());
    }
}
