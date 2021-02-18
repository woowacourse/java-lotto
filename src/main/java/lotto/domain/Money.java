package lotto.domain;

public class Money {

    public static final int PRICE_OF_LOTTO = 1000;

    private int money;

    public Money(String money) {
        validateMoney(money);
        this.money = Integer.parseInt(money);
    }

    public int countLotto() {
        return money / PRICE_OF_LOTTO;
    }

    private void validateMoney(String money) {
        validateNumber(money);
        validateEmpty(money);
        validatePrice(money);
    }

    private void validateNumber(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    private void validateEmpty(String money) {
        if (money.isEmpty()) {
            throw new IllegalArgumentException("빈 값은 입력할 수 없습니다.");
        }
    }

    private void validatePrice(String money) {
        if (Integer.parseInt(money) < PRICE_OF_LOTTO) {
            throw new IllegalArgumentException("로또를 구매에는 최소 1000원 이상의 금액이 필요합니다.");
        }
    }
}
