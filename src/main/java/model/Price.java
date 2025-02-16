package model;

public class Price {

    public static final int LOTTO_PRICE = 1000;
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

        if (price >= LOTTO_PRICE && price % LOTTO_PRICE == 0) {
            return;
        }

        throw new IllegalArgumentException("로또 단위는 1000원 입니다. 1000원 단위로 입력해주세요 입력된 (" + value + ")는 1000원 단위의 수가 아닙니다.");
    }

    private int validateNumberFormat(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요 입력된(" + value + ")는 숫자의 형식이 아닙니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
