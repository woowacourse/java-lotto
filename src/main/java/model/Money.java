package model;

import static constant.LottoConstant.LOTTO_PRICE;
import static constant.LottoConstant.MIN_PRICE;

public record Money(
        int value
) {
    private static final int REMAIN_PRICE = 0;


    public Money {
        if (value % LOTTO_PRICE != REMAIN_PRICE) {
            throw new IllegalArgumentException("금액은 1,000원 단위로 입력해 주세요.");
        }
        if (value <= MIN_PRICE) {
            throw new IllegalArgumentException("금액은 1,000원 이상이여야 합니다.");
        }
    }

    public int computeTicketCount() {
        return value / LOTTO_PRICE;
    }
}
