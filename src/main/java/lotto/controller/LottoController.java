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

    public LottoController(){
    }

    public void printLottos() {
        OutputView.printLottos(lottos);
    }

    public void inputLottoMoney(int money) {
        lottos = new Lottos(money);
    }

    public void createLottoWinningNumbers() {
        String value = inputLottoWinningNumbers();
        int bonusNumber = inputBonusNumber();

        lottoWinningNumbers = new LottoWinningNumbers(value, bonusNumber);
    }

    private String inputLottoWinningNumbers() {
        String value = InputView.inputLottoWinningNumbers();
        value = value.replace(" ", "");
        Validation.checkInputLottoWinningNumbers(value);
        return value;
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

    public void printWinningResult() {
        OutputView.printWinningResult(lottoWinningNumbers);
    }

    public double calculateProfit(int money) {
        return (double) lottoWinningNumbers.calculateWinningMoney() / money;
    }

    public void printProfit(double profit) {
        OutputView.printProfit(profit);
    }
}
