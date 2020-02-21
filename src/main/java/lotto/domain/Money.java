package lotto.domain;

public class Money {

    private static final int ZERO = 0;
    private static final int LOTTO_PRICE = 1000;
    private static final String MONEY_EXCEPTION_MESSAGE = String
        .format("금액을 %d원 단위로 입력해주세요.", LOTTO_PRICE);

    private final int money;

    public Money(int money) {
        validatePositive(money);
        validateDivideByThousand(money);
        this.money = money;
    }

    private void validatePositive(int money) {
        if (money <= ZERO) {
            throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
        }
    }

    private void validateDivideByThousand(int money) {
        if (money % LOTTO_PRICE != ZERO) {
            throw new IllegalArgumentException(MONEY_EXCEPTION_MESSAGE);
        }
    }

    public static int getBuyMoney(LottoCount lottoCount) {
        return lottoCount.getLottoCount() * LOTTO_PRICE;
    }

    public LottoCount getCount() {
        return new LottoCount(money / LOTTO_PRICE);
    }
}
