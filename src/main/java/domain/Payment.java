package domain;

public record Payment(int money) {
    public static int UNIT = LottoMachine.LOTTO_PRICE;

    public Payment {
        validatePositive(money);
        validateUnit(money);
    }

    private void validatePositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("구매금액이 양수가 아닙니다.");
        }
    }

    private void validateUnit(int money) {
        if (money % UNIT != 0) {
            throw new IllegalArgumentException("올바르지 않은 구매금액 단위입니다.");
        }
    }
}
