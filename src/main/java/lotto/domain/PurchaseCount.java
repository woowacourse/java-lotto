package lotto.domain;

public class PurchaseCount {
    
    private static final int MIN_PAYMENT_AMOUNT = 0;
    
    private final int manualPurchaseCount;
    
    private final int automaticPurchaseCount;
    
    private PurchaseCount(int manualPurchaseCount, int automaticPurchaseCount) {
        this.manualPurchaseCount = manualPurchaseCount;
        this.automaticPurchaseCount = automaticPurchaseCount;
    }
    
    public static PurchaseCount of(PaymentAmount paymentAmount, String manualPurchaseCount) {
        if (!isInteger(manualPurchaseCount)) {
            throw new IllegalArgumentException();
        }
    
        int totalCount = paymentAmount.getPurchaseCount();
        int manualCount = Integer.parseInt(manualPurchaseCount);
        if (isNegative(manualCount) || isBiggerThanTotal(totalCount, manualCount)) {
            throw new IllegalArgumentException();
        }
        
        return new PurchaseCount(manualCount, totalCount - manualCount);
    }
    
    private static boolean isBiggerThanTotal(int totalCount, int purchaseCount) {
        return totalCount < purchaseCount;
    }
    
    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    private static boolean isNegative(int value) {
        return value < MIN_PAYMENT_AMOUNT;
    }
    
    public int getManualPurchaseCount() {
        return manualPurchaseCount;
    }
    
    public int getAutomaticPurchaseCount() {
        return automaticPurchaseCount;
    }
}
