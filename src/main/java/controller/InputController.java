package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.LottoNumber;
import util.Validator;
import view.InputView;

public class InputController {

    private static final String LOTTO_NUMBER_INPUT_DELIMITER = ", ";

    public int getMoney() {
        String inputMoney = InputView.scanMoney();
        Validator.validateInteger(inputMoney);
        int money = Integer.parseInt(inputMoney);
        Validator.validateNegativePrice(money);
        return money;
    }

    public List<LottoNumber> getWinningLottoNumbers() {
        String[] inputWinningLottoNumbers = InputView.scanWinningLottoNumbers().split(LOTTO_NUMBER_INPUT_DELIMITER);
        Validator.validateLottoNumbers(inputWinningLottoNumbers);
        return toLottoNumbers(inputWinningLottoNumbers);
    }

    public List<LottoNumber> getPassiveLottoNumbers() {
        String[] passiveLottoNumbers = InputView.scanPassiveLottoNumbers().split(LOTTO_NUMBER_INPUT_DELIMITER);
        Validator.validateLottoNumbers(passiveLottoNumbers);
        return toLottoNumbers(passiveLottoNumbers);
    }

    private List<LottoNumber> toLottoNumbers(String[] passiveLottoNumbers) {
        return Arrays.stream(passiveLottoNumbers)
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    public int getPassiveLottoCount() {
        String inputPassiveLottoCount = InputView.scanPassiveLottoNumbersCount();
        Validator.validateInteger(inputPassiveLottoCount);
        int passiveLottoCount = Integer.parseInt(inputPassiveLottoCount);
        Validator.validateNegativePrice(passiveLottoCount);
        return passiveLottoCount;
    }

    public LottoNumber getBonusNumber() {
        String inputBonusNumber = InputView.scanBonusNumber();
        Validator.validateInteger(inputBonusNumber);
        return LottoNumber.of(Integer.parseInt(inputBonusNumber));
    }
}
