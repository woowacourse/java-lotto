public class Price {

    private final int value;

    public Price(String value) {
        validate(value);
        this.value = Integer.parseInt(value);
    }

    private void validate(String value) {
        validateLottoPriceUnit(value);
    }

    private void validateLottoPriceUnit(String value) {
        int price = validateNumberFormat(value);

        if (price >= LottoRule.LOTTO_PRICE.getValue() && price % LottoRule.LOTTO_PRICE.getValue() == 0) {
            return;
        }

        throw new IllegalArgumentException();
    }

    private int validateNumberFormat(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public int getValue() {
        return value;
    }
}
