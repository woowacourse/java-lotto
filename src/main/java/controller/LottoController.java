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

        makeLottoTicket(money.calculateGames());
        OutputView.printResult(money, makeResult());
    }

    private void makeUserLottoTicket(LottoGameRepeat userGameCount) {
        OutputView.printUserLottoNumbersFormat();
        for (int count = 0; userGameCount.checkLoopTerminate(count); count++) {
            UserLottoGenerator userNumberGenerator = new UserLottoGenerator(InputView.inputUserLottoNumbers());
            lottoGame.add(LottoTicketFactory.createLottoNumbers(userNumberGenerator));
        }
    }

    private void makeLottoTicket(LottoGameRepeat autoGameCount) {
        LottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        LottoGameRepeat userGameCount = autoGameCount.splitLottoGameCount(InputView.inputUserRepeat());
        OutputView.printRepeat(userGameCount, autoGameCount);

        makeUserLottoTicket(userGameCount);
        for (int count = 0; autoGameCount.checkLoopTerminate(count); count++) {
            lottoGame.add(LottoTicketFactory.createLottoNumbers(randomLottoGenerator));
        }
        OutputView.printLottoNumbers(lottoGame);
    }

    private LottoResult makeResult() {
        UserLottoGenerator userNumberGenerator = new UserLottoGenerator(InputView.inputWinnerNumbers());
        LottoTicket winnerNumbers = LottoTicketFactory.createLottoNumbers(userNumberGenerator);
        LottoNumber bonus = LottoNumberFactory.getLottoNumber(InputView.inputBonusNumber());

        return lottoGame.makeResult(new LottoWinner(winnerNumbers, bonus));
    }
}
