package lotto.domain;

public class AmountPaid {

    private final int amount;

    public AmountPaid(int amount) {
        this.amount = amount;
        validateDivideByLottoPrice();
        validateNegativeValue();
    }

    public String calculateProfitRate(int totalPrize) {
        return String.format("%.2f", Math.floor(((double) totalPrize / (double) amount) * 100) * 0.01);
    }

    private void validateDivideByLottoPrice() {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }

    private void validateNegativeValue() {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 음수는 입력할 수 없습니다.");
        }
    }

    public int getLottoQuantity() {
        return amount / 1000;
    }

}
