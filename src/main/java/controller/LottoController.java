package controller;

import domain.WinningResultEvaluator;
import java.util.List;
import domain.BonusNumber;
import domain.Lotto;
import domain.LottoMachine;
import domain.Purchase;
import domain.WinningNumber;
import domain.WinningResult;
import util.NumberGeneratorImpl;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        Purchase purchase = readPurchaseAmount();
        int lottoCount = findLottoCount(purchase);
        List<Lotto> issuedLottos = issueLotto(lottoCount);

        WinningNumber winningNumber = readWinningNumber();
        BonusNumber bonusNumber = readBonusNumber(winningNumber);

        WinningResult winningResult = findWinningResult(issuedLottos, winningNumber, bonusNumber);
        double earningRate = winningResult.calculateEarningRate(purchase);

        printResult(winningResult, earningRate);
    }

    public Purchase readPurchaseAmount() {
        outputView.printPurchaseAmountInstruction();
        try {
            String purchaseAmountInput = inputView.readPurchaseAmount();
            return new Purchase(purchaseAmountInput);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readPurchaseAmount();
        }
    }

    public int findLottoCount(Purchase purchase) {
        int lottoCount = purchase.calculateLottoCount();
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

    public List<Lotto> issueLotto(int lottoCount) {
        LottoMachine lottoMachine = new LottoMachine(new NumberGeneratorImpl());
        List<Lotto> lottos = lottoMachine.issueLotto(lottoCount);
        outputView.printLottos(lottos);
        return lottos;
    }

    public WinningNumber readWinningNumber() {
        outputView.printWinningNumbersInstruction();
        try {
            String winningNumbersInput = inputView.readWinningNumbers();
            return new WinningNumber(winningNumbersInput);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readWinningNumber();
        }
    }

    public BonusNumber readBonusNumber(WinningNumber winningNumber) {
        outputView.printBonusNumbersInstruction();
        try {
            String bonusNumberInput = inputView.readBonusNumbers();
            return new BonusNumber(bonusNumberInput, winningNumber);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readBonusNumber(winningNumber);
        }
    }

    public WinningResult findWinningResult(List<Lotto> issuedLottos,
                                           WinningNumber winningNumber,
                                           BonusNumber bonusNumber) {
        WinningResultEvaluator winningResultEvaluator = new WinningResultEvaluator(winningNumber, bonusNumber);
        return winningResultEvaluator.evaluate(issuedLottos);
    }

    public void printResult(WinningResult winningResult, double earningRate) {
        outputView.printWinningResult(winningResult);
        outputView.printEarningRate(earningRate);
    }
}
