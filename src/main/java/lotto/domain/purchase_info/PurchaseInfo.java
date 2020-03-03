package lotto.domain.purchase_info;

import java.util.List;

public class PurchaseInfo {

    private List<String> manualNumbersInputs;
    private int autoCount;

    public PurchaseInfo(List<String> manualNumbersInputs, int autoCount) {
        this.manualNumbersInputs = manualNumbersInputs;
        this.autoCount = autoCount;
    }

    public List<String> getManualNumbersInputs() {
        return manualNumbersInputs;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
