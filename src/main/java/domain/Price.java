package domain;

import static domain.LottoInformation.LOTTO_PRICE;

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

        if (price >= LOTTO_PRICE && price % LOTTO_PRICE == 0) {
            return;
        }

        throw new IllegalArgumentException(String.format("로또 단위는 %d원 입니다. %d원 단위로 입력해주세요 입력된 (%s)는 로또 단위의 수가 아닙니다.", LOTTO_PRICE, LOTTO_PRICE, value, LOTTO_PRICE));
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

    public int divide(int lottoPrice) {
        return value / lottoPrice;
    }

    public int getTicketCount() {
        return divide(LOTTO_PRICE);
    }
}
