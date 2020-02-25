package lotto.domain;

import lotto.util.InputValidationUtil;

public class ManualTicketingCount {
    private int manualTicketingCount;

    public ManualTicketingCount(String manualTicketingCount) {
        this.manualTicketingCount = InputValidationUtil.returnNumberWithNumberCheck(manualTicketingCount);
        InputValidationUtil.isPositiveNumber(this.manualTicketingCount);
    }

    public int getManualTicketingCount() {
        return manualTicketingCount;
    }
}
