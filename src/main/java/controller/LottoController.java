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

        makeLottoNumbers(money.calculateGames());
        OutputView.printResult(money, makeResult());
    }

    private void makeLottoNumbers(LottoGameRepeat repeat) {
        LottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        OutputView.printRepeat(repeat);
        for (int count = 0; repeat.checkLoopTerminate(count); count++) {
            LottoTicket lottoTicket = LottoTicketFactory.createLottoNumbers(randomLottoGenerator);
            OutputView.printLottoNumbers(lottoTicket);
            lottoGame.add(lottoTicket);
        }
    }

    private LottoResult makeResult() {
        UserLottoGenerator userNumberGenerator = new UserLottoGenerator(InputView.inputWinnerNumbers());
        LottoTicket winnerNumbers = LottoTicketFactory.createLottoNumbers(userNumberGenerator);
        LottoNumber bonus = LottoNumberFactory.getLottoNumber(InputView.inputBonusNumber());

        return lottoGame.makeResult(new LottoWinner(winnerNumbers, bonus));
    }
}
