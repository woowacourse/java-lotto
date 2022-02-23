package view;

import validator.LottoNumberValidator;

public class BonusNumberInputView implements InputView<Integer> {

    @Override
    public Integer getUserInputData() {
        String inputData = scanner.nextLine();
        LottoNumberValidator.validate(inputData.trim());
        return Integer.valueOf(inputData);
    }

    public Integer printInputBonusNumberAndGet() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return getUserInputData();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return printInputBonusNumberAndGet();
        }
    }

}
