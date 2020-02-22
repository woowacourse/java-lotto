package controller;

import domain.*;
import domain.lottonumber.*;
import domain.lottonumber.generator.LottoGenerator;
import domain.lottonumber.generator.RandomLottoGenerator;
import domain.lottonumber.generator.UserLottoGenerator;
import domain.lottoresult.LottoResult;
import domain.lottoresult.LottoWinner;
import view.InputView;
import view.OutputView;

public class LottoController {
    LottoGame lottoGame = new LottoGame();

    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoResult lottoResult = new LottoResult();

        makeLottoNumbers(money.calculateGames());
        lottoGame.makeResult(lottoResult, makeWinnerNumbers());
        OutputView.printResult(money, lottoResult);
    }

    private LottoWinner makeWinnerNumbers() {
        UserLottoGenerator userNumberGenerator = new UserLottoGenerator();
        userNumberGenerator.init(InputView.inputWinnerNumbers());
        LottoTicket winnerNumbers = LottoTicketFactory.createLottoNumbers(userNumberGenerator);
        LottoNumber bonus = LottoNumber.of(InputView.inputBonusNumber());

        return new LottoWinner(winnerNumbers, bonus);
    }

    private void makeLottoNumbers(int repeat) {
        LottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        OutputView.printRepeat(repeat);
        for (int i = 0; i < repeat; i++) {
            LottoTicket lottoTicket = LottoTicketFactory.createLottoNumbers(randomLottoGenerator);
            OutputView.printLottoNumbers(lottoTicket);
            lottoGame.add(lottoTicket);
        }
    }
}
