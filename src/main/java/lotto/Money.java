package lotto;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int inputMoney;

    public Money(int inputMoney) {
        this.inputMoney = inputMoney;
    }

    public int getLottoCount() {
        return inputMoney / LOTTO_PRICE;
    }
}
