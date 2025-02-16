package domain;

public class Money {

    private final int amount;

    public Money(String amount) {
        validate(amount);
        this.amount = Integer.parseInt(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void validate(String amount) {
        int money = parseAndValidateNumber(amount);

        validateZeroAmount(money);
        validateMultipleOfLottoPrice(money);
    }

    private static void validateMultipleOfLottoPrice(int price) {
        if (price % LottoRule.LOTTO_PRICE.getValue() == 0) {
            return;
        }
        throw new IllegalArgumentException("구입 금액은 로또 가격 단위(" + LottoRule.LOTTO_PRICE.getValue() + ")의 배수여야 합니다.");
    }

    private static void validateZeroAmount(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException("구입 금액은 0원일 수 없습니다.");
        }
    }

    private int parseAndValidateNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }
}