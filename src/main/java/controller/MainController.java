package controller;

import domain.LottoTickets;
import domain.Purchase;
import domain.Result;
import domain.WinLottoNumbers;
import java.util.ArrayList;
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
        int manualCount = getManualCount(money);
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

    private int getManualCount(int money) {
        try {
            return InputView.inputLottoAmount(money);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getManualCount(money);
        }
    }

    private LottoTickets getLottoTickets(Purchase purchase) {
        List<List<Integer>> manualLottoNumberInput = getManualLottoNumberInput(purchase);
        return new LottoTickets(manualLottoNumberInput, purchase.getAutoCount());
    }

    private List<List<Integer>> getManualLottoNumberInput(Purchase purchase) {
        if (purchase.getManualCount() == 0) {
            return new ArrayList<>();
        }
        try {
            return InputView.inputManualLottoNumbers(purchase);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getManualLottoNumberInput(purchase);
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