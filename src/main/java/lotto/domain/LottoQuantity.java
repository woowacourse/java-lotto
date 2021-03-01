package lotto.domain;

import static lotto.domain.Money.PRICE_OF_LOTTO;

public class LottoQuantity {

    public static final int ZERO_COUNT = 0;
    private final int fixedLottoQuantity;
    private final int autoLottoQuantity;

    public LottoQuantity(Money money, String countOfFixedLotto) {
        validateLottoQuantity(money, countOfFixedLotto);
        fixedLottoQuantity = Integer.parseInt(countOfFixedLotto);
        autoLottoQuantity = money.countLotto() - fixedLottoQuantity;
    }

    public Money calculateFixedLottoPrice() {
        return new Money(Integer.toString(fixedLottoQuantity * PRICE_OF_LOTTO));
    }

    public Money calculateAutoLottoPrice() {
        return new Money(Integer.toString(autoLottoQuantity * PRICE_OF_LOTTO));
    }

    public int getFixedLottoQuantity() {
        return fixedLottoQuantity;
    }

    public int getAutoLottoQuantity() {
        return autoLottoQuantity;
    }

    private void validateLottoQuantity(Money money, String countOfFixedLotto) {
        validateEmpty(countOfFixedLotto);
        validateNumber(countOfFixedLotto);
        validateNegative(countOfFixedLotto);
        validateQuantity(money, countOfFixedLotto);
    }

    private void validateEmpty(String countOfFixedLotto) {
        if (countOfFixedLotto.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    private void validateNumber(String countOfFixedLotto) {
        try {
            Integer.parseInt(countOfFixedLotto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private void validateNegative(String countOfFixedLotto) {
        if (Integer.parseInt(countOfFixedLotto) < 0) {
            throw new IllegalArgumentException("수동 로또 구매 수는 양수여야 합니다.");
        }
    }

    private void validateQuantity(Money money, String countOfFixedLotto) {
        if (money.countLotto() < Integer.parseInt(countOfFixedLotto)) {
            throw new IllegalArgumentException("수동 로또의 개수는 구입 금액을 초과할 수 없습니다.");
        }
    }
}
