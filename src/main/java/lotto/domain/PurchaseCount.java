package lotto.domain;

public class PurchaseCount {
    
    private static final int MIN_PAYMENT_AMOUNT = 0;
    
    private static final String ERROR_NOT_INTEGER = "숫자가 아닙니다.";
    private static final String ERROR_NEGATIVE_LOTTO_NUMBER = "0 혹은 양수만 입력해주세요.";
    private static final String ERROR_ILLEGAL_MANUAL_COUNT = "수동 로또 구매 개수가 전체 로또 구매 개수보다 큽니다.";
    
    private final int manualPurchaseCount;
    
    private final int automaticPurchaseCount;
    
    private PurchaseCount(int manualPurchaseCount, int automaticPurchaseCount) {
        this.manualPurchaseCount = manualPurchaseCount;
        this.automaticPurchaseCount = automaticPurchaseCount;
    }
    
    public static PurchaseCount of(PaymentAmount paymentAmount, String manualPurchaseCount) {
        int manualCount = parseInt(manualPurchaseCount);
        int totalCount = paymentAmount.getPurchaseCount();
        if (isNegative(manualCount)) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_LOTTO_NUMBER);
        }
        
        if (isBiggerThanTotal(totalCount, manualCount)) {
            throw new IllegalArgumentException(ERROR_ILLEGAL_MANUAL_COUNT);
        }
        
        return new PurchaseCount(manualCount, totalCount - manualCount);
    }
    
    private static int parseInt(String manualPurchaseCount) {
        if (isInteger(manualPurchaseCount)) {
            return Integer.parseInt(manualPurchaseCount);
        }
        
        throw new IllegalArgumentException(ERROR_NOT_INTEGER);
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
