package lotto.domain.machine;

import lotto.domain.ticket.LottoType;

import java.util.List;
import java.util.Map;

public class PurchaseInformation {
    private Map<LottoType,Integer> autoManualNumsInformation;
    private List<List<Integer>> manualNumbers;

    private PurchaseInformation(Map<LottoType,Integer> autoManualNumsInformation, List<List<Integer>> manualNumbers) {
        this.autoManualNumsInformation = autoManualNumsInformation;
        this.manualNumbers = manualNumbers;
    }

    static public PurchaseInformation of(Map<LottoType,Integer> autoManualNumsInformation, List<List<Integer>> manualNumbers){
        return new PurchaseInformation(autoManualNumsInformation,manualNumbers);
    }

    public Map<LottoType, Integer> getAutoManualNumsInformation() {
        return autoManualNumsInformation;
    }
    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
