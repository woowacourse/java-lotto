package lotto.domain.machine;

import lotto.domain.ticket.LottoType;

import java.util.List;
import java.util.Map;

public class PurchaseInformation {
    private Map<LottoType,Integer> autoOrManualInformation;
    private List<List<Integer>> manualNumbers;
    public PurchaseInformation( Map<LottoType,Integer> autoOrManualInformation, List<List<Integer>> manualNumbers) {
        this.autoOrManualInformation = autoOrManualInformation;
        this.manualNumbers = manualNumbers;
    }
    public Map<LottoType, Integer> getAutoOrManualInformation() {
        return autoOrManualInformation;
    }
    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
