package lotterymachine;

public class Money {
    public static final int PER_LOTTERY_TICKET_PRICE = 1000;
    private static final String TERMS_OF_PURCHASE = "로또 구매는 기본 1000원 이상부터 할 수 있습니다.";

    private final int value;

    public Money(int number) {
        validateNumber(number);
        this.value = number;
    }

    private void validateNumber(int number) {
        if (number < PER_LOTTERY_TICKET_PRICE) {
            throw new IllegalArgumentException(TERMS_OF_PURCHASE);
        }
    }

    public int getValue() {
        return value;
    }
}
