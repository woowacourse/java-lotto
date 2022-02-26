package view.inputview;

import validator.LottoNumberValidator;

public class BonusNumberInputView extends ConsoleInputView<Integer> {

    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "입력된 값이 정수가 아닙니다.";

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
        String inputData = inputLine();
        int bonusNumber = parse(inputData.trim());
        LottoNumberValidator.validate(bonusNumber);
        return Integer.valueOf(inputData);
    }

    private static int parse(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }
}
