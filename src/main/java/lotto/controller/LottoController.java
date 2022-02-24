package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.utils.Validation;
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
        Validation.checkInputLottoWinningNumbers(value);

        return value;
    }

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    public int inputBonusNumber() {
        String bonusNumber = InputView.inputBonusNumber();
        Validation.checkValidateInt(bonusNumber);

        return Integer.parseInt(bonusNumber);
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
