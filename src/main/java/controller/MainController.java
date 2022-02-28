package controller;

import domain.LottoTicket;
import domain.Purchase;
import domain.Rank;
import domain.Result;
import domain.WinLottoNumbers;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class MainController {

    public void run() {
        Purchase purchase = getPurchase();

        List<LottoTicket> lottoTickets = createLottoTickets(purchase.getManualCount(),
            purchase.getAutoCount());
        OutputView.printLottoTickets(lottoTickets);

        WinLottoNumbers winLottoNumbers = getWinNumbers();

        Result result = makeResult(lottoTickets, winLottoNumbers);
        printResult(result, purchase);
    }

    private Purchase getPurchase() {
        try {
            int money = InputView.inputMoney();
            int manualCount = InputView.inputLottoAmount();
            return new Purchase(money, manualCount);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getPurchase();
        }
    }

    private List<LottoTicket> createLottoTickets(int manualCount, int autoCount) {
        List<LottoTicket> lottoTickets;

        lottoTickets = InputView.inputManualLottoNumbers(manualCount).stream()
            .map(LottoTicket::of)
            .collect(Collectors.toList());
        for (int i = 0; i < autoCount; i++) {
            lottoTickets.add(LottoTicket.ofAuto());
        }
        return lottoTickets;
    }

    private WinLottoNumbers getWinNumbers() {
        try {
            String winLottoNumber = InputView.inputWinLottoNumbers();
            int bonus = InputView.inputBonusNumber();
            return WinLottoNumbers.of(winLottoNumber, bonus);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getWinNumbers();
        }
    }

    private Result makeResult(List<LottoTicket> lottoTickets, WinLottoNumbers winLottoNumbers) {
        Result result = new Result();
        for (LottoTicket lottoTicket : lottoTickets) {
            int matchCount = winLottoNumbers.countSameNumber(lottoTicket);
            boolean isBonus = winLottoNumbers.isContainsBonus(lottoTicket);
            result.add(Rank.of(matchCount, isBonus));
        }
        return result;
    }

    private void printResult(Result result, Purchase purchase) {
        OutputView.printResult(result);
        OutputView.printProfit(result.getProfit(purchase.getMoney()));
    }
}