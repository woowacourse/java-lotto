package lotto.domain;

public class LottoMoney {
    private static final String LOWER_PRICE_MESSAGE = "티켓 가격보다 적은 입력입니다.";
    private static final String UPPER_PRICE_MESSAGE = "원 이상은 구매할 수 없습니다.";
    private static final int MAX_PURCHASE_PRICE = 100000;
    static final int LOTTO_PRICE = 1000;

    private final int purchasePrice;

    public LottoMoney(int input) {
        validate(input);
        this.purchasePrice = input;
    }

    private void validate(int input) {
        if (input < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOWER_PRICE_MESSAGE);
        }
        if (input > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(MAX_PURCHASE_PRICE + UPPER_PRICE_MESSAGE);
        }
    }

    public int getCountOfTicket() {
        return purchasePrice / LOTTO_PRICE;
    }

    public int getRemainMoney() {
        return (this.purchasePrice % 1000);
    }
}
