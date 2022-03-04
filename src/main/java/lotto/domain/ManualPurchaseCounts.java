package lotto.domain;

import static lotto.util.regex.NumberRegex.ZERO_OR_NATURAL_NUMBER_REGEX;

public class ManualPurchaseCounts {
    private static final String INVALID_PURCHASE_MANUAL_LOTTO_COUNTS_EXCEPTION_MESSAGE = "0 이상의 정수를 입력해주세요.";
    private static final String OVER_COUNTS_EXCEPTION_MESSAGE =
            "구입금액으로 구매할 수 있는 개수를 초과한 개수입니다.";

    private final int manualLottoCounts;

    public ManualPurchaseCounts(final String inputManualLottoCounts, final int allLottoCounts) {
        validateValidNumber(inputManualLottoCounts);
        final int manualLottoCounts = Integer.parseInt(inputManualLottoCounts);
        validateManualCountsNotOverAllCounts(manualLottoCounts, allLottoCounts);
        this.manualLottoCounts = manualLottoCounts;
    }

    private static void validateValidNumber(final String value) {
        if (!ZERO_OR_NATURAL_NUMBER_REGEX.matcher(value).matches()) {
            throw new IllegalArgumentException(INVALID_PURCHASE_MANUAL_LOTTO_COUNTS_EXCEPTION_MESSAGE);
        }
    }

    private static void validateManualCountsNotOverAllCounts(final int manualLottoCounts, final int allLottoCounts) {
        if (manualLottoCounts > allLottoCounts) {
            throw new IllegalArgumentException(OVER_COUNTS_EXCEPTION_MESSAGE);
        }
    }

    public int getManualLottoCounts() {
        return manualLottoCounts;
    }
}
