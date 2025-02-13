package controller;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import domain.WinningInfo;
import java.util.List;
import java.util.Map;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void start() {
        int purchaseAmount = inputView.purchaseAmountInput();
        Ticket ticket = lottoService.createTicket(purchaseAmount);
        outputView.printPurchaseResult(ticket);
        lottoService.createLottos(ticket);

        List<Lotto> lottos = lottoService.getLottos();
        outputView.printLottos(lottos);

        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = lottoService.createLotto(winningNumbers);

        int bonusNumber = inputView.bonusNumberInput();
        WinningInfo winningInfo = lottoService.createWinningNumber(lotto, bonusNumber);
        Map<Rank, Integer> rankResult = lottoService.calculateRank(winningInfo, lottos);
        outputView.printWinningStatistic(rankResult);
       /*
        System.out.println("당첨 통계");
        System.out.println("-".repeat(9));
        for (Rank rank : rankResult.keySet()) {
            if(!rank.getDescription().isBlank()){
                System.out.println(rank.getDescription() + " - " + rankResult.get(rank));
            }
        }

        */

    }
}
