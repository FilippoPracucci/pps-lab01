package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    public static final String WRONG_USER_ID_ERROR_MESSAGE = "The user ID given is wrong.";
    public static final String WITHDRAW_DEPOSIT_AMOUNT_EXCEED_ERROR_MESSAGE = "The amount given is greater than the actual balance.";
    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID)) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException(WRONG_USER_ID_ERROR_MESSAGE);
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (checkUser(userID)) {
            if (isWithdrawAllowed(amount)) {
                this.balance -= amount;
            } else {
                throw new IllegalArgumentException(WITHDRAW_DEPOSIT_AMOUNT_EXCEED_ERROR_MESSAGE);
            }
        } else {
            throw new IllegalArgumentException(WRONG_USER_ID_ERROR_MESSAGE);
        }
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
