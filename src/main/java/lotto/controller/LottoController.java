package lotto.controller;

import java.util.ArrayList;
import lotto.domain.*;
import lotto.util.StringConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        LottoGame lottoGame = new LottoGame(purchaseAmount, StringConverter.toInt(InputView.getManualLottoCount()));

        makeManualAndAutoLottos(lottoGame);
        OutputView.printLottos(lottoGame.getManualLottoCount(), lottoGame.getAutoLottoCount(), lottoGame.getLottos());

        WinningLotto winningLotto = getWinningLotto();
        RankBoard rankBoard = new RankBoard(winningLotto, lottoGame.getLottos());
        OutputView.printResult(rankBoard, rankBoard.calculateProfitRatio(purchaseAmount.getAmount()));
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(StringConverter.toInt(InputView.getAmount()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private void makeManualAndAutoLottos(LottoGame lottoGame) {
        InputView.printGetManualLottosMessage();
        lottoGame.makeManualLottos(getInputManualLottos(lottoGame));
        lottoGame.makeAutoLottos();
    }

    private List<List<Integer>> getInputManualLottos(LottoGame lottoGame) {
        try {
            List<List<Integer>> inputLottos = new ArrayList<>();
            for (int i = 0; i < lottoGame.getManualLottoCount(); i++) {
                inputLottos.add(StringConverter.toInts(InputView.getManualLotto()));
            }
            return inputLottos;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getInputManualLottos(lottoGame);
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            List<Integer> winningNumbers = StringConverter.toInts(InputView.getWinningNumbers());
            int bonusNumber = StringConverter.toInt(InputView.getBonusNumber());
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }
}
