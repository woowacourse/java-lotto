package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.Rank;
import lotto.model.Cashier;
import lotto.model.DashBoard;
import lotto.model.Lotto;
import lotto.model.WinningLotto;
import lotto.view.Console;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int purchaseAmount = requestPurchaseAmount();
        List<Lotto> lottos = purchaseLotto(purchaseAmount);
        WinningLotto winningLotto = requestWinningLotto();
        DashBoard dashBoard = judgeLottoResult(lottos, winningLotto);
        printResult(dashBoard, purchaseAmount);
        end();
    }

    private int requestPurchaseAmount() {
        return inputView.requestPurchaseAmount();
    }

    private List<Lotto> purchaseLotto(int purchaseAmount) {
        Cashier cashier = new Cashier();
        List<Lotto> lottos = cashier.payForLotto(purchaseAmount);
        outputView.printLottos(convertLottoDtos(lottos));
        return lottos;
    }

    private List<LottoDto> convertLottoDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(LottoDto::from)
                .toList();
    }

    private WinningLotto requestWinningLotto() {
        Set<Integer> winningNumbers = Set.copyOf(inputView.requestWinningNumbers());
        int bonusNumber = inputView.requestBonusNumber();
        return new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    private DashBoard judgeLottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        DashBoard dashBoard = new DashBoard();
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.determineRank(lotto);
            dashBoard.recordResult(rank);
        }
        return dashBoard;
    }

    private void printResult(DashBoard dashBoard, int purchaseAmount) {
        outputView.printResult(dashBoard.getRanks());
        outputView.printWinningRatio(calculateRatio(dashBoard.getRanks(), purchaseAmount));
    }

    private float calculateRatio(Map<Rank, Integer> ranks, int purchaseAmount) {
        return (float) calculateTotalWinningAmount(ranks) / purchaseAmount;
    }

    private int calculateTotalWinningAmount(Map<Rank, Integer> ranks) {
        int totalAmount = 0;
        for (Rank rank : ranks.keySet()) {
            totalAmount += rank.getWinningAmount() * ranks.get(rank);
        }
        return totalAmount;
    }

    private void end() {
        Console.close();
    }
}
