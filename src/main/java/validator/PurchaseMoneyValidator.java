package validator;

public class PurchaseMoneyValidator {

    private static final String NOT_MULTIPLES_OF_1000_ERROR_MESSAGE = "금액을 1,000의 배수로 입력해주세요.";
    private static final String NOT_POSITIVE_ERROR_MESSAGE = "금액은 양수로 입력해야 합니다.";
    private static final int TICKET_PRICE = 1000;

    public static void validate(int purchaseMoney) {
        if (isMultiplesOfTicketPrice(purchaseMoney)) {
            throw new IllegalArgumentException(NOT_MULTIPLES_OF_1000_ERROR_MESSAGE);
        }

        if (isNotPositiveNumber(purchaseMoney)) {
            throw new IllegalArgumentException(NOT_POSITIVE_ERROR_MESSAGE);
        }
    }

    private static boolean isNotPositiveNumber(int purchaseMoney) {
        return purchaseMoney <= 0;
    }

    private static boolean isMultiplesOfTicketPrice(int purchaseMoney) {
        return purchaseMoney % TICKET_PRICE != 0;
    }
}
