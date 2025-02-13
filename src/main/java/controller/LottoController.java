package controller;

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
        Lottos lottos = Lottos.of(purchaseAmount);

        int quantity = lottos.getQuantity();
        outputView.printLottoQuantity(quantity);

        List<String> lottoNumbers = lottos.getLottoNumbers();
        outputView.printLottos(lottoNumbers);

        List<Integer> winningNumbers = inputView.askWinningNumbers();
        int bonusNumber = inputView.askBonusNumber();

        List<MatchDto> matchDtos = lottos.getMatchDtos(winningNumbers, bonusNumber);
        List<WinningCountDto> winningCountDtos = WinningStatistics.calculateWinningCountDtos(matchDtos);
        outputView.printWinningStatistics(winningCountDtos);

        double yield = WinningStatistics.calculateYield(purchaseAmount, winningCountDtos);
        outputView.printYield(yield);
    }
}
