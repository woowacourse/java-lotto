package controller;

import java.util.ArrayList;
import java.util.List;
import model.BonusNumber;
import model.Lotto;
import model.Purchasement;
import model.WinningNumber;
import model.WinningResult;
import model.WinningStatus;
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
        Purchasement purchasement = readPurchaseAmount();
        int lottoCount = findLottoCount(purchasement);
        List<Lotto> issuedLottos = issueLotto(lottoCount);

        WinningNumber winningNumber = readWinningNumber();
        BonusNumber bonusNumber = readBonusNumber(winningNumber);

        WinningResult winningResult = checkWinningResult(issuedLottos, winningNumber, bonusNumber);
        double earningRate = calculateEarningRate(winningResult, purchasement);

        printResult(winningResult, earningRate);
    }

    public Purchasement readPurchaseAmount() {
       return inputView.readPurchaseAmount();
    }

    public int findLottoCount(Purchasement purchasement) {
        int lottoCount = purchasement.calculateLottoCount();
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

    public List<Lotto> issueLotto(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto());
        }
        outputView.printLottos(lottos);
        return lottos;
    }

    public WinningNumber readWinningNumber() {
        return inputView.readWinningNumbers();
    }

    public BonusNumber readBonusNumber(WinningNumber winningNumber) {
        return inputView.readBonusNumbers(winningNumber);
    }

    public WinningResult checkWinningResult(List<Lotto> issuedLottos, WinningNumber winningNumber, BonusNumber bonusNumber) {
        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : issuedLottos) {
            int matchingCount = winningNumber.findMatchingCountWith(lotto.getNumbers());
            boolean matchesBonusNumber = bonusNumber.matchesWith(lotto.getNumbers());
            WinningStatus winningStatus = WinningStatus.findBy(matchingCount, matchesBonusNumber);
            winningResult.update(winningStatus);
        }
        return winningResult;
    }

    public double calculateEarningRate(WinningResult winningResult, Purchasement purchasement) {
        int totalPrice = winningResult.calculateTotalPrice();
        return ((double) totalPrice)/purchasement.getAmount();
    }

    public void printResult(WinningResult winningResult, double earningRate) {
        outputView.printWinningResult(winningResult);
        outputView.printEarningRate(earningRate);
    }
}
