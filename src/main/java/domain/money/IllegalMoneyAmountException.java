package domain.money;

public class IllegalMoneyAmountException extends IllegalArgumentException {
    public IllegalMoneyAmountException() {
        super("금액 액수 오류");
    }
}
