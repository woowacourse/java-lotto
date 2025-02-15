package controller;

import java.util.List;
import java.util.function.Supplier;
import model.BonusNumber;
import model.Lotto;
import util.LottoGenerator;
import model.Purchase;
import model.WinningNumber;
import model.WinningResult;
import model.WinningStatus;
import util.Parser;
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
        return retryUntilSuccess(() -> {
            String purchaseAmountInput = inputView.readPurchaseAmount();
            int purchaseAmount = Parser.parseOneNumber(purchaseAmountInput);
            return new Purchase(purchaseAmount);
        });
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
        return retryUntilSuccess(() -> {
            List<String> winningNumbersInput = inputView.readWinningNumbers();
            List<Integer> winningNumbers = Parser.parseNumbers(winningNumbersInput);
            return new WinningNumber(winningNumbers);
        });
    }

    public BonusNumber readBonusNumber(WinningNumber winningNumber) {
        outputView.printBonusNumbersInstruction();
        return retryUntilSuccess(() -> {
            String bonusNumberInput = inputView.readBonusNumbers();
            int bonusNumber = Parser.parseOneNumber(bonusNumberInput);
            return new BonusNumber(bonusNumber, winningNumber);
        });
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

    private <T> T retryUntilSuccess(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
