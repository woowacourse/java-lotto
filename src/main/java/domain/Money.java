package domain;

public class Money {

    private final int amount;

    public Money(String rawAmount) {
        int amount = Integer.parseInt(rawAmount);
        validateNegative(amount);
        this.amount = amount;
    }

    public void validateNegative(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("돈은 음수가 될 수 없습니다.");
        }
    }
}
