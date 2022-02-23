package view;

import validator.PurchaseMoneyValidator;

public class PurchaseMoneyInputView implements InputView<Integer> {

    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";

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
        String userInput = scanner.nextLine();
        PurchaseMoneyValidator.validate(userInput);
        return Integer.valueOf(userInput);
    }
}
