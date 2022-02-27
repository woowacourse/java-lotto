package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.LottoResult;
import lotto.exception.InvalidException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void printLottos(Lottos lottos) {
        OutputView.printLottos(lottos);
    }

    public Lottos inputLottoMoney(final int money) {
        return new Lottos(money);
    }

    public LottoWinningNumbers createLottoWinningNumbers() {
        String value = inputLottoWinningNumbers();
        int bonusNumber = inputBonusNumber();

        return new LottoWinningNumbers(value, bonusNumber);
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

    public LottoResult calculateRanks(Lottos lottos, LottoWinningNumbers lottoWinningNumbers) {
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : lottos.getLottos()) {
            lottoResult.calculateWinning(lottoWinningNumbers.getWinningLotto(), lottoWinningNumbers.getBonusNumber(), lotto);
        }

        return lottoResult;
    }


    public void printWinningResult(LottoWinningNumbers lottoWinningNumbers, LottoResult lottoResult) {
        OutputView.printWinningResult(lottoWinningNumbers, lottoResult);
    }
}
