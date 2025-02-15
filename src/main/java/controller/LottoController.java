package controller;

import java.util.List;
import model.BonusNumber;
import model.Lotto;
import util.LottoGenerator;
import model.Purchase;
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
        Purchase purchase = readPurchaseAmount();
        int lottoCount = findLottoCount(purchase);
        List<Lotto> issuedLottos = issueLotto(lottoCount);

        WinningNumber winningNumber = readWinningNumber();
        BonusNumber bonusNumber = readBonusNumber(winningNumber);

        WinningResult winningResult = checkWinningResult(issuedLottos, winningNumber, bonusNumber);
        double earningRate = winningResult.calculateEarningRate(purchase);

        printResult(winningResult, earningRate);
    }

    public Purchase readPurchaseAmount() {
        outputView.printPurchaseAmountInstruction();
        while (true) {
            try {
                String purchaseAmountInput = inputView.readPurchaseAmount();
                return new Purchase(purchaseAmountInput);
            }
            catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public int findLottoCount(Purchase purchase) {
        int lottoCount = purchase.calculateLottoCount();
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

    public List<Lotto> issueLotto(int lottoCount) {
        List<Lotto> lottos = LottoGenerator.generate(lottoCount);
        outputView.printLottos(lottos);
        return lottos;
    }

    public WinningNumber readWinningNumber() {
        outputView.printWinningNumbersInstruction();
        while (true) {
            try {
                String winningNumbersInput = inputView.readWinningNumbers();
                return new WinningNumber(winningNumbersInput);
            }
            catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public BonusNumber readBonusNumber(WinningNumber winningNumber) {
        outputView.printBonusNumbersInstruction();
        while (true) {
            try {
                String bonusNumberInput = inputView.readBonusNumbers();
                return new BonusNumber(bonusNumberInput, winningNumber);
            }
            catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
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

    public void printResult(WinningResult winningResult, double earningRate) {
        outputView.printWinningResult(winningResult);
        outputView.printEarningRate(earningRate);
    }
}
