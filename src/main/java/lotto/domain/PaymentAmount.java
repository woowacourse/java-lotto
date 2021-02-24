package lotto.domain;

public class PaymentAmount {
    
    private static final int LOTTO_PAYMENT = 1000;
    private static final int MIN_PAYMENT_AMOUNT = 0;
    private static final int ZERO_REMAINDER = 0;
    
    private static final String ERROR_NEGATIVE_LOTTO_NUMBER = "0 혹은 양수만 입력해주세요.";
    private static final String ERROR_NOT_MULTIPLE_LOTTO_PAYMENT = "로또의 가격은 1000의 배수입니다.";
    private static final String ERROR_NOT_INTEGER = "숫자가 아닙니다.";
    
    private final int paymentAmount;
    
    private PaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    
    public static PaymentAmount from(String paymentAmount) {
        int payment = parseInt(paymentAmount);
        if (isNegative(payment)) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_LOTTO_NUMBER);
        }
        
        if (isMultipleOfLottoPayment(payment)) {
            return new PaymentAmount(payment);
        }
        
        throw new IllegalArgumentException(ERROR_NOT_MULTIPLE_LOTTO_PAYMENT);
    }
    
    private static int parseInt(String paymentAmount) {
        if (isInteger(paymentAmount)) {
            return Integer.parseInt(paymentAmount);
        }
        
        throw new IllegalArgumentException(ERROR_NOT_INTEGER);
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
    
    private static boolean isMultipleOfLottoPayment(int value) {
        return value % LOTTO_PAYMENT == ZERO_REMAINDER;
    }
    
    public int getPurchaseCount() {
        return paymentAmount / LOTTO_PAYMENT;
    }
}
