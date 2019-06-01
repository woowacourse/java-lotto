package lotto.domain;

public class LottoMoney {
    private static final int MONEY_MIN_CONDITION = 0;

    private final long money;

    public LottoMoney(final long money) {
        this.money = money;
        validateMoney();
    }

    private void validateMoney() {
        validateMinMoney();
        validateChange();
    }

    public long getAmount() {
        return money / LottoTicket.PRICE;
    }

    private void validateChange() {
        if (money % LottoTicket.PRICE != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위로 입력해주세요.");
        }
    }

    private void validateMinMoney() {
        if (money < MONEY_MIN_CONDITION) {
            throw new IllegalArgumentException("금액은 0이상으로 입력해 주세요");
        }
    }

    public long getMoney() {
        return money;
    }
}
