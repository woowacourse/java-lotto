package controller;

import domain.Lottos;
import domain.MatchDto;
import domain.PurchaseAmount;
import domain.WinningCountDto;
import domain.WinningLotto;
import domain.WinningStatistics;
import java.util.List;
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
        int inputPurchaseAmount = inputView.askPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputPurchaseAmount);
        Lottos lottos = issueLottos(purchaseAmount.getMoney());

        List<Integer> winningNumbers = inputView.askWinningNumbers();
        int bonusNumber = inputView.askBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        List<WinningCountDto> winningCountDtos = matchAndCountWinningLottos(lottos, winningLotto);
        calculateYield(purchaseAmount.getMoney(), winningCountDtos);
    }

    private Lottos issueLottos(int purchaseAmount) {
        Lottos lottos = Lottos.createLottos(purchaseAmount);

        int quantity = lottos.getQuantity();
        outputView.printLottoQuantity(quantity);

        List<String> lottoNumbers = lottos.getLottoNumbers();
        outputView.printLottos(lottoNumbers);
        return lottos;
    }

    private List<WinningCountDto> matchAndCountWinningLottos(Lottos lottos, WinningLotto winningLotto) {
        List<MatchDto> matchDtos = lottos.getMatchDtos(winningLotto);
        List<WinningCountDto> winningCountDtos = WinningStatistics.calculateWinningCountDtos(matchDtos);
        outputView.printWinningStatistics(winningCountDtos);

        return winningCountDtos;
    }

    private void calculateYield(int purchaseAmount, List<WinningCountDto> winningCountDtos) {
        double yield = WinningStatistics.calculateYield(purchaseAmount, winningCountDtos);
        outputView.printYield(yield);
    }
}
