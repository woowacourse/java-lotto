package lotto.controller;

import java.util.List;
import java.util.Set;
import lotto.model.Cashier;
import lotto.model.DashBoard;
import lotto.model.Lotto;
import lotto.model.LottoTicket;
import lotto.model.WinningLotto;
import lotto.view.Console;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Cashier cashier;

    public LottoController(InputView inputView, OutputView outputView, Cashier cashier) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cashier = cashier;
    }

    public void start() {
        int purchaseAmount = requestPurchaseAmount();
        LottoTicket lottoTicket = purchaseLotto(purchaseAmount);
        WinningLotto winningLotto = requestWinningLotto();
        DashBoard dashBoard = judgeLottoResult(lottoTicket, winningLotto);
        printResult(dashBoard);
        end();
    }

    private int requestPurchaseAmount() {
        return inputView.requestPurchaseAmount();
    }

    private LottoTicket purchaseLotto(int purchaseAmount) {
        LottoTicket lottoTicket = cashier.payForLotto(purchaseAmount);
        outputView.printLottos(convertLottoDtos(lottoTicket.getLottos()));
        return lottoTicket;
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

    private DashBoard judgeLottoResult(LottoTicket lottoTicket, WinningLotto winningLotto) {
        DashBoard dashBoard = new DashBoard();
        dashBoard.recordWinningResults(winningLotto, lottoTicket);
        return dashBoard;
    }

    private void printResult(DashBoard dashBoard) {
        outputView.printResult(dashBoard.getRanks());
        outputView.printWinningRatio(dashBoard.calculateWinningRate());
    }

    private void end() {
        Console.close();
    }
}
