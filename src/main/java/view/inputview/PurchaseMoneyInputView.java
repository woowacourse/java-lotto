package view.inputview;

import validator.PurchaseMoneyValidator;

public class PurchaseMoneyInputView extends ConsoleInputView<Integer> {

    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "금액을 정수로 입력해주세요.";

    @Override
    public Integer getUserInputData() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        try {
            return inputPurchaseMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getUserInputData();
        }
    }

    private Integer inputPurchaseMoney() {
        String userInput = inputLine();
        int purchaseMoney = parse(userInput);
        PurchaseMoneyValidator.validate(purchaseMoney);
        return Integer.valueOf(userInput);
    }

    private int parse(String purchaseMoneyString) {
        try {
            return Integer.parseInt(purchaseMoneyString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }
}
