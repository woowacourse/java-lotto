package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import util.Validator;
import view.InputView;

public class InputController {

    private static final String LOTTO_NUMBER_INPUT_DELIMITER = ",";

    public int getMoney() {
        String inputMoney = InputView.scanMoney();
        Validator.validateInteger(inputMoney);
        int money = Integer.parseInt(inputMoney);
        Validator.validateNegativePrice(money);
        return money;
    }

    public List<Integer> getWinningLottoNumbers() {
        String[] inputWinningLottoNumbers = InputView.scanWinningLottoNumbers().split(LOTTO_NUMBER_INPUT_DELIMITER);
        Validator.validateWinningNumberInput(inputWinningLottoNumbers);
        return Arrays.stream(inputWinningLottoNumbers)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public int getBonusNumber() {
        String inputBonusNumber = InputView.scanBonusNumber();
        Validator.validateInteger(inputBonusNumber);
        return Integer.parseInt(inputBonusNumber);
    }
}
