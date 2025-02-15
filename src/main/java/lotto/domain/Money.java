package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public void validateAmount(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요!");
        }
    }

    public int getLottoTicketCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}


