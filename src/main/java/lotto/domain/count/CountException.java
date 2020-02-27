package lotto.domain.count;

public class CountException extends IllegalArgumentException {

    private static final String MANUAL_COUNT_OVER_TOTAL_COUNT = "총 구매 개수는 수동 구매 개수보다 작을 수 없습니다.";
    private static final String NEGATIVE_TOTAL_COUNT = "총 구매 개수는 음수일 수 없습니다.";
    private static final String NEGATIVE_MANUAL_COUNT = "수동 구매 개수는 음수일 수 없습니다.";

    private CountException(String errorMessage) {
        super(errorMessage);
    }

    static CountException manualCountOverTotal() {
        return new CountException(MANUAL_COUNT_OVER_TOTAL_COUNT);
    }

    static CountException negativeTotalCount() {
        return new CountException(NEGATIVE_TOTAL_COUNT);
    }

    static CountException negativeManualCount() {
        return new CountException(NEGATIVE_MANUAL_COUNT);
    }

}
