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
        int payment = parseInt(paymentAmount);
        if (isZeroOrPositive(payment) && isMultipleOfLottoPayment(payment)) {
            return new PaymentAmount(payment);
        }
    
        throw new IllegalArgumentException();
    }
    
    private static int parseInt(String paymentAmount) {
        if (isInteger(paymentAmount)) {
            return Integer.parseInt(paymentAmount);
        }
        
        throw new IllegalArgumentException();
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
    
    private static boolean isMultipleOfLottoPayment(int value) {
        return value % LOTTO_PAYMENT == ZERO_REMAINDER;
    }
    
    public int getPurchaseCount() {
        return paymentAmount / LOTTO_PAYMENT;
    }
}
