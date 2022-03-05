package lotto.domain;

public class LottoBuyMoney {

    private static final int LOTTO_PRICE = 1_000;
    private static final int MIN_AMOUNT = 0;

    private final int money;

    public LottoBuyMoney(int money) {
        validateUnderLimit(money);
        validateUnderThousands(money);
        this.money = money;
    }

    public int countAutoAmountByManualAmount(int manualAmount) {
        validateAmount(manualAmount);
        return this.money / LOTTO_PRICE - manualAmount;
    }

    private void validateUnderLimit(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("입력금액은 1,000원 이상이어야 한다.");
        }
    }

    private void validateUnderThousands(int money) {
        if (money % LOTTO_PRICE != MIN_AMOUNT) {
            throw new IllegalArgumentException("입력금액은 1,000원 단위어야 한다.");
        }
    }

    private void validateAmount(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("수량은 음수일 수 없다.");
        }

        if (this.money < amount * LOTTO_PRICE) {
            throw new IllegalArgumentException("구매 금액을 초과한 수량이다.");
        }
    }
}
