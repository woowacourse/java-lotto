package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.LottoNumber;
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

    public List<LottoNumber> getWinningLottoNumbers() {
        String[] inputWinningLottoNumbers = InputView.scanWinningLottoNumbers().split(LOTTO_NUMBER_INPUT_DELIMITER);
        Validator.validateWinningNumberInput(inputWinningLottoNumbers);
        return Arrays.stream(inputWinningLottoNumbers)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    public LottoNumber getBonusNumber() {
        String inputBonusNumber = InputView.scanBonusNumber();
        Validator.validateInteger(inputBonusNumber);
        return LottoNumber.of(Integer.parseInt(inputBonusNumber));
    }
}
