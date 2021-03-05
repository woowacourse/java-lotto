package domain;

public class LottoCounter {
    private final int numberOfManualLotto;
    private final int numberOfAutoLotto;

    public LottoCounter(final int numberOfManualLotto, final int numberOfAutoLotto) {
        this.numberOfManualLotto = numberOfManualLotto;
        this.numberOfAutoLotto = numberOfAutoLotto;
    }

    public static LottoCounter of(final int numberOfTotalLotto, final int numberOfManualLotto) {
        validateLottoNumber(numberOfTotalLotto, numberOfManualLotto);
        int numberOfAutoLotto = numberOfTotalLotto - numberOfManualLotto;
        return new LottoCounter(numberOfManualLotto, numberOfAutoLotto);
    }

    private static void validateLottoNumber(final int numberOfTotalLotto, final int numberOfManualLotto) {
        if (numberOfTotalLotto < numberOfManualLotto) {
            throw new IllegalArgumentException("수동로또 개수가 총 로또개수보다 작어야합니다.");
        }
    }

    public int getNumberOfManualLotto() {
        return numberOfManualLotto;
    }

    public int getNumberOfAutoLotto() {
        return numberOfAutoLotto;
    }
}
