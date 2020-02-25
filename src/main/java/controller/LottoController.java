package controller;

import domain.Money;
import domain.RepeatCount;
import domain.lotto.*;
import domain.lotto.generator.RandomNumberGenerator;
import domain.lotto.generator.UserNumberGenerator;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        RepeatCount repeatCount = money.createRepeatCount();
        RepeatCount userRepeatCount = repeatCount.split(InputView.inputNumber(OutputView::printUserRepeatCountFormat));

        LottoGame userLottoGame = LottoGame.create(new UserNumberGenerator(), userRepeatCount);
        LottoGame autoLottoGame = LottoGame.create(new RandomNumberGenerator(), repeatCount);
        LottoGame lottoGame = LottoGame.merge(userLottoGame, autoLottoGame);

        OutputView.printLottoNumbersCount(userRepeatCount, repeatCount);
        OutputView.printLottoGame(lottoGame);

        LottoWinner lottoWinner = makeWinnerNumbers();
        LottoResult lottoResult = lottoGame.createGameResult(lottoWinner);

        OutputView.printResultAll(money, lottoResult);
    }

    private LottoWinner makeWinnerNumbers() {
        LottoNumbers winnerNumbers = LottoNumbersFactory.createLottoNumbers(InputView.inputNumbers(OutputView::printWinnerNumbersFormat));
        LottoNumber bonus = LottoNumberFactory.getInstance(InputView.inputNumber(OutputView::printBonusNumberFormat));
        return new LottoWinner(winnerNumbers, bonus);
    }
}
