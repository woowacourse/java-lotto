package lotto.domain;

public class LottoMoney {
    private static final int MONEY_MIN_CONDITION = 0;
    private static final int CHANGE_MONEY = 0;

    private final long money;

    public LottoMoney(final long money) {
        this.money = money;
        validateMoney();
    }

    private void validateMoney() {
        validateMinMoney();
        validateChange();
    }

    public int getAmount() {
        return (int) money / LottoTicket.PRICE;
    }

    private void validateChange() {
        if (money % LottoTicket.PRICE != CHANGE_MONEY) {
            throw new IllegalArgumentException("금액은 1000원 단위로 입력해주세요.");
        }
    }

    private void validateMinMoney() {
        if (money < MONEY_MIN_CONDITION) {
            throw new IllegalArgumentException("금액은 0이상으로 입력해 주세요");
        }
    }

    public double divideMoney(double lottoMoney) {
        return (double) lottoMoney / money;
    }

    @Override
    public String toString() {
        return money + "";
    }
}
