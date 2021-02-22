package lotto.domain;

import lotto.exception.IllegalLottoCountException;

import java.util.regex.Pattern;

public class LottoCount {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final int manualLottoCount;
    private final int autoLottoCount;
    private int manualLottoRound;

    public LottoCount(String manualCount, int totalCount) {
        validateLottoCount(manualCount, totalCount);
        this.manualLottoCount = Integer.parseInt(manualCount);
        this.autoLottoCount = totalCount - manualLottoCount;
        manualLottoRound = 0;
    }

    private void validateLottoCount(String manualCount, int totalCount) {
        if (isBlank(manualCount) || isInvalidNumberFormat(manualCount) ||
                isExceedTotalCount(manualCount, totalCount)) {
            throw new IllegalLottoCountException();
        }
    }

    private boolean isBlank(String manualCount) {
        return "".equals(manualCount);
    }

    private boolean isInvalidNumberFormat(String manualCount) {
        return !NUMBER_PATTERN.matcher(manualCount).matches();
    }

    private boolean isExceedTotalCount(String manualCount, int totalCount) {
        return totalCount < Integer.parseInt(manualCount);
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }

    public boolean isAvailManualLottoRound() {
        return manualLottoCount != manualLottoRound;
    }

    public void passManualLottoRound() {
        this.manualLottoRound++;
    }
}
