package lotto.domain.customer;

import java.util.List;

public class Customer {
    private final PurchaseInfo purchaseInfo;
    private final List<List<Integer>> manualNumbers;

    public Customer(PurchaseInfo purchaseInfo, List<List<Integer>> manualNumbers) {
        this.purchaseInfo = purchaseInfo;
        this.manualNumbers = manualNumbers;
    }

    public PurchaseInfo getPurchaseInfo() {
        return purchaseInfo;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
