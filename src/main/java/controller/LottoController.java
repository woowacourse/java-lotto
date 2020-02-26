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
    public void run() {
        Money money = new Money(InputView.inputMoney());
        LottoGame lottoGame = makeLottoGame(money.calculateGames());
        OutputView.printResult(money, makeResult(lottoGame));
    }

    private LottoGame makeLottoGame(LottoGameRepeat autoGameRepeat) {
        LottoGame lottoGame = new LottoGame();
        LottoGameRepeat userGameRepeat = autoGameRepeat.splitLottoGameCount(InputView.inputUserRepeat());

        OutputView.printRepeat(userGameRepeat, autoGameRepeat);
        makeUserLottoTicket(lottoGame, userGameRepeat);
        makeAutoLottoTicket(lottoGame, autoGameRepeat);
        OutputView.printLottoNumbers(lottoGame);

        return lottoGame;
    }


    private void makeUserLottoTicket(LottoGame lottoGame, LottoGameRepeat userGameRepeat) {
        OutputView.printUserLottoNumbersFormat();
        for (int count = 0; userGameRepeat.checkLoopTerminate(count); count++) {
            UserLottoGenerator userNumberGenerator = new UserLottoGenerator(InputView.inputUserLottoNumbers());
            lottoGame.add(LottoTicketFactory.createLottoNumbers(userNumberGenerator));
        }
    }

    private void makeAutoLottoTicket(LottoGame lottoGame, LottoGameRepeat autoGameRepeat) {
        LottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        for (int count = 0; autoGameRepeat.checkLoopTerminate(count); count++) {
            lottoGame.add(LottoTicketFactory.createLottoNumbers(randomLottoGenerator));
        }
    }

    private LottoResult makeResult(LottoGame lottoGame) {
        UserLottoGenerator userNumberGenerator = new UserLottoGenerator(InputView.inputWinnerNumbers());
        LottoTicket winnerNumbers = LottoTicketFactory.createLottoNumbers(userNumberGenerator);
        LottoNumber bonus = LottoNumberFactory.getLottoNumber(InputView.inputBonusNumber());

        return lottoGame.makeResult(new LottoWinner(winnerNumbers, bonus));
    }
}