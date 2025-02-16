package controller;

import domain.Lotto;
import domain.Lottos;
import domain.Rank;
import domain.WinningNumbers;
import dto.response.LottosResponse;
import dto.response.WinningResultResponse;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int purchaseAmount = inputView.askPurchaseAmount();
        Lottos lottos = issueLottos(purchaseAmount);

        Lotto winningLotto = inputView.askWinningLotto();
        int bonusNumber = inputView.askBonusNumber();

        WinningNumbers winningNumbers = WinningNumbers.createByWinningLottoAndBonusNumber(winningLotto, bonusNumber);

        outputView.printWinningResult(getWinningResult(lottos, winningNumbers, purchaseAmount));
    }

    private Lottos issueLottos(int purchaseAmount) {
        Lottos lottos = Lottos.issueByMoney(purchaseAmount);

        int quantity = lottos.getQuantity();
        outputView.printLottoQuantity(quantity);

        outputView.printLottos(LottosResponse.from(lottos));
        return lottos;
    }

    private WinningResultResponse getWinningResult(Lottos lottos, WinningNumbers winningNumbers, int purchaseAmount) {
        Map<Rank, Integer> rankCount = lottos.getRankCount(winningNumbers);
        return WinningResultResponse.of(rankCount, winningNumbers.calculateYield(rankCount, purchaseAmount));
    }
}
