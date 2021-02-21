package lotto.domain.number;

public class ManualPurchasesNumber extends Number {
    public ManualPurchasesNumber(int number, PayOut payOut) {
        this(String.valueOf(number), payOut);
    }

    public ManualPurchasesNumber(String number, PayOut payOut) {
        super(number);

        validatePurchasable(payOut);
    }

    private void validatePurchasable(PayOut payOut) {
        if (payOut.getGameCount() < getValueAsInt()) {
            throw new IllegalArgumentException("가능한 게임 횟수를 초과했습니다.");
        }
    }
}
