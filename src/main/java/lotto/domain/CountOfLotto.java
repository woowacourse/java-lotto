package lotto.domain;

public class CountOfLotto {
    private static final int ZERO = 0;
    private final int countOfManualLotto;
    private final int countOfRandomLotto;

    public CountOfLotto(Payment payment, int countOfManualLotto) {
        int totalLotto = payment.calculateCountOfLotto();

        if (totalLotto < countOfManualLotto) {
            throw new IllegalArgumentException("입력한 로또 개수가 지불한 금액을 초과합니다");
        }

        if (countOfManualLotto < ZERO) {
            throw new IllegalArgumentException("음수를 넣을 수 없습니다");
        }
        this.countOfManualLotto = countOfManualLotto;
        this.countOfRandomLotto = totalLotto - countOfManualLotto;
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfRandomLotto() {
        return countOfRandomLotto;
    }
}
