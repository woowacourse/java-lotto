package view;

import validator.LottoNumberValidator;

public class BonusNumberInputView implements InputView<Integer> {

    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    @Override
    public Integer getUserInputData() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        try {
            return inputBonusNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getUserInputData();
        }
    }

    private Integer inputBonusNumber() {
        String inputData = scanner.nextLine();
        LottoNumberValidator.validate(inputData.trim());
        return Integer.valueOf(inputData);
    }
}
