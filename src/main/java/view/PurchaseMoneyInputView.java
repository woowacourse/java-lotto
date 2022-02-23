package view;

import validator.PurchaseMoneyValidator;

public class PurchaseMoneyInputView implements InputView<Integer> {

    @Override
    public Integer getUserInputData() {
        String userInput = scanner.nextLine();
        PurchaseMoneyValidator.validate(userInput);
        return Integer.valueOf(userInput);
    }

    public Integer printInputPurchaseMoneyAndGet() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return getUserInputData();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return printInputPurchaseMoneyAndGet();
        }
    }
}
