package controller;

import domain.*;
import domain.lotto.*;
import domain.lotto.generator.RandomNumberGenerator;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoGame lottoGame = LottoGame.create(new RandomNumberGenerator(), money.countGames());
        OutputView.printLottoNumbersCount(money);
        OutputView.printLottoGame(lottoGame);
        LottoWinner lottoWinner = makeWinnerNumbers();
        LottoResult lottoResult = lottoGame.createGameResult(lottoWinner);
        OutputView.printResultTitle();
        OutputView.printLottoResult(lottoResult);
        OutputView.printEarning(money.calculateEarningRate(lottoResult.calculateEarning()));
    }

    private LottoWinner makeWinnerNumbers() {
        LottoNumbers winnerNumbers = LottoNumbersFactory.createLottoNumbers(InputView.inputWinnerNumbers());
        LottoNumber bonus = LottoNumber.of(InputView.inputBonusNumber());
        return new LottoWinner(winnerNumbers, bonus);
    }
}
