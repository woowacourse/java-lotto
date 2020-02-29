package controller;

import domain.*;
import domain.lottonumber.*;
import domain.lottonumber.generator.RandomLottoGenerator;
import domain.lottonumber.generator.UserLottoGenerator;
import domain.lottoresult.LottoResult;
import domain.lottoresult.LottoWinner;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoGame lottoGame = makeLottoGame(money.calculateGames());
        OutputView.printResult(money, makeResult(lottoGame));
    }

    private LottoGame makeLottoGame(LottoGameRepeat autoGameRepeat) {
        LottoGame lottoGame = new LottoGame();
        LottoGameRepeat userGameRepeat = autoGameRepeat.splitGame(InputView.inputUserRepeat());

        if (userGameRepeat.hasRepeat()) {
            makeUserLottoTickets(lottoGame, userGameRepeat);
        }
        lottoGame.makeLottoTickets(autoGameRepeat, new RandomLottoGenerator());

        OutputView.printRepeat(userGameRepeat, autoGameRepeat);
        OutputView.printLottoGame(lottoGame);
        return lottoGame;
    }


    private void makeUserLottoTickets(LottoGame lottoGame, LottoGameRepeat userGameRepeat) {
        OutputView.printUserLottoNumbersFormat();
        for (int count = 0; userGameRepeat.hasRepeat(count); count++) {
            UserLottoGenerator userNumberGenerator = new UserLottoGenerator(InputView.inputUserLottoNumbers());
            lottoGame.makeLottoTickets(userNumberGenerator);
        }
    }

    private LottoResult makeResult(LottoGame lottoGame) {
        UserLottoGenerator userNumberGenerator = new UserLottoGenerator(InputView.inputWinnerNumbers());
        LottoTicket winnerNumbers = LottoTicketFactory.createLottoTicket(userNumberGenerator);
        LottoNumber bonus = LottoNumberFactory.getLottoNumber(InputView.inputBonusNumber());

        return lottoGame.makeResult(new LottoWinner(winnerNumbers, bonus));
    }
}