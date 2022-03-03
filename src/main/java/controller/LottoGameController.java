package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGameController {

    public void run() {
        LottoDispenser lottoDispenser = buildDispenserFromInputMoney();
        List<Lotto> lottos = buyLottos(lottoDispenser);
        OutputView.printLottos(lottos);

        LottoGame lottoGame = new LottoGame(lottos);
        WinningLotto winningLotto = getWinningLottoFromInput();
        lottoGame.calculatePrizeResult(winningLotto);

        OutputView.printFinalStatistic(lottoGame.getPrizeResult());
        OutputView.printEarningRate(lottoGame.calculateEarningRate());
    }

    private List<Lotto> buyLottos(LottoDispenser lottoDispenser) {
        int manualLottosCount = getManualLottoCountFromInput(lottoDispenser);
        InputView.showMessageInputLottoNumbers();
        for (int i = 0; i < manualLottosCount; i++) {
            buyLottoFromInput(lottoDispenser);
        }
        lottoDispenser.buyAutoLottos();
        List<Lotto> lottos = lottoDispenser.getBoughtLottos();
        OutputView.printLottosCount(manualLottosCount, lottos.size() - manualLottosCount);
        return lottos;
    }

    private LottoDispenser buildDispenserFromInputMoney() {
        try {
            return new LottoDispenser(InputView.inputMoney());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return buildDispenserFromInputMoney();
        }
    }

    private int getManualLottoCountFromInput(LottoDispenser lottoDispenser) {
        try {
            int manualLottosCount = InputView.inputManualLottosCount();
            lottoDispenser.checkEnoughMoneyRemain(manualLottosCount);
            return manualLottosCount;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualLottoCountFromInput(lottoDispenser);
        }
    }

    private void buyLottoFromInput(LottoDispenser lottoDispenser) {
        try {
            lottoDispenser.buyManualLotto(InputView.inputLottoNumbers());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            buyLottoFromInput(lottoDispenser);
        }
    }

    private WinningLotto getWinningLottoFromInput() {
        try {
            Lotto lotto  = getLottoFromInput();
            int bonus = getBonusNumberFromInput();
            return new WinningLotto(lotto, bonus);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLottoFromInput();
        }
    }

    private Lotto getLottoFromInput() {
        try {
            Lotto lotto  = new Lotto(InputView.inputWinningLottoNumbers());
            return lotto;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLottoFromInput();
        }
    }

    private int getBonusNumberFromInput() {
        try {
            int bonus = (InputView.inputBonus());
            return bonus;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumberFromInput();
        }
    }

}
