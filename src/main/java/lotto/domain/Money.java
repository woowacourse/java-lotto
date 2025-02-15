package lotto.domain;

import lotto.utils.NumberUtils;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(final String amount) {
        int parseAmount = NumberUtils.parseInt(amount);
        validateAmount(parseAmount);
        this.amount = parseAmount;
    }

    private void validateAmount(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("양수여야 합니다.");
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
        }
    }

    public int getLottoTicketCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}


