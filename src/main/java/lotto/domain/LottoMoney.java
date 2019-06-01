package lotto.domain;

public class LottoMoney {
    static final int LOTTO_PRICE = 1000;
    private static final int MAX_PURCHASE_PRICE = 100000;

    private final int purchasePrice;

    public LottoMoney(int input) {
        validate(input);
        this.purchasePrice = input;
    }

    private void validate(int input) {
        if (input < LOTTO_PRICE) {
            throw new IllegalArgumentException("티켓 가격보다 적은 입력입니다.");
        }
        if (input > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(MAX_PURCHASE_PRICE + "원 이상은 구매할 수 없습니다.");
        }
    }

    public int getCountOfTicket() {
        return purchasePrice / LOTTO_PRICE;
    }
}
