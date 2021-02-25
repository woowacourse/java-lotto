package domain;

public class Wallet {

    private int amount;

    public Wallet(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validatePositive(amount);
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("음의 정수가 입력되었습니다.");
        }
    }

    public boolean pay(int amount) {
        if (this.amount < amount) {
            return false;
        }
        this.amount -= amount;
        return true;
    }

    public int getAmount() {
        return amount;
    }
}
