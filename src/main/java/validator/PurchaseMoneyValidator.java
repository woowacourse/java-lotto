package validator;

public class PurchaseMoneyValidator {

    private static final int TICKET_PRICE = 1000;
    private static final String NOT_MULTIPLES_OF_1000_ERROR_MESSAGE = "금액을 1,000의 배수로 입력해주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "금액을 정수로 입력해주세요.";

    public static void validate(final String purchaseMoneyString) {
        int purchaseMoney = checkInteger(purchaseMoneyString);

        if (isNotDividedByTicketPrice(purchaseMoney)) {
            throw new IllegalArgumentException(NOT_MULTIPLES_OF_1000_ERROR_MESSAGE);
        }
    }

    private static int checkInteger(final String purchaseMoneyString) {
        int purchaseMoney;
        try {
            purchaseMoney = Integer.parseInt(purchaseMoneyString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
        return purchaseMoney;
    }

    private static boolean isNotDividedByTicketPrice(final int purchaseMoney) {
        return purchaseMoney % TICKET_PRICE != 0;
    }
}
