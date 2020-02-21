package controller;

import domain.*;
import domain.lottonumber.*;
import domain.lottonumber.generator.NumberGenerator;
import domain.lottonumber.generator.RandomNumberGenerator;
import domain.lottonumber.generator.UserNumberGenerator;
import domain.lottoresult.LottoResult;
import domain.lottoresult.LottoWinner;
import view.InputView;
import view.OutputView;

public class LottoController {
    LottoGame lottoGame = new LottoGame();
    LottoResult lottoResult = new LottoResult();

    public void run() {
        Money money = new Money(InputView.inputMoney());
        makeLottoNumbers(money.calculateGames());
        lottoGame.makeResult(lottoResult, makeWinnerNumbers());
        printResult(money);
    }

    private void printResult(Money money) {
        OutputView.printResultTitle();
        OutputView.printResult(lottoResult);
        long earning = lottoResult.calculateEarning();
        OutputView.printEarning(money.calculateEarnings(earning));
    }

    private LottoWinner makeWinnerNumbers() {
        UserNumberGenerator userNumberGenerator = new UserNumberGenerator();
        userNumberGenerator.init(InputView.inputWinnerNumbers());
        LottoNumbers winnerNumbers = LottoNumbersFactory.createLottoNumbers(userNumberGenerator);
        LottoNumber bonus = LottoNumber.of(InputView.inputBonusNumber());

        return new LottoWinner(winnerNumbers, bonus);
    }

    private void makeLottoNumbers(int repeat) {
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        OutputView.printRepeat(repeat);
        for (int i = 0; i < repeat; i++) {
            LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(randomNumberGenerator);
            OutputView.printLottoNumbers(lottoNumbers);
            lottoGame.add(lottoNumbers);
        }
    }
}
