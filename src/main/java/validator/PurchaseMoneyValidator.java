package validator;

public class PurchaseMoneyValidator {

    private static final String NOT_MULTIPLES_OF_1000_ERROR_MESSAGE = "금액을 1,000의 배수로 입력해주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "금액을 정수로 입력해주세요.";

    public static void validate(String purchaseMoneyString) {
        int purchaseMoney = checkInteger(purchaseMoneyString);

        if (isMultiplesOf1000(purchaseMoney)) {
            throw new IllegalArgumentException(NOT_MULTIPLES_OF_1000_ERROR_MESSAGE);
        }
    }

    private static boolean isMultiplesOf1000(int purchaseMoney) {
        return purchaseMoney % 1000 != 0;
    }

    private static int checkInteger(String purchaseMoneyString) {
        int purchaseMoney;
        try {
            purchaseMoney = Integer.parseInt(purchaseMoneyString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
        return purchaseMoney;
    }
}
