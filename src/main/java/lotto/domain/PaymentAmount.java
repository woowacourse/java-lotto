package lotto.domain;

public class PaymentAmount {
    
    private static final int LOTTO_PAYMENT = 1000;
    
    private static final int MIN_PAYMENT_AMOUNT = 0;
    
    private static final int ZERO_REMAINDER = 0;
    
    private final int paymentAmount;
    
    private PaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    
    public static PaymentAmount from(String paymentAmount) {
        if (!isInteger(paymentAmount)) {
            throw new IllegalArgumentException();
        }
        
        int payment = Integer.parseInt(paymentAmount);
        if (!isZeroOrPositive(payment) || !isMultipleOfLottoPay(payment)) {
            throw new IllegalArgumentException();
        }
        
        return new PaymentAmount(payment);
    }
    
    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    private static boolean isZeroOrPositive(int value) {
        return value >= MIN_PAYMENT_AMOUNT;
    }
    
    private static boolean isMultipleOfLottoPay(int value) {
        return value % LOTTO_PAYMENT == ZERO_REMAINDER;
    }
    
    public int getPurchaseCount() {
        return paymentAmount / LOTTO_PAYMENT;
    }
}
