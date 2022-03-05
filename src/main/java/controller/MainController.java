package controller;

import domain.LottoTickets;
import domain.Purchase;
import domain.Result;
import domain.WinLottoNumbers;
import java.util.List;
import view.InputView;
import view.OutputView;

public class MainController {

    public void run() {
        Purchase purchase = getPurchase();

        LottoTickets lottoTickets = getLottoTickets(purchase);
        OutputView.printLottoTickets(purchase, lottoTickets);

        WinLottoNumbers winLottoNumbers = getWinNumbers();
        Result result = makeResult(lottoTickets, winLottoNumbers);
        printResult(result, purchase);
    }

    private Purchase getPurchase() {
        int money = getMoney();
        int manualCount = getManualCount();
        try {
            return new Purchase(money, manualCount);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getPurchase();
        }
    }

    private int getMoney() {
        try {
            return InputView.inputMoney();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getMoney();
        }
    }

    private int getManualCount() {
        try {
            return InputView.inputLottoAmount();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getManualCount();
        }
    }

    private LottoTickets getLottoTickets(Purchase purchase) {
        try {
            List<List<Integer>> manualLottoNumberInput = InputView.inputManualLottoNumbers(
                purchase);
            return new LottoTickets(manualLottoNumberInput, purchase.getAutoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getLottoTickets(purchase);
        }
    }


    private WinLottoNumbers getWinNumbers() {
        try {
            List<Integer> winLottoNumber = InputView.inputWinLottoNumbers();
            int bonus = InputView.inputBonusNumber();
            return WinLottoNumbers.of(winLottoNumber, bonus);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getWinNumbers();
        }
    }

    private Result makeResult(LottoTickets lottoTickets, WinLottoNumbers winLottoNumbers) {
        Result result = new Result();
        lottoTickets.get().stream()
            .map(winLottoNumbers::match)
            .forEach(result::add);
        return result;
    }

    private void printResult(Result result, Purchase purchase) {
        OutputView.printResult(result);
        OutputView.printProfit(result.getProfit(purchase.getMoney()));
    }
}