package domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ManualCount {

    private int manualCount;

    public ManualCount(String inputCount) {
        validateNullOrBlank(inputCount);
        validateNumeric(inputCount);
        this.manualCount = Integer.parseInt(inputCount);
    }

    private static void validateNullOrBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(String.format("input값이 공백 또는 null입니다. 현재 input값은 %s 입니다.", input));
        }
    }

    private static void validateNumeric(String input) {
        if (!NumberUtils.isDigits(input)) {
            throw new IllegalArgumentException(String.format("input값이 숫자가 아닙니다. 현재 input값은 %s 입니다.", input));
        }
    }

    public int getManualCount() {
        return manualCount;
    }
}
