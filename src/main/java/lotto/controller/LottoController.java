package lotto.controller;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.Constant;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.exception.InvalidException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private Lottos lottos;
    private LottoWinningNumbers lottoWinningNumbers;

    public LottoController() {
    }

    public void printLottos() {
        OutputView.printLottos(lottos);
    }

    public void inputLottoMoney(final int money) {
        lottos = new Lottos(money);
    }

    public void createLottoWinningNumbers() {
        String value = inputLottoWinningNumbers();
        int bonusNumber = inputBonusNumber();

        lottoWinningNumbers = new LottoWinningNumbers(value, bonusNumber);
    }

    private String inputLottoWinningNumbers() {
        String value = removeBlank(InputView.inputLottoWinningNumbers());

        return value;
    }

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    public int inputBonusNumber() {
        String bonusNumber = InputView.inputBonusNumber();
        checkValidateInt(bonusNumber);

        return Integer.parseInt(bonusNumber);
    }

    private static void checkValidateInt(final String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_MONEY);
        }
    }

    public void calculateRanks() {
        lottoWinningNumbers.initWinningResult();

        for (Lotto lotto : lottos.getLottos()) {
            lottoWinningNumbers.calculateWinning(lotto);
        }
    }

    public double calculateProfit(final int money) {
        return (double) lottoWinningNumbers.calculateWinningMoney() / money;
    }

    public void printWinningResult() {
        OutputView.printWinningResult(lottoWinningNumbers);
    }

    public void printProfit(final double profit) {
        OutputView.printProfit(profit);
        if (profit >= 1){
            OutputView.printWinningLottoProfit();
            return;
        }
        OutputView.printWinningLottoLoss();
    }
}
