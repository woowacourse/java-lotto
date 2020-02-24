package lotto;

import lotto.domain.exception.DuplicateLottoNumberException;
import lotto.domain.exception.LottoNumberSizeException;
import lotto.domain.number.LottoRounds;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.LottoRound;
import lotto.domain.number.WinningNumbers;
import lotto.domain.random.RandomNumberGenerator;
import lotto.domain.result.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        Money money = inputPurchaseNumber();
        LottoRounds lottoRounds = createAllLottoNumbers(money);
        OutputView.printAllLottoNumbers(lottoRounds);
        WinningNumbers winningNumbers = inputWinningNumbers();

        GameResults gameResults = createGameResults(lottoRounds, winningNumbers);
        OutputView.printGameResults(gameResults);

        OutputView.printYield(money.calculateYield(gameResults));
    }

    private static Money inputPurchaseNumber() {
        Money money = InputView.inputPurchaseMoney();
        OutputView.printPurchaseNumber(money);
        return money;
    }

    private static LottoRounds createAllLottoNumbers(Money money) {
        List<LottoRound> lottoRoundList = new ArrayList<>();
        for (int i = 0; i < money.calculateRound(); i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            List<LottoNumber> randomNumbers = randomNumberGenerator.generateNumbers();
            lottoRoundList.add(new LottoRound(randomNumbers));
        }
        return new LottoRounds(lottoRoundList);
    }

    private static GameResults createGameResults(LottoRounds lottoRounds, WinningNumbers winningNumbers) {
        List<GameResult> gameResults = lottoRounds.calcurateGameResult(winningNumbers);
        return new GameResults(gameResults);
    }

    private static WinningNumbers inputWinningNumbers() {
        try {
            List<LottoNumber> lottoNumbers = InputView.inputWinningNumbers();
            LottoNumber bonusNumber = InputView.inputBonusNumber();
            return new WinningNumbers(lottoNumbers, bonusNumber);
        } catch (DuplicateLottoNumberException | LottoNumberSizeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }
}
