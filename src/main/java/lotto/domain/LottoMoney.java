package lotto.domain;

public class LottoMoney {
    private static final String LOWER_PRICE_MESSAGE = "티켓 가격보다 적은 입력입니다.";
    private static final String UPPER_PRICE_MESSAGE = "원 이상은 구매할 수 없습니다.";
    private static final long MAX_PURCHASE_PRICE = 100000;
    static final long LOTTO_PRICE = 1000;

    private final long purchasePrice;

    public LottoMoney(long input) {
        validate(input);
        this.purchasePrice = input;
    }

    private void validate(long input) {
        if (input < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOWER_PRICE_MESSAGE);
        }
        if (input > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(MAX_PURCHASE_PRICE + UPPER_PRICE_MESSAGE);
        }
    }

    public int getCountOfTicket() {
        return (int) (purchasePrice / LOTTO_PRICE);
    }

    public int getRemainMoney() {
        return (int) (this.purchasePrice % LOTTO_PRICE);
    }
}