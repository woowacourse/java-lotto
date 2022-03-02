package lotto.domain;

public class LottoCount {

    private static final String ERROR_PAYMENT_NULL = "구입 금액에 올바른 값을 입력해주세요.";
    private static final String ERROR_COUNT_NOT_NEGATIVE = "로또 구매 개수는 음수가 될 수 없습니다.";
    private static final String ERROR_MORE_TOTAL_COUNT = "총 구매 가능한 로또 개수보다 더 많이 구매할 수 없습니다.";

    private final int manualCount;
    private final int autoCount;

    public LottoCount(final Payment payment, final int manualCount) {
        validatePayment(payment);

        int totalCount = getTotalCount(payment);

        validateManualCount(totalCount, manualCount);

        this.manualCount = manualCount;
        this.autoCount = totalCount - this.manualCount;
    }

    public int getManualCount() {
        return this.manualCount;
    }

    public int getAutoCount() {
        return this.autoCount;
    }

    private int getTotalCount(final Payment payment) {
        return payment.getPayment() / Lotto.LOTTO_PRICE;
    }

    private void validatePayment(final Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException(ERROR_PAYMENT_NULL);
        }
    }

    void validateManualCount(final int totalCount, final int manualCount) {
        validateNegative(manualCount);
        validateLessTotalCount(totalCount, manualCount);
    }

    private void validateNegative(final int lottoCount) {
        if (lottoCount < 0) {
            throw new IllegalArgumentException(ERROR_COUNT_NOT_NEGATIVE);
        }
    }

    private void validateLessTotalCount(final int totalCount, final int manualCount) {
        if (totalCount < manualCount) {
            throw new IllegalArgumentException(ERROR_MORE_TOTAL_COUNT);
        }
    }
}
