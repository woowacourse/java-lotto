package controller;

import domain.Ball;
import domain.Lottos;
import domain.MatchDto;
import domain.WinningCountDto;
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
        int purchaseAmount = inputView.askPurchaseAmount();
        Lottos lottos = issueLottos(purchaseAmount);

        List<Integer> winningNumbers = inputView.askWinningNumbers();
        Ball bonus = new Ball(inputView.askBonusNumber());

        List<WinningCountDto> winningCountDtos = matchAndCountWinningLottos(lottos, winningNumbers, bonus);

        calculateYield(purchaseAmount, winningCountDtos);
    }

    private Lottos issueLottos(int purchaseAmount) {
        Lottos lottos = Lottos.createLottos(purchaseAmount);

        int quantity = lottos.getQuantity();
        outputView.printLottoQuantity(quantity);

        List<String> lottoNumbers = lottos.getLottoNumbers();
        outputView.printLottos(lottoNumbers);
        return lottos;
    }

    private List<WinningCountDto> matchAndCountWinningLottos(
            Lottos lottos, List<Integer> winningNumbers, Ball bonus
    ) {
        List<MatchDto> matchDtos = lottos.getMatchDtos(winningNumbers, bonus);
        List<WinningCountDto> winningCountDtos = WinningStatistics.calculateWinningCountDtos(matchDtos);
        outputView.printWinningStatistics(winningCountDtos);

        return winningCountDtos;
    }

    private void calculateYield(int purchaseAmount, List<WinningCountDto> winningCountDtos) {
        double yield = WinningStatistics.calculateYield(purchaseAmount, winningCountDtos);
        outputView.printYield(yield);
    }
}
