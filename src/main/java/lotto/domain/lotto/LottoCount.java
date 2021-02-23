package lotto.domain.lotto;

import java.util.regex.Pattern;

public class LottoCount {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoCount(String manualCount, int totalCount) {
        validateLottoCount(manualCount, totalCount);
        this.manualLottoCount = Integer.parseInt(manualCount);
        this.autoLottoCount = totalCount - manualLottoCount;
    }

    private void validateLottoCount(String manualCount, int totalCount) {
        checkManualCount(manualCount);
        checkExceedTotalCount(manualCount, totalCount);
    }

    private void checkManualCount(String manualCount) {
        if ("".equals(manualCount) || !NUMBER_PATTERN.matcher(manualCount).matches()) {
            throw new IllegalArgumentException("[ERROR] 구매 갯수가 정수가 아닙니다.");
        }
    }

    private void checkExceedTotalCount(String manualCount, int totalCount) {
        if (totalCount < Integer.parseInt(manualCount)) {
            throw new IllegalArgumentException("[ERROR] 구매 가능한 로또 개수를 초과했습니다.");
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
