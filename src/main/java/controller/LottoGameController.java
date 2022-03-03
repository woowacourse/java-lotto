package controller;

import domain.Lotto;
import domain.LottoDispenser;
import domain.LottoGame;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGameController {

    public void run() {
        LottoDispenser lottoDispenser = new LottoDispenser(InputView.inputMoney());
        List<Lotto> lottos = buyLottos(lottoDispenser);
        OutputView.printLottos(lottos);

        LottoGame lottoGame = new LottoGame(lottos);
        WinningLotto winningLotto = getWinningLotto();
        lottoGame.calculatePrizeResult(winningLotto);

        OutputView.printFinalStatistic(lottoGame.getPrizeResult());
        OutputView.printEarningRate(lottoGame.calculateEarningRate());
    }

    private List<Lotto> buyLottos(LottoDispenser lottoDispenser) {
        int manualLottosCount = InputView.inputManualLottosCount();
        lottoDispenser.checkEnoughMoneyRemain(manualLottosCount);
        InputView.showMessageInputLottoNumbers();
        for (int i = 0; i < manualLottosCount; i++) {
            lottoDispenser.buyManualLotto(InputView.inputLottoNumbers());
        }
        lottoDispenser.buyAutoLottos();
        int autoLottosCount = lottoDispenser.getBoughtLottos().size() - manualLottosCount;
        OutputView.printLottosCount(manualLottosCount, autoLottosCount);
        return lottoDispenser.getBoughtLottos();
    }

    private WinningLotto getWinningLotto() {
        Lotto lotto  = new Lotto(InputView.inputWinningLottoNumbers());
        int bonus = InputView.inputBonus();
        return new WinningLotto(lotto, bonus);
    }

}
