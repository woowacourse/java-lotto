package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    public static void main(String[] args) {
        int totalLottoCount = purchaseLotto();
        LottoCount lottoCount = createLottoCount(totalLottoCount);
        LottoTickets lottoTickets = createLottoTickets(lottoCount, inputManualLottoNumbers(lottoCount));
        WinningNumber winningNumber = inputWinningNumber();
        LottoResult lottoResult = countWinningLottos(lottoTickets, winningNumber);
        int profitRatio = calculateProfitRatio(totalLottoCount, lottoResult);

        printFinalResult(lottoResult, profitRatio);
    }

    private static int purchaseLotto() {
        PurchaseAmount amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        return lottoCount;
    }

    private static PurchaseAmount inputPurchaseAmount() {
        try {
            return new PurchaseAmount(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmount();
        }
    }

    private static LottoCount createLottoCount(int totalLottoCount) {
        try {
            return new LottoCount(totalLottoCount, InputView.inputManualLottoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return createLottoCount(totalLottoCount);
        }
    }

    private static List<List<String>> inputManualLottoNumbers(LottoCount lottoCount) {
        try {
            int manualLottoCount = lottoCount.getManualCount();
            return InputView.inputManualLottoNumbers(manualLottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputManualLottoNumbers(lottoCount);
        }
    }

    private static LottoTickets createLottoTickets(LottoCount lottoCount, List<List<String>> manualLottoNumbers) {
        Generator randomGenerator = new RandomNumberGenerator();
        LottoTickets lottoTickets = LottoFactory.createLottoTickets(lottoCount, randomGenerator, manualLottoNumbers);
        OutputView.printLottoTickets(lottoTickets, lottoCount);
        return lottoTickets;
    }

    private static WinningNumber inputWinningNumber() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputWinningNumber();
        }
    }

    private static LottoResult countWinningLottos(LottoTickets lottoTickets, WinningNumber winningNumber) {
        return lottoTickets.countWinningLotto(winningNumber);
    }

    private static int calculateProfitRatio(int lottoCount, LottoResult lottoResult) {
        return lottoResult.calculateProfitRatio(lottoCount);
    }

    private static void printFinalResult(LottoResult lottoResult, int profitRatio) {
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(profitRatio);
    }
}
