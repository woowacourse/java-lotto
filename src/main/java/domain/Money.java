package domain;

public class Money {
    public static final int LOTTO_PRICE = 1_000;
    public static final int NEGATIVE_CRITERIA_POINT = 0;
    private int amount;

    public Money(String inputMoney) {
        checkNotNumber(inputMoney);
        this.amount = Integer.parseInt(inputMoney);
        checkNegativeAmount(amount);
        checkUnderLottoPrice();
    }

    public static int calculateProfitRatio(int lottoCount) {
        return LottoResult.calculateTotalProfit() / (lottoCount * LOTTO_PRICE);
    }

    private void checkUnderLottoPrice() {
        if (amount < LOTTO_PRICE){
            throw new IllegalArgumentException("로또 한장 가격보다 낮은 금액을 입력하셨습니다.");
        }
    }

    private void checkNotNumber(final String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("구매 금액은 숫자여야합니다.");
        }
    }

    private void checkNegativeAmount(final int amount) {
        if (amount < NEGATIVE_CRITERIA_POINT) {
            throw new IllegalArgumentException("구매 금액은 음수일 수 없습니다.");
        }
    }

    public int getCount() {
        return amount / LOTTO_PRICE;
    }
}
