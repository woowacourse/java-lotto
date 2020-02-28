package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        int totalLottoCount = purchaseLotto();
        LottoCount lottoCount = createLottoCount(totalLottoCount);
        LottoTickets lottoTickets = createLottoTickets(totalLottoCount);
        WinningNumber winningNumber = inputWinningNumber();
        LottoResult lottoResult = countWinningLottos(lottoTickets, winningNumber);
        int profitRatio = calculateProfitRatio(totalLottoCount, lottoResult);

        printFinalResult(lottoResult, profitRatio);
    }

    private static int purchaseLotto() {
        PurchaseAmount amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        OutputView.printPurchaseCountMessage(lottoCount);
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

    private static LottoTickets createLottoTickets(int lottoCount) {
        Generator randomGenerator = new RandomNumberGenerator();
        LottoTickets lottoTickets = new LottoTickets(LottoFactory.createLottoTickets(lottoCount, randomGenerator));
        OutputView.printLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private static WinningNumber inputWinningNumber() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch(IllegalArgumentException | NullPointerException e){
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
