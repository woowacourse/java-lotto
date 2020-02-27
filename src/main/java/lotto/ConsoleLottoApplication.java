package lotto;

import lotto.domain.LottoGame;
import lotto.domain.exception.DuplicateLottoNumberException;
import lotto.domain.exception.LottoNumberSizeException;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoRound;
import lotto.domain.number.WinningNumbers;
import lotto.domain.result.GameResults;
import lotto.domain.result.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        Money money = InputView.inputPurchaseMoney();

        List<LottoRound> manualLottos = InputView.inputManualLottoRounds(money);
        LottoGame lottoGame = LottoGame.initialize(money, manualLottos);
        OutputView.printPurchaseNumber(money, manualLottos);
        OutputView.printAllLottoNumbers(lottoGame);

        GameResults gameResults = lottoGame.calculateResult(inputWinningNumbers());
        OutputView.printGameResults(gameResults);
        OutputView.printYield(lottoGame.calculateYield(gameResults));
    }

    private static WinningNumbers inputWinningNumbers() {
        try {
            List<LottoNumber> lottoNumbers = InputView.inputWinningLottoNumbers();
            LottoNumber bonusNumber = InputView.inputBonusNumber();
            return new WinningNumbers(lottoNumbers, bonusNumber);
        } catch (DuplicateLottoNumberException | LottoNumberSizeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }
}
